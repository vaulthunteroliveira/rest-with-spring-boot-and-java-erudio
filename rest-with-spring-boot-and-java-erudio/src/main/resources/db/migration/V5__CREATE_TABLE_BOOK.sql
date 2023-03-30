CREATE TABLE books (
	id bigserial not null,
	author text,
	launch_date date not null,
	price decimal(65,2) not null,
	title text,
	CONSTRAINT books_pkey PRIMARY KEY (id)
);