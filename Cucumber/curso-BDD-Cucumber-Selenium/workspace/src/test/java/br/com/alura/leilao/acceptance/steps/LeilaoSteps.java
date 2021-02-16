package br.com.alura.leilao.acceptance.steps;

import br.com.alura.leilao.e2e.pages.Browser;
import br.com.alura.leilao.e2e.pages.LeiloesPage;
import br.com.alura.leilao.e2e.pages.LoginPage;
import br.com.alura.leilao.e2e.pages.NovoLeilaoPage;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import org.junit.Assert;

public class LeilaoSteps {

    private LoginPage loginPage;
    private LeiloesPage leiloesPage;
    private NovoLeilaoPage novoLeilaoPage;
    private Browser browser;

    @Dado("um usuario logado")
    public void um_usuario_logado() {
        Browser browser = new Browser();
        browser.seed();
        loginPage = new Browser().getLoginPage();
        leiloesPage = loginPage.realizaLoginComoFulano();
    }

    @Quando("acessa a pagina de leilao novamente")
    public void acessa_a_pagina_de_leilao_novamente() {
        novoLeilaoPage = this.leiloesPage.visitaPaginaParaCriarUmNovoLeilao();
    }

    @Quando("preenche o formulario com dados validos")
    public void preenche_o_formulario_com_dados_validos() {
        this.leiloesPage = this.novoLeilaoPage.preencheForm("PC", "1500", "13/01/2021");
    }

    @Entao("volta para a pagina de leiloes")
    public void volta_para_a_pagina_de_leiloes() {
        Assert.assertTrue(this.leiloesPage.estaNaPaginaDeLeiloes());
    }

    @Entao("o novo leilao aparece na tabela")
    public void o_novo_leilao_aparece_na_tabela() {
        this.leiloesPage.existe("PC", "1500","13/01/2021", "fulano");
        this.browser.clean();
    }
}
