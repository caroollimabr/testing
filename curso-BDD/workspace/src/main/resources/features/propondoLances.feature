# language: pt
  ## BDD
Funcionalidade: Propondo lances
  Cenario: Propondo um unico lance valido
    Dado um lance valido
    Quando propoe ao leilao
    Entao o lance e aceito

  Cenario: Propondo varios lances validos
    Dado um lance de 10.0 reais do usuario "sicrano"
    E um lance de 15.0 reais do usuario "beltrano"
    Quando propoe varios lances ao leilao
    Entao os lances sao aceitos

  Esquema do Cenario: Propondo um lance invalido
    Dado um lance invalido de <valor> reais do usuario '<nomeUsuario>'
    Quando propoe ao leilao
    Entao o lance nao e aceito

  Exemplos:
    | valor | nomeUsuario |
    | 0     | fulano      |
    | -1    | sicrano     |

  Cenario: Propondo uma sequencia de lances
    Dado dois lances
      | valor | nomeUsuario |
      | 10.0  | sicrano     |
      | 15.0  | sicrano     |
    Quando propoe varios lances ao leilao
    Entao o segundo lance nao e aceito


