# language: pt
  ## End2End

Funcionalidade: Apenas usuarios cadastrados podem se logar

  Cenario: Um usuario valido consegue se logar
    Dado o usuario
    Quando realiza login sendo valido
    Entao é redirecionado para a pagina de leiloes


  Cenario: Um usuario invalido não consegue se logar
    Dado o usuario
    Quando tenta se logar sendo invalido
    Entao continua na página de login