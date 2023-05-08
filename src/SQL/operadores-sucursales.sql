-- public.operadores_sucursales definition
-- Drop table
-- DROP TABLE public.operadores_sucursales;

CREATE TABLE public.operadores_sucursales (
	opesuc_id int4 NOT NULL GENERATED ALWAYS AS IDENTITY,
	suc_id int4 NOT NULL,
	ope_id int4 NOT NULL,
	CONSTRAINT operadores_sucursales_pk PRIMARY KEY (opesuc_id)
);

-- public.operadores_sucursales foreign keys

ALTER TABLE public.operadores_sucursales ADD CONSTRAINT operadores_sucursales_fk FOREIGN KEY (suc_id) REFERENCES public.sucursales(suc_id);
ALTER TABLE public.operadores_sucursales ADD CONSTRAINT operadores_sucursales_fk_1 FOREIGN KEY (ope_id) REFERENCES public.operadores(ope_id);