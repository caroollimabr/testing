package br.com.alura.leilao.test.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LeiloesPage extends PageObject {

    public static final String URL_CADASTRO_LEILAO = "http://localhost:8080/leiloes/new";
    private static final String URL_LEILOES = "http://localhost:8080/leiloes";

    public LeiloesPage(WebDriver browser){
        super(browser);
    }

    public CadastroLeilaoPage carregaFormulario() {
        this.browser.navigate().to(URL_CADASTRO_LEILAO);
        return new CadastroLeilaoPage(browser);
    }

    //novo item da tabela
    public boolean isLeilaoCadastrado(String nome, String valorInicial, String dataAbertura) {
        WebElement ultimaLinhaTabela =
                this.browser.findElement(By.cssSelector("#tabelaLeiloes tbody tr:last-child")); // faz todo o caminho. Last-child vai ser a última linha criada da tabela
        WebElement colunaNome = ultimaLinhaTabela.findElement(By.cssSelector("td:nth-child(1)"));
        WebElement colunaDataAbertura = ultimaLinhaTabela.findElement(By.cssSelector("td:nth-child(2)"));
        WebElement colunaValorInicial = ultimaLinhaTabela.findElement(By.cssSelector("td:nth-child(3)"));

        return colunaNome.getText().equals(nome)
                && colunaDataAbertura.getText().equals(dataAbertura)
                && colunaValorInicial.getText().equals(valorInicial);
    }

    public boolean isPaginaAtual() {
        return this.browser.getCurrentUrl().equals(URL_LEILOES);
    }
}
