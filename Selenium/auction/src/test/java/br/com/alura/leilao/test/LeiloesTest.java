package br.com.alura.leilao.test;

import br.com.alura.leilao.page.CadastroLeilaoPage;
import br.com.alura.leilao.page.LeiloesPage;
import br.com.alura.leilao.page.LoginPage;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LeiloesTest extends LeiloesPage {

    private LeiloesPage paginaLeiloes;
    private CadastroLeilaoPage paginaCadastroLeilao;

    public LeiloesTest(WebDriver browser) {
        super(browser);
    }

    @BeforeEach
    public void beforeEach(){
        LoginPage paginaDeLogin = new LoginPage();
        this.paginaLeiloes = paginaDeLogin.efetuaLogin("fulano", "pass");
        this.paginaCadastroLeilao = paginaLeiloes.carregaFormulario();
    }

    @AfterEach
    public void afterEach(){
        this.paginaLeiloes.fechar();
        this.paginaCadastroLeilao.fechar();
    }

    @Test
    public void cadastraLeilao(){
        String hoje = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        String nomeLeilao = "Leilao do dia " + hoje;
        String valorInicial = "500.00";

        this.paginaLeiloes = paginaCadastroLeilao.cadastrarLeilao(nomeLeilao, valorInicial, hoje);

        Assert.assertTrue(paginaLeiloes.isLeilaoCadastrado(nomeLeilao, valorInicial, hoje));


    }
}
