select * from tb_categoria;
insert into tb_categoria (categoria) values ("tabuleiro"), ("RPG"), ("plataforma"), ("card game");
truncate tb_categoria;
drop table tb_categoria;
delete from tb_categoria where id between 1 and 12;

select * from tb_produto;
insert into tb_produto (nome_produto, preco, qtd_estoque, categoria_id) values ("Zelda", 450.00, 5, 2), ("Detetive", 100.99, 8, 1), ("Super Mario", 490.99, 3, 3), ("HeartStone", 65.49, 2, 4);