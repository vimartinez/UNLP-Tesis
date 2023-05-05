-- public.trazasajustebajo definition
-- Drop table
-- DROP TABLE public.trazasajustebajo;
CREATE TABLE public.trazasajustebajo (
	case_id text NOT NULL,
	"timestamp" timestamptz NULL,
	"@@index" int8 NULL,
	"@@case_index" int8 NULL,
	trace_fitness float8 NULL,
	resuelta bool NULL DEFAULT false,
	CONSTRAINT trazasajustebajo_pk PRIMARY KEY (case_id),
	CONSTRAINT trazasajustebajo_fk FOREIGN KEY (case_id) REFERENCES public.trazas(case_id)
);
