#language: pt
Funcionalidade: Alugar filme
  Como um usuário
  Eu quero cadastrar aluguéis de filmes
  Para controlar preços e datas de entrega

Cenário: Deve alugar um filme com sucesso
  Dado um filme
    | estoque | 2     |
    | preco   | 3     |
    | tipo    | comum |
  Quando eu alugar
  Então o preço de aluguel será R$3
  E a data de entrega será em 1 dias
  E o estoque do filme será 1 unidades

Esquema do Cenario: Deve dar condições conforme o tipo de aluguel
  Dado um filme com estoque de 2 unidades
  E o preço de aluguel seja R$<preco>
  E o tipo de aluguel seja "<tipo>"
  Quando eu alugar
  Então o preço de aluguel será R$<valor>
  E a data de entrega será em <qtdDias> dias
  E a pontuação recebida será de <pontuacao> pontos

  Exemplos:
  | preco | tipo      | valor | qtdDias | pontuacao |
  | 4     | estendido | 8     | 3       | 2         |
  | 4     | comum     | 4     | 1       | 1         |
  | 10    | estendido | 20    | 3       | 2         |
  | 5     | semanal   | 15    | 7       | 3         |

Cenário: Não deve alugar um filme sem estoque
Dado um filme com estoque de 0 unidades
Quando eu alugar
Então não será possível por falta de estoque
E o estoque do filme será 0 unidades