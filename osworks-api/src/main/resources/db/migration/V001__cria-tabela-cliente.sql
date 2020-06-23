CREATE TABLE public.cliente
(
    id bigint NOT NULL,
    email character varying(255) COLLATE pg_catalog."default",
    nome character varying(255) COLLATE pg_catalog."default",
    telefone character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT cliente_pkey PRIMARY KEY (id)
);