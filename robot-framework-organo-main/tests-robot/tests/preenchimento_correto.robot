*** Settings ***
Resource         ../resources/main.robot
Test Setup       Dado que o Organo for acessado
Test Teardown    Fechar o navegador

*** Test Cases ***
Verificar se, ao preencher os campos do formulário corretamente, os dados são inseridos na lista e se um novo card é criado no time esperado
    Quando os campos do formulário forem preenchidos corretamente
    E o botão Criar card for clicado
    Então haverá um card no time esperado

Verificar se é possível criar mais de um card se os campos forem preenchidos corretamente
    Quando os campos do formulário forem preenchidos corretamente
    E o botão Criar card for clicado
    Então haverá mais de um card no time esperado

Verificar se é possível criar um card para cada time disponível se os campos forem preenchidos corretamente
    Quando os campos do formulário forem preenchidos corretamente
    Então haverá um card em cada time

