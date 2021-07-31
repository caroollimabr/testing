import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features/alugar_filme.feature",
        glue = "br.com.carol.cucumber.steps",
        //tags = "@contador", // executa apenas os testes com essa tag
        plugin = "pretty",
        snippets = CucumberOptions.SnippetType.CAMELCASE
        ) // , monochrome = true, dryRun = true (valida que os passos estão lá), strict = true

public class Runner {


}

