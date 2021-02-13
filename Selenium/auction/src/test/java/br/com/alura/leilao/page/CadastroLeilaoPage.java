package br.com.alura.leilao.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CadastroLeilaoPage {

    private WebDriver browser;
    private static final String URL_CADASTRO_LEILAO = "http://localhost:8080/leiloes/new";

    public CadastroLeilaoPage(WebDriver browser){
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver\\chromedriver.exe");
        this.browser = browser;
    }

    public void fechar() {
        this.browser.quit();
    }

    public LeiloesPage cadastrarLeilao(String nome, String valorInicial, String dataAbertura) {
        this.browser.findElement(By.id("nome")).sendKeys(nome);
        this.browser.findElement(By.id("valorInicial")).sendKeys(valorInicial);
        this.browser.findElement(By.id("dataAbertura")).sendKeys(dataAbertura);
        this.browser.findElement(By.id("button-submit")).click();

        return new LeiloesPage(browser);

    }

    public boolean isPaginaAtual() {
        return this.browser.getCurrentUrl().equals(URL_CADASTRO_LEILAO);
    }
}
