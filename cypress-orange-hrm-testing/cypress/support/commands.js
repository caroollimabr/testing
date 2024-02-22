Cypress.Commands.add('login', (user, pass) => { 
    cy.get('[name="username"]').type(user);
    cy.get('[name="password"]').type(pass);
    cy.contains('button', 'Login').click();
})

Cypress.Commands.add('resetPass', (user) => { 
    cy.get('[name="username"]').type(user);
    cy.contains('button', 'Reset Password').click();

})

//
//
// -- This is a child command --
// Cypress.Commands.add('drag', { prevSubject: 'element'}, (subject, options) => { ... })
//
//
// -- This is a dual command --
// Cypress.Commands.add('dismiss', { prevSubject: 'optional'}, (subject, options) => { ... })
//
//
// -- This will overwrite an existing command --
// Cypress.Commands.overwrite('visit', (originalFn, url, options) => { ... })