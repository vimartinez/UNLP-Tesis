# -*- coding: utf-8 -*-
"""
Created on Mon Apr 26 22:38:59 2023

@author: Vic
"""

import pm4py
import pandas as pd
import sqlalchemy
import smtplib 
import shutil
import os
import shutil
from datetime import datetime

POSTAL_PROCESS_PETRI_NET = "PostalProcessAlpha.pnml"
INPUT_LOG_FILE = "TrazasEntrada-01.csv"
DB_HOST = "localhost"
DB_NAME = "UNLP-Tesis"
DB_USER = "postgres"
DB_PASSWORD = "1234"
EXPORT_FILES = False

def mine_postal_data():
    
    if os.path.exists(INPUT_LOG_FILE):    
    
        #importar csv
        event_log = pd.read_csv(INPUT_LOG_FILE, sep=',')
        event_log = event_log.applymap(str)
        event_log = pm4py.format_dataframe(event_log, case_id='case_id', activity_key='activity', timestamp_key='timestamp')
        
        #importar proceso
        petri_net, initial_marking, final_marking = pm4py.read_pnml(POSTAL_PROCESS_PETRI_NET)
        
        #verificación de conformidad
        conformance_token_based_replay = pm4py.conformance_diagnostics_token_based_replay(event_log, petri_net, initial_marking, final_marking, activity_key='activity', case_id_key='case_id', timestamp_key='time:timestamp')
        data_frame = pd.DataFrame(conformance_token_based_replay)
        event_log_traces = event_log.drop_duplicates(subset=['case_id'])
        event_log_traces.drop('activityid', inplace=True, axis=1)
        event_log_traces.drop('activity', inplace=True, axis=1)
        merged_df = pd.merge(event_log_traces, data_frame, left_index=True, right_index=True, how='inner')
        
        #Remover columnas adicionales
        merged_df.drop('case:concept:name', inplace=True, axis=1)
        merged_df.drop('concept:name', inplace=True, axis=1)
        merged_df.drop('time:timestamp', inplace=True, axis=1)
        merged_df.drop('activated_transitions', inplace=True, axis=1)
        merged_df.drop('reached_marking', inplace=True, axis=1)
        merged_df.drop('enabled_transitions_in_marking', inplace=True, axis=1)
        merged_df.drop('transitions_with_problems', inplace=True, axis=1)
        merged_df.drop('missing_tokens', inplace=True, axis=1)
        merged_df.drop('consumed_tokens', inplace=True, axis=1)
        merged_df.drop('remaining_tokens', inplace=True, axis=1)
        merged_df.drop('produced_tokens', inplace=True, axis=1)
        merged_df.drop('trace_is_fit', inplace=True, axis=1)
    
        #Filtrado por ajuste menor a 0.5
        traces_with_low_alignemt = merged_df.query("trace_fitness < 0.5")
        
        #Filrado por tiempo excesivo de resolución
        filtered_traces_performance = pm4py.filter_case_performance(event_log, 800000.0, 19000000.0, timestamp_key='time:timestamp', case_id_key='case:concept:name')
        
        #Remover columnas adicionales
        traces_with_excessive_time_detail = pd.DataFrame(filtered_traces_performance)
        traces_with_excessive_time_detail.drop('case:concept:name', inplace=True, axis=1)
        traces_with_excessive_time_detail.drop('concept:name', inplace=True, axis=1)
        traces_with_excessive_time_detail.drop('time:timestamp', inplace=True, axis=1)
        traces_with_excessive_time = traces_with_excessive_time_detail.drop_duplicates(subset=['case_id'])
        traces_with_excessive_time.drop('activityid', inplace=True, axis=1)
        traces_with_excessive_time.drop('activity', inplace=True, axis=1)
           
        event_log.drop('case:concept:name', inplace=True, axis=1)
        event_log.drop('concept:name', inplace=True, axis=1)
        event_log.drop('time:timestamp', inplace=True, axis=1)
       
        #Export a BD Postgre
        engine = sqlalchemy.create_engine("postgresql://"+DB_USER+":"+DB_PASSWORD+"@"+DB_HOST+"/"+DB_NAME)
        event_log.to_sql('trazas', engine, if_exists='append', index=False)
        traces_with_low_alignemt.to_sql('trazasajustebajo', engine, if_exists='append', index=False)
        traces_with_excessive_time.to_sql('trazasexcesotiempo', engine, if_exists='append', index=False)
        #send_notification()
        move_file_proceced()
        
        if EXPORT_FILES == True:
    
            OUTPUT_LOG_FILE = "TrazasConAjusteBajoAPP.csv"
            OUTPUT_LOG_FILE2 = "TrazasConBajaPerformanceAPP2.csv"
            OUTPUT_LOG_FILE_XES = "TrazasConAjusteBajoApp.xes"
            OUTPUT_LOG_FILE_XES2 = "TrazasConBajaPerformanceAPP.xes"
            
            #Expot a csv las trazas con ajuste bajo
            data_frame = pd.DataFrame(traces_with_low_alignemt)
            data_frame.to_csv(OUTPUT_LOG_FILE,index=False, header=True)       
            #Export a csv las trazas con más de diez días
            traces_with_excessive_time_detail.to_csv(OUTPUT_LOG_FILE2,index=False, header=True)       
            #Export a xes las trazas con ajuste bajo
            #pm4py.write_xes(data_frame, OUTPUT_LOG_FILE_XES)     
            #Export a xes las trazas con más de diez días
            #pm4py.write_xes(filtered_traces_performance, OUTPUT_LOG_FILE_XES2)
      
    else:
        print("No hay archivos de trazas para procesar")
    
        
def send_notification():
    remitente = "Notificaciones <notificaciones@mail.com.ar>" 
    destinatario = "Responsable de envios <responsable@mail.com.ar>" 
    asunto = "Posibles desvíos operativos en envíos de trazas" 
    mensaje = """Atención:<br/> <br/> 
    Se encontraron posibles desvíos operativos en el procesamiento de varios envios. <br>
    Ingresar en la aplicación de seguimiento para su gestión. <br><br>
    Este mensaje se envió de forma automática, no responder al mismo.
    """

    email = """From: %s 
    To: %s 
    MIME-Version: 1.0 
    Content-type: text/html 
    Subject: %s 

    %s
    """ % (remitente, destinatario, asunto, mensaje) 
    try: 
        smtp = smtplib.SMTP('localhost') 
        smtp.sendmail(remitente, destinatario, email) 
        print ("Correo enviado") 
    except: 
        print ("Error: no se pudo enviar el mensaje. ")

def move_file_proceced():
    fecha_actual = datetime.now().strftime("%Y-%m-%d")  
    nuevo_nombre = f"{INPUT_LOG_FILE.split('.')[0]}_{fecha_actual}.csv"
    ruta_procesados = "procesados"
    os.rename(INPUT_LOG_FILE, nuevo_nombre)
    shutil.move(nuevo_nombre, os.path.join(ruta_procesados, nuevo_nombre))
    
if __name__ == "__main__":
    mine_postal_data()
    
    