*** Settings ***
Documentation   Exemplos do uso de IF nos testes
Test Setup      Log To Console    ${\n}
Test Teardown   Log To Console    ${\n}

*** Variables ***
${NUMERO}   ${0}

*** Test Case ***
Exemplo com dois blocos IF e ELSE
    IF  ${NUMERO} > 0
        Mostrar mensagem quando o número for maior que zero
        Log  message= Você está vendo essa mensagem porque o bloco IF corresponde à condição verdadeira  console=True
    ELSE
        Mostrar mensagem quando o número for menor ou igual a zero
        Log  message= Você está vendo essa mensagem porque o bloco ELSE corresponde à condição falsa  console=True
    END

*** Keywords ***
Mostrar mensagem quando o número for maior que zero
    Log   O número [${NUMERO}] é maior que zero!
    Log To Console    O número [${NUMERO}] é maior que zero!

Mostrar mensagem quando o número for menor que zero
    Log   O número [${NUMERO}] é menor que zero!
    Log To Console    O número [${NUMERO}] é menor que zero!

Mostrar mensagem quando o número for menor ou igual a zero
    Log   O número [${NUMERO}] é menor ou igual a zero!
    Log To Console    O número [${NUMERO}] é menor ou igual a zero!