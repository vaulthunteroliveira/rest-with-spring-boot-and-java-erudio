CREATE TABLE medicos (
	id bigserial not null,
	nome varchar(100) NOT NULL,
	email varchar(100) NULL,
	crm varchar(6) NOT NULL,
	especialidade varchar(100) not null,
    logradouro varchar(100) not null,
    bairro varchar(100) not null,
    cep varchar(9) not null,
    complemento varchar(100),
    numero varchar(20),
    uf char(2) not null,
    cidade varchar (20) not null,
	CONSTRAINT medicos_pkey PRIMARY KEY (id)
);