CREATE TABLE users (
	id bigserial not null,
	user_name varchar default null,
	fullname varchar default null,
	password varchar default null,
	account_non_expired bool default null,
	account_non_locked bool default null,
	credentials_non_expired bool default null,
	enabled bool default null,
	CONSTRAINT users_pkey PRIMARY KEY (id)
);