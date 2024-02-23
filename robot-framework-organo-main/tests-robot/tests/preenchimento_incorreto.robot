*** Settings ***
Resource         ../resources/main.robot
Test Setup       Dado que o Organo for acessado
Test Teardown    Fechar o navegador

*** Variables ***
${BOTAO_CARD}             id:form-botao

*** Test Cases ***
Verificar se, quando um campo obrigatório não for preenchido corretamente, há uma mensagem de campo obrigatório
    Quando o botão Criar card for clicado
    Então deve haver uma mensagem de campo obrigatório
