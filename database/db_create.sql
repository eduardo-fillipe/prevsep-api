BEGIN TRANSACTION;

CREATE TABLE IF NOT EXISTS public.paciente
(
    "id_paciente" serial NOT NULL,
    idade integer,
    sexo character varying,
    leito character varying,
    nr_atendimento character varying,
    registro character varying,
    cpf character varying,
    PRIMARY KEY ("id_paciente")
);

CREATE TABLE IF NOT EXISTS public.formulario_sepse_enf1
(
    id_formulario serial NOT NULL,
    id_paciente integer NOT NULL,
    procedencia character varying,
    sirs character varying,
    disf_organica character varying,
    crm_medico bigint,
    dt_ac_medico date,
    cre_enfermeiro bigint NOT NULL,
    dt_criacao date NOT NULL,
    status integer NOT NULL,
    PRIMARY KEY (id_formulario)
);

CREATE TABLE IF NOT EXISTS public.formulario_sepse_medico
(
    id_formulario integer NOT NULL,
    id_paciente integer NOT NULL,
    foco_infeccioso character varying,
    crit_exclusao character varying,
    bundle_hora1 character varying,
    dt_disp_protocolo date,
    dt_coleta_lactato date,
    dt_coleta_hemocult date,
    dt_primeira_dose date,
    crm_medico bigint,
    dt_criacao date NOT NULL,
    status integer NOT NULL,
    reavaliacoes_seriadas character varying,
    PRIMARY KEY (id_formulario)
);

CREATE TABLE IF NOT EXISTS public.formulario_sepse_enf2
(
    id_formulario integer NOT NULL,
    dt_uti date,
    dt_alta date,
    dt_obito date,
    dt_criacao date NOT NULL,
    status integer NOT NULL,
    cre_enfermeiro bigint,
    PRIMARY KEY (id_formulario)
);

CREATE TABLE IF NOT EXISTS public.medico
(
    cpf character varying(11) NOT NULL,
    crm bigint NOT NULL UNIQUE,
    PRIMARY KEY (cpf)
);

CREATE TABLE IF NOT EXISTS public.enfermeiro
(
    cpf character varying NOT NULL,
    cre bigint NOT NULL UNIQUE,
    PRIMARY KEY (cpf)
);

CREATE TABLE IF NOT EXISTS public.gestor
(
    cpf character varying NOT NULL,
    PRIMARY KEY (cpf)
);

CREATE TABLE IF NOT EXISTS public.usuario
(
    cpf character varying(11) NOT NULL,
    email character varying(255) NOT NULL,
    cargo integer NOT NULL,
    senha character varying(255) NOT NULL,
    status integer,
    nome character varying,
    PRIMARY KEY (cpf)
);

ALTER TABLE public.formulario_sepse_enf1
    ADD FOREIGN KEY (id_paciente)
        REFERENCES public.paciente ("id_paciente")
        NOT VALID;


ALTER TABLE public.formulario_sepse_medico
    ADD FOREIGN KEY (id_paciente)
        REFERENCES public.paciente ("id_paciente")
        NOT VALID;


ALTER TABLE public.formulario_sepse_enf2
    ADD FOREIGN KEY (id_formulario)
        REFERENCES public.formulario_sepse_medico (id_formulario)
        NOT VALID;


ALTER TABLE public.enfermeiro
    ADD FOREIGN KEY (cpf)
        REFERENCES public.usuario (cpf)
        NOT VALID;


ALTER TABLE public.medico
    ADD FOREIGN KEY (cpf)
        REFERENCES public.usuario (cpf)
        NOT VALID;


ALTER TABLE public.gestor
    ADD FOREIGN KEY (cpf)
        REFERENCES public.usuario (cpf)
        NOT VALID;


ALTER TABLE public.formulario_sepse_medico
    ADD FOREIGN KEY (id_formulario)
        REFERENCES public.formulario_sepse_enf1 (id_formulario)
        NOT VALID;


ALTER TABLE public.formulario_sepse_enf2
    ADD FOREIGN KEY (id_formulario)
        REFERENCES public.formulario_sepse_medico (id_formulario)
        NOT VALID;


ALTER TABLE public.formulario_sepse_enf1
    ADD FOREIGN KEY (crm_medico)
        REFERENCES public.medico (crm)
        NOT VALID;


ALTER TABLE public.formulario_sepse_enf1
    ADD FOREIGN KEY (cre_enfermeiro)
        REFERENCES public.enfermeiro (cre)
        NOT VALID;


ALTER TABLE public.formulario_sepse_medico
    ADD FOREIGN KEY (crm_medico)
        REFERENCES public.medico (crm)
        NOT VALID;

COMMIT;