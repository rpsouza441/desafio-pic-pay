DELETE FROM tb_transfer;
DELETE FROM tb_wallet;
DELETE FROM tb_wallet_type;




INSERT INTO tb_wallet (id, full_name, cpf_cnpj, email, password, balance, wallet_type_id)
VALUES (1, 'Pedro S', '12345', 'pedro@email.com', '123123', 10, 1);

INSERT INTO tb_wallet (id, full_name, cpf_cnpj, email, password, balance, wallet_type_id)
VALUES (1, 'Maria R', '23456', 'maria@email.com', '123123', 30, 1);

INSERT INTO tb_wallet (id, full_name, cpf_cnpj, email, password, balance, wallet_type_id)
VALUES (1, 'Joao W', '34567', 'joao@email.com', '123123', 50, 2);
