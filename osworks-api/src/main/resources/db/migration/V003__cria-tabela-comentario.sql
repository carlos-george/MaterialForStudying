create table comentario (
	id  bigserial not null, 
	data_envio timestamp, 
	descricao varchar(255), 
	ordem_id int8, 
	primary key (id)
);

alter table comentario add constraint FKbjnuinqi4wk2g5mxceucwk055 foreign key (ordem_id) references ordemservico;