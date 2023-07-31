CREATE TABLE endereco(
nrocli bigint primary key auto_increment,
rua varchar(200) not null,
numero int not null,
complemento varchar(150),
UF varchar(30) not null,
CEP varchar(15) not null,
cidade varchar(50) not null,
nrocli_cliente bigint NOT NULL,
FOREIGN KEY (nrocli_cliente) REFERENCES cliente(nrocli)

);