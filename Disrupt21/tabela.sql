drop sequence sq_pedido;
drop sequence sq_cliente;
drop table tb_pedido;
drop table tb_cliente;

create table tb_endereco(
	cep number primary key not null,
	logradouro varchar(100),
	numero varchar(10),
	complemento varchar(30),
	bairro varchar(40),
	cidade varchar(40),
	estado varchar(30),
	constraint fk_endereco foreign key (cep_endereco) references tb_endereco(cep)
);

create table tb_cliente(
	id number primary key not null,
	cpf varchar(14) not null,
	nome varchar(50) not null,
	email varchar (35) not null,
	telefone varchar(12) not null
);


create table tb_pedido (
	ide number primary key not null,
	id_cliente number not null,
	numeroPedido number(4,2) not null,
	quantidade number(2) not null,
	frete number(2,2) not null,
	totalPago number(5,2),
	constraint fk_cliente foreign key (id_cliente) references tb_cliente(id)
);

create sequence sq_pedido increment by 1 start with 1 nocycle order;