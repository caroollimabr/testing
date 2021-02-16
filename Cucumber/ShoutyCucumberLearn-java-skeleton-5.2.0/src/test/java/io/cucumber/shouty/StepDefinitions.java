package io.cucumber.shouty;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

public class StepDefinitions {

    private Person sean;
    private Person lucy;
    private String messageFromSean;

    @Given("Lucy is located/standing {int} meter(s) from Sean") //Cucumber expression
    public void lucy_is_located_meters_from_Sean(Integer distance) {
        lucy = new Person("Lucy");
        sean = new Person("Sean");
        lucy.moveTo(distance);
    }

    @When("Sean shouts {string}")
    public void sean_shouts(String message) {
        sean.shout(message);
        messageFromSean = message;
    }

    @Then("Lucy hears Sean's message")
    public void lucy_hears_Sean_s_message() {
        assertEquals(asList(messageFromSean), lucy.getMessagesHeard());
    }
}
