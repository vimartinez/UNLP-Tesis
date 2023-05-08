-- public.trazas definition
-- Drop table
-- DROP TABLE public.trazas;
CREATE TABLE public.trazas (
	case_id text NOT NULL,
	activity text NULL,
	"timestamp" timestamptz NULL,
	activityid text NULL,
	"@@index" int8 NULL,
	"@@case_index" int8 NULL,
	id int4 NOT NULL GENERATED ALWAYS AS IDENTITY
);