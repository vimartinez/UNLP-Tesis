# -*- coding: utf-8 -*-
"""
Created on Mon Apr 26 22:38:59 2023

@author: Vic
"""

import pm4py
import pandas as pd
import sqlalchemy

POSTAL_PROCESS_PETRI_NET = "PostalProcess.pnml"
INPUT_LOG_FILE = "TrazasDeEntrada.csv"
DB_HOST = "localhost"
DB_NAME = "UNLP-Tesis"
DB_USER = "postgres"
DB_PASSWORD = "1234"
EXPORT_FILES = False

def mine_postal_data():
    
    #importar csv
    event_log = pd.read_csv(INPUT_LOG_FILE, sep=',')
    event_log = pm4py.format_dataframe(event_log, case_id='case_id', activity_key='activity', timestamp_key='timestamp')
    #importar proceso
    petri_net, initial_marking, final_marking = pm4py.read_pnml(POSTAL_PROCESS_PETRI_NET)
    #verificación de conformidad
    conformance_token_based_replay = pm4py.conformance_diagnostics_token_based_replay(event_log, petri_net, initial_marking, final_marking, activity_key='concept:name', case_id_key='case:concept:name', timestamp_key='time:timestamp')
    data_frame = pd.DataFrame(conformance_token_based_replay)
    merged_df = pd.merge(event_log, data_frame, left_index=True, right_index=True, how='inner')
    merged_df.drop('case:concept:name', inplace=True, axis=1)
    merged_df.drop('concept:name', inplace=True, axis=1)
    merged_df.drop('time:timestamp', inplace=True, axis=1)
    merged_df.drop('@@index', inplace=True, axis=1)
    merged_df.drop('@@case_index', inplace=True, axis=1)
    merged_df.drop('activated_transitions', inplace=True, axis=1)
    merged_df.drop('reached_marking', inplace=True, axis=1)
    merged_df.drop('enabled_transitions_in_marking', inplace=True, axis=1)
    merged_df.drop('transitions_with_problems', inplace=True, axis=1)
    merged_df.drop('missing_tokens', inplace=True, axis=1)
    merged_df.drop('consumed_tokens', inplace=True, axis=1)
    merged_df.drop('remaining_tokens', inplace=True, axis=1)
    merged_df.drop('produced_tokens', inplace=True, axis=1)
    
    merged_df.to_csv("merged_df.csv",index=False, header=True)
    filtered_traces =merged_df.query("trace_fitness < 0.5")

        
    #Filtrado por ajuste menor a 0.5
   # filtered_traces = list(filter(lambda x: x['trace_fitness'] < 0.5, event_log2))
    #Filrado por baja performance ...
    filtered_traces_performance = pm4py.filter_case_performance(event_log, 800000.0, 19000000.0, timestamp_key='time:timestamp', case_id_key='case:concept:name')
    #Remover columnas adicionales
    data_frame2 = pd.DataFrame(filtered_traces_performance)
    data_frame2.drop('case:concept:name', inplace=True, axis=1)
    data_frame2.drop('concept:name', inplace=True, axis=1)
    data_frame2.drop('time:timestamp', inplace=True, axis=1)
    data_frame2.drop('@@index', inplace=True, axis=1)
    data_frame2.drop('@@case_index', inplace=True, axis=1)
       
    #Export a BD Postgre
    engine = sqlalchemy.create_engine("postgresql://"+DB_USER+":"+DB_PASSWORD+"@"+DB_HOST+"/"+DB_NAME)
    merged_df.to_sql('trazasajustebajo', engine, if_exists='append', index=False)
    data_frame2.to_sql('trazasexcesomovimientos', engine, if_exists='append', index=False)

    if EXPORT_FILES == True:

        OUTPUT_LOG_FILE = "TrazasConAjusteBajoAPP.csv"
        OUTPUT_LOG_FILE2 = "TrazasConBajaPerformanceAPP2.csv"
        OUTPUT_LOG_FILE_XES = "TrazasConAjusteBajoApp.xes"
        OUTPUT_LOG_FILE_XES2 = "TrazasConBajaPerformanceAPP.xes"
        
        #Expot a csv las trazas con ajuste bajo
        data_frame = pd.DataFrame(filtered_traces)
        data_frame.to_csv(OUTPUT_LOG_FILE,index=False, header=True)       
        #Export a csv las trazas con más de diez días
        data_frame2.to_csv(OUTPUT_LOG_FILE2,index=False, header=True)       
        #Export a xes las trazas con ajuste bajo
        #pm4py.write_xes(data_frame, OUTPUT_LOG_FILE_XES)     
        #Export a xes las trazas con más de diez días
        pm4py.write_xes(filtered_traces_performance, OUTPUT_LOG_FILE_XES2)


if __name__ == "__main__":
    mine_postal_data()
    

    

    