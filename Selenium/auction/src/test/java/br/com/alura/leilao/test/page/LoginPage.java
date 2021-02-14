package br.com.alura.leilao.test.page;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

public class LoginPage extends PageObject {

    private static final String URL_LOGIN = "http://localhost:8080/login";
    private static final String URL_LEILOES ="http://localhost:8080/leiloes/2";

    public LoginPage(){
        super(null);
        this.browser.navigate().to(URL_LOGIN);
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
}
