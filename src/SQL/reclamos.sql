CREATE TABLE public.reclamos (
	id int4 NOT NULL GENERATED ALWAYS AS IDENTITY,
	case_id varchar NOT NULL,
	rec_fecha timestamp NOT NULL,
	rec_operador varchar NOT NULL,
	rec_tipo varchar NULL
);

