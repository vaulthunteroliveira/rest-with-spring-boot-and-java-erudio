CREATE TABLE person (
	id bigserial not null,
	firstname varchar(255) NOT NULL,
	lastname varchar(255) NULL,
	address varchar(255) NULL,
	gender varchar(6) NOT NULL,
	arrombado bool NULL,
	CONSTRAINT person_pkey PRIMARY KEY (id)
);