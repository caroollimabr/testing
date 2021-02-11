package br.com.alura.leilao.test;

import br.com.alura.leilao.page.CadastroLeilaoPage;
import br.com.alura.leilao.page.LeiloesPage;
import br.com.alura.leilao.page.LoginPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

public class LeiloesTest extends LeiloesPage {

    public LeiloesPage paginaDeLeiloes;

    public LeiloesTest(WebDriver browser) {
        super(browser);
    }

    @BeforeEach
    public void beforeEach(){
        this.paginaDeLeiloes = new LeiloesPage(browser);
    }

    @AfterEach
    public void afterEach(){
        this.paginaDeLeiloes.fechar();
    }

    @Test
    public void cadastraLeilao(){
        LoginPage paginaDeLogin = new LoginPage();
        paginaDeLogin.preencheFormularioDeLogin("fulano", "pass");
        this.paginaDeLeiloes = paginaDeLogin.clicaBotaoEnviarLogin();
        CadastroLeilaoPage paginaCadastroLeilao = paginaDeLeiloes.carregaFormulario();



    }
}
