describe('API', () => {
  
      it('should return 200 when validate is called', () => {
        cy.request({
            method: 'POST',
            url: 'https://opensource-demo.orangehrmlive.com/web/index.php/auth/validate',
            body: Cypress.env()
        }).then((res) => {
            expect(res.status).to.be.equal(200);
            expect(res.body).is.not.empty;
            expect(res.body).to.have.property('id');
            expect(res.body.id).to.be.equal(1);
        })
      });

      it('should return 200 when action-summary is called', () => {

        cy.request({
            method: 'POST',
            url: 'https://opensource-demo.orangehrmlive.com/web/index.php/api/v2/dashboard/employees/action-summary',
            body: Cypress.env()
        }).then((res) => {
            expect(res.status).to.be.equal(200);
            expect(res.body).is.not.empty;
            expect(res.body.data[0]).to.have.property('id');
            expect(res.body.data[0].id).to.be.equal(1);
            expect(res.body.data[0].group).to.be.equal('Leave Requests To Approve');
            expect(res.body.data[0].pendingActionCount).to.be.equal(3);
        })
      });
      
      
  })