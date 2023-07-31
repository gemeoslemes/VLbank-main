CREATE EVENT atualizar_saldo
ON SCHEDULE
    EVERY 1 MONTH
STARTS '2023-06-01 00:00:00'
DO
    UPDATE evento
    SET saldo = saldo + 5000;
