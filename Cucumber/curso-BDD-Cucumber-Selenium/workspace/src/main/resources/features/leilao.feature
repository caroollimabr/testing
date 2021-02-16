# language: pt
## BDD - End2End

@leilao
Funcionalidade: Cadastrando um leilao

  Cenario: Um usuario logado pode cadastrar um leilao
    Dado um usuario logado
    Quando acessa a pagina de leilao novamente
    E preenche o formulario com dados validos
    Entao volta para a pagina de leiloes
    E o novo leilao aparece na tabela