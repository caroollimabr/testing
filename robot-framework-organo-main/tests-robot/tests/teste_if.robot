*** Settings ***
Test Setup       Log To Console    ${\n}
Test Teardown    Log To Console    ${\n}

*** Variables ***
${NUMERO}   ${1}

*** Test Case ***
Exemplo bloco IF
    IF  ${NUMERO} > 0
        Mostrar mensagem quando o número for maior que zero
        Log  message= Você está vendo essa mensagem porque o bloco IF foi executado e sua condição é verdadeira  console=True
    END

*** Keywords ***
Mostrar mensagem quando o número for maior que zero
    Log   O número [${NUMERO}] é maior que zero!
    Log To Console    O número [${NUMERO}] é maior que zero!
