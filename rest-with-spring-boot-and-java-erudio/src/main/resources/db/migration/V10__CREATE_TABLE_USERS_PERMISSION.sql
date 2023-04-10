create table user_permission (
	id_user int references users(id),
	id_permission int references permission(id),
	constraint user_permission_pkey primary key (id_user, id_permission)
)