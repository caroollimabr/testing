import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features/alugar_filme.feature",
        glue = "br.com.carol.cucumber.steps",
        //tags = "@contador", // executa apenas os testes com essa tag
        plugin = {"pretty", "html:target/report.html", "json:target/report.json"},  // 2 e 3 criam um relatorio de exec
        //monochrome = true,
        //dryRun = true (valida que os passos estão lá, + rapido antes de rodar um teste grande),
        //strict = true,
        snippets = CucumberOptions.SnippetType.CAMELCASE
        )

public class RunnerTest {


}

