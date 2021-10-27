package com.kam.springboot3.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import javax.servlet.ServletContext;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.kam.springboot3.Springboot3Application;


//This uses mock mvc context.

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextConfiguration(classes = { Springboot3Application.class })
class ThirdControllerITTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup()
    {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    void testApplicationContext() {
        ServletContext servletContext = webApplicationContext.getServletContext();
        assertNotNull(servletContext);
        assertTrue(servletContext instanceof MockServletContext);
        assertNotNull(webApplicationContext.getBean("thirdController"));
    }

    @Test
    void getThirdControllerTest() throws Exception
    {
        MvcResult result = mockMvc.perform(get("/thirdController")).andDo(print()).andReturn();
        String expectedResponseBody = "Hello Third Service - third controller";
        assertEquals(result.getResponse().getStatus(), HttpStatus.OK.value());
        assertEquals(expectedResponseBody, result.getResponse().getContentAsString());

    }
}
