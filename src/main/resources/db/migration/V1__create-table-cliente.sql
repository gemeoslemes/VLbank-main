CREATE TABLE cliente (
  nrocli BIGINT PRIMARY KEY AUTO_INCREMENT,
  nome VARCHAR(255) NOT NULL,
  cpf_cnpj VARCHAR(20) NOT NULL,
  tipo_pessoa varchar(100) /*('FISICA', 'JURIDICA')*/ ,
  email VARCHAR(255) NOT NULL,
  telefone varchar(20) NOT NULL,
  data_nascimento DATE NOT NULL,
  senha VARCHAR(255) NOT NULL,
  confirmar_senha VARCHAR(255) NOT NULL
);

CREATE INDEX idx_cliente_senha ON cliente (senha);
