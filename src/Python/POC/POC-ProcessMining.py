# -*- coding: utf-8 -*-
"""
Created on Tue Apr 25 22:34:36 2023

@author: Vic
"""

import pm4py
import pandas as pd

POSTAL_PROCESS_PETRI_NET = "PostalProcess.pnml"
INPUT_LOG_FILE = "EventLogPOC.xes"
OUTPUT_LOG_FILE = "TrazasConAjusteBajoPOC.csv"
OUTPUT_LOG_FILE2 = "TrazasConBajaPerformancePOC.csv"
OUTPUT_LOG_FILE_XES = "TrazasConAjusteBajoPOC.xes"
OUTPUT_LOG_FILE_XES2 = "TrazasConBajaPerformancePOC.xes"

def mine_postal_data():
    
    #Importar log y proceso
    event_log = pm4py.read_xes(INPUT_LOG_FILE)
    petri_net, initial_marking, final_marking = pm4py.read_pnml(POSTAL_PROCESS_PETRI_NET)
    start_activities = pm4py.get_start_activities(event_log)
    end_activities = pm4py.get_end_activities(event_log)
    print("Start activities: {}\nEnd activities: {}".format(start_activities, end_activities))
    
    #Verificación de conformidad
    conformance_token_based_replay = pm4py.conformance_diagnostics_token_based_replay(event_log, petri_net, initial_marking, final_marking, activity_key='concept:name', case_id_key='case:concept:name', timestamp_key='time:timestamp')
  #  conformance_alignments = pm4py.conformance_diagnostics_alignments(event_log, petri_net, initial_marking, final_marking, activity_key='concept:name', case_id_key='case:concept:name', timestamp_key='time:timestamp')
    
    #Filtrado por ajuste menor a 0.5
    filtered_traces = list(filter(lambda x: x['trace_fitness'] < 0.5, conformance_token_based_replay))
    
    #Export filtrados ajuste menor a 0.5
    data_frame = pd.DataFrame(filtered_traces)
    data_frame.to_csv(OUTPUT_LOG_FILE)
   # pm4py.write_xes(data_frame, OUTPUT_LOG_FILE_XES)
    print(data_frame.count)
   
    #Filrado por baja performance ...
    filtered_traces_performance = pm4py.filter_case_performance(event_log, 800000.0, 19000000.0, timestamp_key='time:timestamp', case_id_key='case:concept:name')
    
    #Export filtrados baja performance
    data_frame2 = pd.DataFrame(filtered_traces_performance)
    data_frame2.to_csv(OUTPUT_LOG_FILE2)
    pm4py.write_xes(filtered_traces_performance, OUTPUT_LOG_FILE_XES2)
    print(data_frame2.count())
    
  #  print("Conformance Token Based Replay:")
  #  data_frame = pd.DataFrame(conformance_token_based_replay)
  #  print("Conformance Alignments :")
  #  data_frame2 = pd.DataFrame(conformance_token_based_replay)
  #  print(data_frame2.to_string(index=False))

if __name__ == "__main__":
    mine_postal_data()