import ForgotPassword from '../support/pages/forgotPassword/forgotPassword'

describe('forgot password', () => {
  
  beforeEach(() => {
    ForgotPassword.accessPageForgotPassword();
  })

    it('should send the reset link successfully', () => {
      cy.resetPass('admin');
      cy.contains('h6', 'Reset Password link sent successfully').should('be.visible');
    });

    it('should show span that says that username is required to reset password', () => {
      cy.resetPass(' ');
      cy.contains('span', 'Required').should('be.visible');
    });
})