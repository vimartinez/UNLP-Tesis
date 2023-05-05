#!/bin/bash

# Configuración de la conexión a la base de datos Oracle
ORACLE_USER="userName"
ORACLE_PASSWORD="userPassword"
ORACLE_HOST="192.168.0.54"
ORACLE_PORT="5001"
ORACLE_SID="bdProd"

# Consulta SQL a ejecutar
SQL_QUERY="SELECT env_id as case_id, eve_descrip as activity, 
                    eve_fecha as timestamp, eve_id as activityid
           FROM envios
           WHERE informado IS NULL
           ORDER BY env_id"

# Archivo de salida CSV
OUTPUT_FILE="TrazasEntrada.csv"

# Conectarse a la base de datos Oracle y ejecutar la consulta
sqlplus -s ${ORACLE_USER}/${ORACLE_PASSWORD}@${ORACLE_HOST}:${ORACLE_PORT}/${ORACLE_SID} << EOF
set pagesize 0
set colsep ,
set feedback off
set echo off
set heading off
spool ${OUTPUT_FILE}
${SQL_QUERY};
spool off
exit
EOF
