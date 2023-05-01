# -*- coding: utf-8 -*-
"""
Created on Sun Apr 30 01:18:11 2023

https://www.geeksforgeeks.org/how-to-insert-a-pandas-dataframe-to-an-existing-postgresql-table/

create table fossil_fuels_c02(year int, country varchar,total int,solidfuel int, liquidfuel int,gasfuel int,cement int,gasflaring int,percapita int,bunkerfuels int);
"""

import psycopg2
import psycopg2.extras as extras
import pandas as pd
  
DB_HOST = "localhost"
DB_NAME = "UNLP-Tesis"
DB_USER = "postgres"
DB_PASSWORD = "1234"
DB_PORT = '5432'
  
def execute_values(conn, df, table):
  
    tuples = [tuple(x) for x in df.to_numpy()]
  
    cols = ','.join(list(df.columns))
    # SQL query to execute
    query = "INSERT INTO %s(%s) VALUES %%s" % (table, cols)
    cursor = conn.cursor()
    try:
        extras.execute_values(cursor, query, tuples)
        conn.commit()
    except (Exception, psycopg2.DatabaseError) as error:
        print("Error: %s" % error)
        conn.rollback()
        cursor.close()
        return 1
    print("the dataframe is inserted")
    cursor.close()
  
  
conn = psycopg2.connect(database=DB_NAME, user=DB_USER, password=DB_PASSWORD, host=DB_HOST, port=DB_PORT)
df = pd.read_csv('fossilfuels.csv')
execute_values(conn, df, 'fossil_fuels_c02')