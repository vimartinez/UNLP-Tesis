-- public.sucursales definition
-- Drop table
-- DROP TABLE public.sucursales;

CREATE TABLE public.sucursales (
	suc_id int4 NOT NULL GENERATED ALWAYS AS IDENTITY,
	suc_descripcion varchar NULL,
	suc_direccion varchar NULL,
	suc_partido varchar NULL,
	suc_provincia varchar NULL,
	suc_telefono varchar NULL,
	suc_mail varchar NULL,
	CONSTRAINT sucursales_pk PRIMARY KEY (suc_id)
);