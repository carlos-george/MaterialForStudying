create table ordemservico (
	id  bigserial not null, 
	data_abertura timestamp, 
	data_finalizacao timestamp, 
	descricao varchar(255), 
	preco numeric(10, 2), 
	status varchar(20), 
	cliente_id int8, 
	primary key (id)
);
alter table ordemservico add constraint FKqerkb8u5oinbncan96j1kw9wi foreign key (cliente_id) references cliente;