# -*- coding: utf-8 -*-
"""
Created on Tue Apr 25 22:35:20 2023

@author: Vic
"""

import pm4py

LOG_FILE_PATH = "LogProcesoBaseParaMinar.xes"
PETRI_NET_OUTPUT_FILE = 'PostalProcessInductive.pnml'
PETRI_NET_OUTPUT_FILE2 = 'PostalProcessAlpha.pnml'

def discover_process(log_file):
    base_event_log = pm4py.read_xes(log_file)
    petri_net, initial_marking, final_marking = pm4py.discover_petri_net_inductive(base_event_log, activity_key='concept:name', case_id_key='case:concept:name', timestamp_key='eveFecha')
    log = pm4py.write_pnml(petri_net, initial_marking, final_marking, PETRI_NET_OUTPUT_FILE)
    print(log)
    
    petri_net2, initial_marking2, final_marking2 = pm4py.discover_petri_net_alpha(base_event_log, activity_key='concept:name', case_id_key='case:concept:name', timestamp_key='eveFecha')
    log = pm4py.write_pnml(petri_net2, initial_marking2, final_marking2, PETRI_NET_OUTPUT_FILE2)
    print(log)

if __name__ == "__main__":
    discover_process(LOG_FILE_PATH)