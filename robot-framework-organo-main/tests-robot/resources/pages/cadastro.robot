*** Settings ***
Resource    ../main.robot

*** Variables ***
${CAMPO_NOME}             id:form-nome
${CAMPO_CARGO}            id:form-cargo
${CAMPO_IMAGEM}           id:form-imagem
${CAMPO_TIME}             class:lista-suspensa
${BOTAO_CARD}             id:form-botao

@{selecionar_times}
...      //option[contains(.,'Programação')]
...            //option[contains(.,'Front-End')]
...            //option[contains(.,'Data Science')]
...           //option[contains(.,'Devops')] 
...               //option[contains(.,'UX e Design')]
...           //option[contains(.,'Mobile')]
...         //option[contains(.,'Inovação e Gestão')]

*** Keywords ***
Quando os campos do formulário forem preenchidos corretamente
    ${Nome}          FakerLibrary.First Name
    Input Text       ${CAMPO_NOME}      ${Nome}
    ${Cargo}         FakerLibrary.Job
    Input Text       ${CAMPO_CARGO}     ${Cargo}
    ${Imagem}        FakerLibrary.Image Url    width=100    height=100
    Input Text       ${CAMPO_IMAGEM}    ${Imagem}
    Click Element    ${CAMPO_TIME}
    Click Element    ${selecionar_times}[0]
E o botão Criar card for clicado
    Click Element    ${BOTAO_CARD}
Quando o botão Criar card for clicado
    Click Element    ${BOTAO_CARD}
Então haverá um card no time esperado
    Element Should Be Visible    class:colaborador
    Sleep    5s
Então haverá mais de um card no time esperado
    FOR    ${i}    IN RANGE    1    3
        Log    ${i}
        Quando os campos do formulário forem preenchidos corretamente
        E o botão Criar card for clicado
    END
    Sleep    10s
Então haverá um card em cada time
    FOR    ${indice}    ${time}    IN ENUMERATE    @{selecionar_times}
        Log    ${indice}: ${time}
        Quando os campos do formulário forem preenchidos corretamente
        Click Element    ${time}
        E o botão Criar card for clicado
    END
    Sleep    10s
Então deve haver uma mensagem de campo obrigatório
    Element Should Be Visible    id:form-nome-erro
    Element Should Be Visible    id:form-cargo-erro
    Element Should Be Visible    id:form-times-erro