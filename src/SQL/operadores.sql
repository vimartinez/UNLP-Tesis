-- public.operadores definition
-- Drop table
-- DROP TABLE public.operadores;

CREATE TABLE public.operadores (
	ope_id int4 NOT NULL GENERATED ALWAYS AS IDENTITY,
	ope_nombre varchar NULL,
	ope_cargo varchar NULL,
	ope_telefono varchar NULL,
	ope_mail varchar NULL,
	CONSTRAINT operadores_pk PRIMARY KEY (ope_id)
);