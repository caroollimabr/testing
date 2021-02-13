package br.com.alura.leilao.page;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

//Padrão page object: Page é o lugar onde deve ficar a manipulação da página com Selenium
public class LoginPage {
    private WebDriver browser;
    private static final String URL_LOGIN = "http://localhost:8080/login";
    private static final String URL_LEILOES ="http://localhost:8080/leiloes/2";

    public LoginPage(){
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver\\chromedriver.exe");
        this.browser = new ChromeDriver();
        this.browser.navigate().to(URL_LOGIN);
    }

    public void fechar() {
        this.browser.quit();
    }

    public void preencheFormularioDeLogin(String username, String password) {
        browser.findElement(By.id("username")).sendKeys(username); //acessa e escreve (sendKeys)
        browser.findElement(By.id("password")).sendKeys(password);
    }

    public void submeteFormularioDeLogin() {
        browser.findElement(By.id("login-form")).submit(); //submete formulário, outra opcao é encontrar o botão + .click()
    }


    public LeiloesPage efetuaLogin(String username, String password) {
        this.preencheFormularioDeLogin(username, password);
        this.submeteFormularioDeLogin();
        return new LeiloesPage(browser);
    }

    public boolean isPaginaDeLogin() {
        return browser.getCurrentUrl().contains(URL_LOGIN); //é possível colocar .equals
    }

    public String getNomeUsuarioLogado() {
        try{
            return browser.findElement(By.id("usuario-logado")).getText();
        } catch (NoSuchElementException e){
            return null;
        }
    }

    public void clicaBotaoEnviar() {
        browser.findElement(By.id("botaoEnviar")).click(); //.submit irá submeter formulario pelo elemento do form
    }

    public void navegaParaPaginaDeLances() {
        browser.navigate().to(URL_LEILOES);
    }

    public boolean contemTexto(String texto) {
        return browser.getPageSource().contains(texto); //getPageSource() verifica se a pg tem a string
    }

    public LeiloesPage clicaBotaoEnviarLogin(){
        browser.findElement(By.id("botaoEnviar")).click();
        return new LeiloesPage(browser);
    }
}
