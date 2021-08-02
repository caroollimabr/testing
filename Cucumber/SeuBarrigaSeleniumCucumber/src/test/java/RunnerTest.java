import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src\\test\\resources\\cadastro_contas.feature",
        glue = "br.com.carol.test.seubarriga.steps",
        //tags = "@este",
        plugin = {"pretty", "html:target/report.html", "json:target/report.json"},
        monochrome = false,
        snippets = CucumberOptions.SnippetType.CAMELCASE
        //dryRun = true // verifica os passos, ajuda com os testes funcionais, que demoram p rodar
)

public class RunnerTest {
    @BeforeClass
    public static void reset(){
        // para deletar as contas de teste e não dar "falso negativo" no resultado do teste
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://seubarriga.wcaquino.me/login");
        driver.findElement(By.id("email")).sendKeys("carol@carol");
        driver.findElement(By.name("senha")).sendKeys("123456");
        driver.findElement(By.tagName("button")).click();
        driver.findElement(By.linkText("reset")).click();
        driver.quit();
    }
}
