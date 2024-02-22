
const el = require('./elements').ELEMENTS;

class Cadastro {
    acessarPaginaDeCadastro(){
        cy.visit('http://localhost:4200');
        cy.get(el.registerNow).click();
    }

    preencherFormulario(){
        cy.get(el.email).type('catarina@1235jh.com.br');
        cy.get(el.fullName).type('Catarina Bela');
        cy.get(el.registerUserName).type('catarina');
        cy.get(el.registerPassword).type('catarina123456');
    }

    submeterCadastro(){
        cy.get(el.btnRegister).click();
    }
}

    
export default new Cadastro();