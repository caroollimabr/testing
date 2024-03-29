#language: pt
Funcionalidade: Cadastro de contas

  Como um usuário
  Gostaria de cadastrar contas
  Para que eu possa distribuir meu dinheiro de uma forma mais organizada

#  Contexto: #cenário declarativo (melhor legibilidade do que se quer validar)
#    Dado que desejo adicionar uma conta
#
#  Esquema do Cenário: Deve validar regras do cadastro de contas
#    Quando adiciono a conta "<conta>"
#    Então recebo a mensagem "<mensagem>"

# Cenários imperativos (mais detalhados)
  Contexto: #background-en
    Dado que estou acessando a aplicação
    Quando informo o usuário "carol@carol"
    E a senha "123456"
    E seleciono entrar
    Então visualizo a página inicial
    Quando seleciono Contas
    E seleciono Adicionar

  Esquema do Cenário: Deve validar regras do cadastro de contas
    Quando informo a conta "<conta>"
    E seleciono Salvar
    Então recebo a mensagem "<mensagem>"

    Exemplos:
    | conta            | mensagem                          |
    | Conta de Teste   | Conta adicionada com sucesso!     |
    |                  | Informe o nome da conta           |
    | Conta mesmo nome | Já existe uma conta com esse nome!|
