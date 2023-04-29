# -*- coding: utf-8 -*-
"""
Created on Mon Apr 26 22:38:59 2023

@author: Vic
"""

import pm4py
import pandas as pd

POSTAL_PROCESS_PETRI_NET = "PostalProcess.pnml"
INPUT_LOG_FILE = "evenlog_202203261922-Parcial_9.csv"
OUTPUT_LOG_FILE = "TrazasConAjusteBajo.csv"
OUTPUT_LOG_FILE2 = "TrazasConBajaPerformance.csv"


def mine_postal_data():
    event_log = pm4py.read_xes(INPUT_LOG_FILE)
    petri_net, initial_marking, final_marking = pm4py.read_pnml(POSTAL_PROCESS_PETRI_NET)
   
    
    

if __name__ == "__main__":
    mine_postal_data()
    

    

    