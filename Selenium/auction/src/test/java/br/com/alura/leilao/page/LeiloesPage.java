package br.com.alura.leilao.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LeiloesPage {

    public WebDriver browser;
    public static final String URL_CADASTRO_LEILAO = "http://localhost:8080/leiloes/new";

    public LeiloesPage(WebDriver browser){
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver\\chromedriver.exe");
        this.browser = browser;
    }

    public void fechar() {
        this.browser.quit();
    }

    public CadastroLeilaoPage carregaFormulario() {
        this.browser.navigate().to(URL_CADASTRO_LEILAO);
        return new CadastroLeilaoPage(browser);
    }
}
