DELETE FROM tb_transfer;
DELETE FROM tb_wallet;




INSERT INTO tb_wallet (id, full_name, cpf_cnpj, email, password, balance, wallet_type_id)
VALUES (500L, 'Dean W', '123345', 'dean@email.com', '123456', 10, 1L);

INSERT INTO tb_wallet (id, full_name, cpf_cnpj, email, password, balance, wallet_type_id)
VALUES (501L, 'Samuel W', '678901', 'sam@email.com', '800900', 30, 1L);

INSERT INTO tb_wallet (id, full_name, cpf_cnpj, email, password, balance, wallet_type_id)
VALUES (502L, 'Castiel', '000001', 'cas@email.com', '123', 50, 2L);
