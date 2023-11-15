# -*- coding: utf-8 -*-
"""
Created on Sun Oct 22 12:55:15 2023

@author: Vic
"""

import pm4py
import pandas as pd

INPUT_XES_FILE = "01-evenlog_202203261656-BaseCSV-Parcial-filtrado-Heuristicas.xes"
OUTPUT_CSV_FILE = "eventLogOrig-01.csv"

log = pm4py.read_xes(INPUT_XES_FILE) #Input Filename
df = pm4py.convert_to_dataframe(log)
#df
df.to_csv(OUTPUT_CSV_FILE)