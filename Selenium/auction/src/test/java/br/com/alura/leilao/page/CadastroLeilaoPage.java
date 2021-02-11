package br.com.alura.leilao.page;

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

}
