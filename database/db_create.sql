BEGIN TRANSACTION;

CREATE TABLE IF NOT EXISTS public.paciente
(
    "idPaciente" integer NOT NULL,
    idade integer,
    sexo character varying,
    leito character varying,
    "nrAtendimento" character varying,
    registro character varying,
    cpf character varying,
    PRIMARY KEY ("idPaciente")
);

CREATE TABLE IF NOT EXISTS public."formularioSepseEnf1"
(
    "idFormulario" integer NOT NULL,
    "idPaciente" integer NOT NULL,
    procedencia character varying,
    sirs character varying,
    "disfOrganica" character varying,
    "crmMedico" bigint,
    "dtAcMedico" date,
    "creEnfermeiro" bigint NOT NULL,
    "dtCriacao" date NOT NULL,
    status integer NOT NULL,
    PRIMARY KEY ("idFormulario")
);

CREATE TABLE IF NOT EXISTS public."formularioSepseMedico"
(
    "idFormulario" integer NOT NULL,
    "idPaciente" integer NOT NULL,
    "focoInfeccioso" character varying,
    "critExclusao" character varying,
    "bundleHora1" character varying,
    "dtDispProtocolo" date,
    "dtColetaLactato" date,
    "dtColetaHemocult" date,
    "dtPrimeiraDose" date,
    "crmMedico" bigint,
    "dtCriacao" date NOT NULL,
    status integer NOT NULL,
    "reavaliacoesSeriadas" character varying,
    PRIMARY KEY ("idFormulario")
);

CREATE TABLE IF NOT EXISTS public."formularioSepseEnf2"
(
    "idFormulario" integer NOT NULL,
    "dtUTI" date,
    "dtAlta" date,
    "dtObito" date,
    "dtCriacao" date NOT NULL,
    status integer NOT NULL,
    "creEnfermeiro" bigint,
    PRIMARY KEY ("idFormulario")
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

ALTER TABLE public."formularioSepseEnf1"
    ADD FOREIGN KEY ("idPaciente")
        REFERENCES public.paciente ("idPaciente")
        NOT VALID;


ALTER TABLE public."formularioSepseMedico"
    ADD FOREIGN KEY ("idPaciente")
        REFERENCES public.paciente ("idPaciente")
        NOT VALID;


ALTER TABLE public."formularioSepseEnf2"
    ADD FOREIGN KEY ("idFormulario")
        REFERENCES public."formularioSepseMedico" ("idFormulario")
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


ALTER TABLE public."formularioSepseMedico"
    ADD FOREIGN KEY ("idFormulario")
        REFERENCES public."formularioSepseEnf1" ("idFormulario")
        NOT VALID;


ALTER TABLE public."formularioSepseEnf2"
    ADD FOREIGN KEY ("idFormulario")
        REFERENCES public."formularioSepseMedico" ("idFormulario")
        NOT VALID;


ALTER TABLE public."formularioSepseEnf1"
    ADD FOREIGN KEY ("crmMedico")
        REFERENCES public.medico (crm)
        NOT VALID;


ALTER TABLE public."formularioSepseEnf1"
    ADD FOREIGN KEY ("creEnfermeiro")
        REFERENCES public.enfermeiro (cre)
        NOT VALID;


ALTER TABLE public."formularioSepseMedico"
    ADD FOREIGN KEY ("crmMedico")
        REFERENCES public.medico (crm)
        NOT VALID;


ALTER TABLE public."formularioSepseEnf2"
    ADD FOREIGN KEY ("creEnfermeiro")
        REFERENCES public."enfermeiro" ("cre")
        NOT VALID;

COMMIT;