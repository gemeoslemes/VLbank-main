CREATE TABLE evento (
    nrocli bigint(20) NOT NULL AUTO_INCREMENT,
    saldo decimal(10, 2),
    valor_transferencia decimal(10, 2),
    limite decimal DEFAULT 200,
    nome varchar(100),
    cpf_cnpj VARCHAR(20),
    banco int DEFAULT 037,
    tipo_transferencia varchar(20),
    nrocli_conta bigint(20),
    PRIMARY KEY (nrocli),
    FOREIGN KEY (nrocli_conta) REFERENCES conta(nrocli)
);
