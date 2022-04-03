CREATE TABLE IF NOT EXISTS faculdade
(
    id bigint NOT NULL,
    nome character varying(50),
    CONSTRAINT faculdade_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS professor
(
    id bigint NOT NULL,
    nome character varying(50),
    CONSTRAINT professor_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS disciplina
(
    id bigint NOT NULL,
    alteracao timestamp,
    cargahorariapratica integer NOT NULL,
    cargahorariateorica integer NOT NULL,
    cargahorariatotal integer NOT NULL,
    codigo integer NOT NULL,
    criacao timestamp,
    descricao character varying(50),
    quantidadealunos integer NOT NULL,
    usuario character varying(50),
    professor_id bigint,
    CONSTRAINT disciplina_pkey PRIMARY KEY (id),
    CONSTRAINT fkoqie7f1kx32b1atco9fpgxd7g FOREIGN KEY (professor_id)
        REFERENCES professor (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

CREATE TABLE IF NOT EXISTS curso
(
    id bigint NOT NULL,
    alteracao timestamp,
    codigo integer NOT NULL,
    criacao timestamp,
    descricao character varying(50),
    usuario character varying(50),
    faculdade_id bigint,
    CONSTRAINT curso_pkey PRIMARY KEY (id),
    CONSTRAINT fk9wy8em0m4mum542g7ik1qy7dh FOREIGN KEY (faculdade_id)
        REFERENCES faculdade (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

CREATE TABLE IF NOT EXISTS aluno
(
    id bigint NOT NULL,
    nome character varying(50),
    curso_id bigint,
    CONSTRAINT aluno_pkey PRIMARY KEY (id),
    CONSTRAINT fk9o09o8qj4x9uf9okvf622jyec FOREIGN KEY (curso_id)
        REFERENCES curso (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

CREATE TABLE IF NOT EXISTS curso_disciplinas
(
    cursos_id bigint NOT NULL,
    disciplinas_id bigint NOT NULL,
    CONSTRAINT fkevhx0g5kl3xss8i5xbkqi7v21 FOREIGN KEY (disciplinas_id)
        REFERENCES disciplina (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fkqf3txe0g9w8baempfnipysqd5 FOREIGN KEY (cursos_id)
        REFERENCES curso (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

CREATE TABLE IF NOT EXISTS disciplina_alunos
(
    disciplinas_id bigint NOT NULL,
    alunos_id bigint NOT NULL,
    CONSTRAINT fk3u0wskpr4r1k93d4ehrkm1498 FOREIGN KEY (disciplinas_id)
        REFERENCES disciplina (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fknifqbw1efwflt88xwao0j0owd FOREIGN KEY (alunos_id)
        REFERENCES aluno (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

