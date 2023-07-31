CREATE TABLE seguranca (
  nrocli BIGINT(20) AUTO_INCREMENT PRIMARY KEY,
  agencia INT,
  numero_conta VARCHAR(20),
  dac TINYINT unsigned,
  senha VARCHAR(255),
  nrocli_cliente BIGINT(20),
  nrocli_conta BIGINT(20)
);
