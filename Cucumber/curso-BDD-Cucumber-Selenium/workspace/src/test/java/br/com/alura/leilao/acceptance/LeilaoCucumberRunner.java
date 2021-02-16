
package br.com.alura.leilao.acceptance;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = "classpath:features", tags = "@leilao") //https://cucumber.io/docs/cucumber/api/#tags
public class LeilaoCucumberRunner {

}
