create table Cliente(
 email varchar(50) primary key,
 nome varchar(50) not null,
 estado varchar(50) not null,
 cidade varchar(50) not null,
 bairro varchar(50) not null,
 rua varchar(50) not null,
 numero int not null,
 complemento varchar(50) not null
)
select * from Cliente