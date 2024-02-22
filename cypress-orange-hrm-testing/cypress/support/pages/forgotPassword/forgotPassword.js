const el = require('./elements').ELEMENTS;

class ForgotPassword {
    accessPageForgotPassword(){
        cy.visit('https://opensource-demo.orangehrmlive.com/web/index.php/auth/login');
        cy.contains('p', 'Forgot your password?').click();
    }

}

export default new ForgotPassword();