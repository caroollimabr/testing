package br.com.alura.leilao.test;

import br.com.alura.leilao.test.page.LoginPage;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

//pagina para testes apenas, sem Selenium
public class LoginTest extends LoginPage {

    private LoginPage paginaDeLogin;

    @BeforeEach
    public void beforeEach(){
        this.paginaDeLogin = new LoginPage();
    }

    @AfterEach
    public void afterEach(){
        this.paginaDeLogin.fechar();
    }

    @Test
    public void fazLoginDadosValidos(){
        paginaDeLogin.preencheFormularioDeLogin("fulano", "pass");
        paginaDeLogin.submeteFormularioDeLogin();

        Assert.assertFalse(paginaDeLogin.isPaginaDeLogin());
        Assert.assertEquals("fulano", paginaDeLogin.getNomeUsuarioLogado());
        Assert.assertTrue(paginaDeLogin.contemTexto("Leilões cadastrados"));

    }

    @Test
    public void naoFazLoginDadosInvalidos(){
        paginaDeLogin.preencheFormularioDeLogin("invalido", "123456");
        paginaDeLogin.clicaBotaoEnviar();

        Assert.assertTrue(paginaDeLogin.isPaginaDeLogin());
        Assert.assertTrue(paginaDeLogin.contemTexto("Usuário e senha inválidos."));
        Assert.assertNull(paginaDeLogin.getNomeUsuarioLogado()); //nesse caso, o elemento não vai existir
    }

    @Test
    public void naoAcessaPaginaRestritaSemLogin(){
        paginaDeLogin.navegaParaPaginaDeLances();

        Assert.assertTrue(paginaDeLogin.isPaginaDeLogin());
        Assert.assertFalse(paginaDeLogin.contemTexto("Dados do Leilão"));
    }

}


