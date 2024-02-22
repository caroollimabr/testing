describe('login', () => {

  beforeEach(() => {
    cy.visit('https://opensource-demo.orangehrmlive.com/web/index.php/auth/login');

//    cy.intercept('POST', 'https://opensource-demo.orangehrmlive.com/web/index.php/auth/validate', {
//      statusCode: 400
//    }).as('stubPost');

  })

  const users = require('../fixtures/users.json');
  users.forEach(user => {
    it('should log in successfully', () => {
      cy.login(user.username, user.password);
      cy.contains('h6', 'Dashboard').should('be.visible');
    });
    
  })

//    it('should not log in successfully due to error in backend response', () => {
//      cy.login('admin', 'admin123');
//      cy.wait('@stubPost');
//      cy.contains('p', 'Invalid credentials').should('be.visible');
//    });

    it('should show spans that say that username and password are required to log in', () => {
      cy.login(' ', ' ');
      cy.contains('span', 'Required').should('be.visible', 2);
    });

    it('should show the message that says that the credentials are invalid', () => { 
      cy.login('test', 'test');
      cy.contains('p', 'Invalid credentials').should('be.visible');
    });
})