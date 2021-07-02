BEGIN TRANSACTION;

CREATE TABLE IF NOT EXISTS public.paciente
(
    "id_paciente" serial NOT NULL,
    nome character varying,
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
    cre_enfermeiro bigint NOT NULL,
    id_paciente integer NOT NULL,
    procedencia integer,
    crm_medico bigint,
    dt_ac_medico date,
    dt_criacao date NOT NULL,
    status integer NOT NULL,
    id_sirs integer,
    id_dinsf_org integer,
    PRIMARY KEY (id_formulario)
);

CREATE TABLE IF NOT EXISTS public.formulario_sepse_medico
(
    id_formulario integer NOT NULL,
    id_paciente integer NOT NULL,
    crm_medico bigint,
    dt_criacao date NOT NULL,
    status integer NOT NULL,
    foco_infeccioso_id integer,
    crit_exclusao_id integer,
    bundle_id integer,
    reavaliacao_seriada_id integer,
    PRIMARY KEY (id_formulario)
);

CREATE TABLE IF NOT EXISTS public.formulario_sepse_enf2
(
    id_formulario integer NOT NULL,
    dt_uti timestamp without time zone,
    dt_alta timestamp without time zone,
    dt_obito timestamp without time zone,
    dt_criacao timestamp without time zone NOT NULL,
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

CREATE TABLE IF NOT EXISTS public.formulario_sepse_enf1_sirs
(
    id serial NOT NULL,
    febre_hipotemia boolean,
    leucocitose_leucopenia boolean,
    taquicardia boolean,
    taquipneia boolean,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.formulario_sepse_enf1_dinsf_org
(
    id serial NOT NULL,
    diurese boolean,
    hipotensao boolean,
    snlc_conf_agtc_coma boolean,
    saturacao_dispneia boolean,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.formulario_sepse_medico_foco_infeccioso
(
    id serial NOT NULL,
    pneumonia_empema boolean,
    infeccao_urinaria boolean,
    infeccao_abdominal boolean,
    menigite boolean,
    endocardite boolean,
    pele_partes_moles boolean,
    infeccao_protese boolean,
    infeccao_ossea boolean,
    infeccao_ferida_operatoria boolean,
    infeccao_sanguinea_cateter boolean,
    sem_foco_definido boolean,
    outras_infeccoes character varying,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.formulario_sepse_medico_criterio_exclusao
(
    id serial NOT NULL,
    apresenta_criterio_exclusao boolean,
    fim_de_vida boolean,
    doenca_atipica boolean,
    probabilidade_sepse_baixa boolean,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.formulario_sepse_medico_bundle
(
    id serial NOT NULL,
    iniciado boolean,
    dt_disparo timestamp without time zone,
    lacto_dt_coleta timestamp without time zone,
    hemocultura_dt_coleta timestamp without time zone,
    antibiotico_amplo_aspectro timestamp without time zone,
    cristaloides boolean,
    vasopressores boolean,
    justificativa_nao character varying,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.formulario_sepse_medico_reavaliacoes_seriadas
(
    id serial NOT NULL,
    q_sofa boolean,
    pas_100_mmghg boolean,
    fr_22_rpm boolean,
    rebaixamento_nivel_consiencia boolean,
    lacto_inicialmente_alto boolean,
    outros character varying,
    justificativa_nao character varying,
    aplicada boolean,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.usuario_login_log
(
    id character varying NOT NULL,
    id_usuario character varying NOT NULL,
    dt_login timestamp without time zone,
    dt_explicit_logout timestamp without time zone,
    role integer,
    status integer,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.usuario_acesso_log
(
    id_log character varying NOT NULL,
    id_usuario character varying NOT NULL,
    dt_requisicao timestamp without time zone NOT NULL,
    operation character varying,
    body_requisicao character varying,
    verbo_requisicao character varying,
    uri_requisicao character varying,
    status_response integer,
    body_response aclitem,
    PRIMARY KEY (id_log)
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


ALTER TABLE public.formulario_sepse_enf1
    ADD FOREIGN KEY (id_sirs)
        REFERENCES public.formulario_sepse_enf1_sirs (id)
        NOT VALID;


ALTER TABLE public.formulario_sepse_enf1
    ADD FOREIGN KEY (id_dinsf_org)
        REFERENCES public.formulario_sepse_enf1_dinsf_org (id)
        NOT VALID;


ALTER TABLE public.formulario_sepse_medico
    ADD FOREIGN KEY (foco_infeccioso_id)
        REFERENCES public.formulario_sepse_medico_foco_infeccioso (id)
        NOT VALID;

ALTER TABLE public.formulario_sepse_medico
    ADD FOREIGN KEY (crit_exclusao_id)
        REFERENCES public.formulario_sepse_medico_criterio_exclusao (id)
        NOT VALID;


ALTER TABLE public.formulario_sepse_medico
    ADD FOREIGN KEY (bundle_id)
        REFERENCES public.formulario_sepse_medico_bundle (id)
        NOT VALID;


ALTER TABLE public.formulario_sepse_medico
    ADD FOREIGN KEY (reavaliacao_seriada_id)
        REFERENCES public.formulario_sepse_medico_reavaliacoes_seriadas (id)
        NOT VALID;

ALTER TABLE public.usuario_login_log
    ADD FOREIGN KEY (id_usuario)
        REFERENCES public.usuario (cpf)
        NOT VALID;


ALTER TABLE public.usuario_acesso_log
    ADD FOREIGN KEY (id_usuario)
        REFERENCES public.usuario (cpf)
        NOT VALID;

COMMIT;