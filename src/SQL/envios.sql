-- public.envios definition
-- Drop table
-- DROP TABLE public.envios;

CREATE TABLE public.envios (
	id int4 NOT NULL GENERATED ALWAYS AS IDENTITY,
	suc_id int4 NOT NULL,
	direccion varchar NULL,
	localidad varchar NULL,
	partido varchar NULL,
	provincia varchar NULL,
	nro_seguimiento varchar NULL,
	fechaingreso date NULL
);