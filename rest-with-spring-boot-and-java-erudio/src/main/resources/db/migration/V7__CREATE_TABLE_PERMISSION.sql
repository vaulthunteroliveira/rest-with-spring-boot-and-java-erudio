CREATE TABLE permission (
	id bigserial not null,
	description varchar default null,
	CONSTRAINT permission_pkey PRIMARY KEY (id)
);