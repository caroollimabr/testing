package br.com.alura.leilao.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class LeiloesPage {

    public WebDriver browser;
    public static final String URL_CADASTRO_LEILAO = "http://localhost:8080/leiloes/new";

    public LeiloesPage(WebDriver browser){
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver\\chromedriver.exe");
        this.browser = new ChromeDriver();
        this.browser = browser;
    }

    public void fechar() {
        this.browser.quit();
    }

    public CadastroLeilaoPage carregaFormulario() {
        this.browser.navigate().to(URL_CADASTRO_LEILAO);
        return new CadastroLeilaoPage(browser);
    }

    //novo item da tabela
    public boolean isLeilaoCadastrado(String nome, String valor, String data) {
        WebElement ultimaLinhaTabela =
                this.browser.findElement(By.cssSelector("#tabelaLeiloes tbody tr:last-child")); // faz todo o caminho. Last child vai ser a última linha criada da tabela
        WebElement colunaNome = ultimaLinhaTabela.findElement(By.cssSelector("td:nth-child(1)"));
        WebElement colunaDataAbertura = ultimaLinhaTabela.findElement(By.cssSelector("td:nth-child(2)"));
        WebElement colunaValorInicial = ultimaLinhaTabela.findElement(By.cssSelector("td:nth-child(3)"));

        return colunaNome.getText().equals(nome)
                && colunaDataAbertura.getText().equals(data)
                && colunaValorInicial.getText().equals(valor);
    }
}
