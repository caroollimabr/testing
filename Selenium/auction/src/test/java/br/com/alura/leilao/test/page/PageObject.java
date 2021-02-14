package br.com.alura.leilao.test.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

// Padrão page object: Page é o lugar onde deve ficar a manipulação da página com Selenium
// https://www.selenium.dev/documentation/en/guidelines_and_recommendations/page_object_models/
public class PageObject {
    public WebDriver browser;

    public PageObject(WebDriver browser){
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver\\chromedriver.exe");
        if (browser == null){ // se não tem navegador aberto
            this.browser = new ChromeDriver(); //crie uma nova instância de navegador (a fim de abri-lo)
        } else {
            this.browser = browser;
        }
        this.browser.manage().timeouts()
                .implicitlyWait(5, TimeUnit.SECONDS) // espera 5 seg ate dar erro (mt usado em apps com req ajax)
                .pageLoadTimeout(10, TimeUnit.SECONDS);

    }

    public void fechar() {
        this.browser.quit();
    }

}
