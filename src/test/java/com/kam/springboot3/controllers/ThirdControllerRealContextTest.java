package com.kam.springboot3.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.net.URL;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


//@SpringBootTest creates real application context.

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class ThirdControllerRealContextTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    ApplicationContext applicationContext;

    @Test
    void testApplicationContext() {
        assertNotNull(applicationContext);
        assertNotNull(applicationContext.getBean("thirdController"));
    }

    @Test
    void getSecondControllerTest() throws Exception
    {
        ResponseEntity<String> response = testRestTemplate.getForEntity(
                new URL("http://localhost:" + port + "/thirdProject/thirdController").toString(), String.class);

        String expectedResponseBody = "Hello Third Service - third controller";

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedResponseBody, response.getBody());
    }
}
