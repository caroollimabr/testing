#language: pt
Funcionalidade: Alugar filme
  Como um usuário
  Eu quero cadastrar aluguéis de filmes
  Para controlar preços e datas de entrega

Cenário: Deve alugar um filme com sucesso
  Dado um filme com estoque de 2 unidades
  E o preço de aluguel seja R$3
  Quando eu alugar
  Então o preço de aluguel será R$3
  E a data de entrega será no dia seguinte
  E o estoque do filme será 1 unidade

Cenário: Deve alugar um filme com sucesso
  Dado um filme com estoque de 2 unidades
  E o preço de aluguel seja R$4
  E o tipo de aluguel seja extendido
  Quando eu alugar
  Então o preço de aluguel será R$8
  E a data de entrega será em 3 dias
  E a pontuação recebida será de 2 pontos

Cenário: Não deve alugar um filme sem estoque
Dado um filme com estoque de 0 unidades
Quando eu alugar
Então não será possível por falta de estoque
E o estoque do filme será 0 unidades