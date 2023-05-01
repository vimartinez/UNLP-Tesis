# -*- coding: utf-8 -*-
"""
Created on Sun Apr 30 00:31:11 2023

@author: Vic
"""

import psycopg2

DB_HOST = "localhost"
DB_NAME = "UNLP-Tesis"
DB_USER = "postgres"
DB_PASSWORD = "1234"

def connect_to_postgres():
    """ Connect to the PostgreSQL database server """
    conn = None
    try:

        conn = psycopg2.connect( host=DB_HOST, database=DB_NAME, user=DB_USER,  password=DB_PASSWORD)
        # create a cursor
        cur = conn.cursor()
        # execute a statement
        print('PostgreSQL database version:')
        cur.execute('SELECT version()')
        # display the PostgreSQL database server version
        db_version = cur.fetchone()
        print(db_version) 
        cur.execute('CREATE TABLE public.vendors (vendor_id int4 NOT NULL GENERATED ALWAYS AS IDENTITY, vendor_name varchar NULL)')
        # close the communication with the PostgreSQL
        cur.close()
    except (Exception, psycopg2.DatabaseError) as error:
        print(error)
    finally:
        if conn is not None:
            conn.close()
            print('Database connection closed.')

def insert_vendor(vendor_name):
    """ insert a new vendor into the vendors table """
    sql = """INSERT INTO vendors(vendor_name)
             VALUES(%s) RETURNING vendor_id;"""
    conn = None
    vendor_id = None
    try:
        print('Insert a vendor ')
        # connect to the PostgreSQL database
        conn = psycopg2.connect( host=DB_HOST, database=DB_NAME, user=DB_USER,  password=DB_PASSWORD)
        # create a new cursor
        cur = conn.cursor()
        # execute the INSERT statement
        cur.execute(sql, (vendor_name,))
        # get the generated id back
        vendor_id = cur.fetchone()[0]
        # commit the changes to the database
        conn.commit()
        # close communication with the database
        cur.close()
    except (Exception, psycopg2.DatabaseError) as error:
        print(error)
    finally:
        if conn is not None:
            conn.close()

    return vendor_id


def insert_vendor_list(vendor_list):
    """ insert multiple vendors into the vendors table  """
    sql = "INSERT INTO vendors(vendor_name) VALUES(%s)"
    conn = None
    try:
        print('Insert multiple vendors ')
        # connect to the PostgreSQL database
        conn = psycopg2.connect( host=DB_HOST, database=DB_NAME, user=DB_USER,  password=DB_PASSWORD)
        # create a new cursor
        cur = conn.cursor()
        # execute the INSERT statement
        cur.executemany(sql,vendor_list)
        # commit the changes to the database
        conn.commit()
        # close communication with the database
        cur.close()
    except (Exception, psycopg2.DatabaseError) as error:
        print(error)
    finally:
        if conn is not None:
            conn.close()

if __name__ == "__main__":
    #Test DB Connection
    connect_to_postgres();
    # insert one vendor
    insert_vendor("3M Co.")
    # insert multiple vendors
    insert_vendor_list([
        ('AKM Semiconductor Inc.',),
        ('Asahi Glass Co Ltd.',),
        ('Daikin Industries Ltd.',),
        ('Dynacast International Inc.',),
        ('Foster Electric Co. Ltd.',),
        ('Murata Manufacturing Co. Ltd.',)
    ])