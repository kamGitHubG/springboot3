package com.kam.springboot3.bdd.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ThirdControllerStepDef {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    ApplicationContext applicationContext;

    ResponseEntity<String> response;

    public ThirdControllerStepDef() {
        super();
    }

    @Given("^the application is bootstraped$")
    public void ensureApplicationIsBootStrapped()
    {
        assertNotNull(applicationContext.getBean("thirdController"));
    }

    @When("^I make a Get call$")
    public void makeCallToFirstController() throws MalformedURLException {
        response = testRestTemplate.getForEntity(
                new URL("http://localhost:" + port + "/thirdProject/thirdController").toString(), String.class);
    }

    @Then("^I should receive the expected output$")
    public void verifyReturnFromRestCall()
    {
        String expectedResponseBody = "Hello Third Service - third controller";

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedResponseBody, response.getBody());
    }
}
