CREATE TABLE conta (
    nrocli bigint(20) NOT NULL AUTO_INCREMENT,
    agencia int DEFAULT 7199,
    dac tinyint unsigned NOT NULL,
    banco int DEFAULT 25,
    numero_conta VARCHAR(20),
    status boolean,
    tipo varchar(5),
    nrocli_cliente bigint(20) NOT NULL,
    PRIMARY KEY (nrocli),
    FOREIGN KEY (nrocli_cliente) REFERENCES cliente(nrocli)
);

CREATE INDEX idx_conta_agencia_numero_dac ON conta (agencia, numero_conta, dac);
