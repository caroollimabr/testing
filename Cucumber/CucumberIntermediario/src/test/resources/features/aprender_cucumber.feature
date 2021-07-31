# language: pt

Funcionalidade: Aprender Cucumber
  Como um aluno
  Eu quero aprender a utilizar Cucumber
  Para que eu possa automatizar critérios de aceitação

Cenário: : Deve executar especificação
  Dado que criei o arquivo corretamente
  Quando eu executá-lo
  Então a especificação deve finalizar com sucesso

@contador
Esquema do Cenário: Deve incrementar o contador
  Dado que o valor do contador é <contador>
  Quando eu incrementar em <incremento>
  Então o valor do contador será <contadorFinal>

  Exemplos:
    | contador | incremento | contadorFinal |
    | 15       | 3          | 18            |
    | 123      | 35         | 158           |

Esquema do Cenário: Deve calcular atraso na entrega
  Dado que a entrega é <entregaInicial>
  Quando a entrega atrasar <qteAtraso> "<tempo>"
  Então a entrega será efetuada em <entregaFinal>

  Exemplos:
  | entregaInicial | qteAtraso | tempo | entregaFinal |
  | 05/04/2021     | 2         | dias  | 07/04/2021   |
  | 05/04/2021     | 2         | meses | 05/06/2021   |


Esquema do Cenário: Deve criar steps genéricos para estes passos
  Dado que o ticket "<tipo>" é "<ticket>"
  E que o valor da passagem é R$ "<valorTicket>"
  E que o nome do passageiro é "<passageiro>"
  E que o telefone do passageiro é "<telefone>"
  Quando criar os steps
  Então o teste vai funcionar

  Exemplos:
  | ticket| tipo     |valorTicket| passageiro      | telefone |
  | AF345 |          | 230,45    | Fulano da Silva | 9999-9999|
  | AB167 | especial | 1120,23   | Sicrano de Tal  | 9888-8888|


Esquema do Cenário: Deve criar steps genéricos para estes passos
  Dado que o ticket "<tipo>" é "<ticketErrado>"
  E que o valor da passagem é R$ "<valorErrado>"
  E que o nome do passageiro é "<passageiroErrado>"
  E que o telefone do passageiro é "<telefoneErrado>"
  Quando criar os steps
  Então o teste não vai funcionar

  Exemplos:
  | ticketErrado| valorErrado | passageiroErrado                          | telefoneErrado |tipo|
  | CD123       | 1.1345,56   | Beltrano Souza Matos de Alcântara Azevedo | 1234-5678      |    |
  | AG1234      |             |                                           | 999-2223       |    |

