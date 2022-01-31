package com.example1.consumeapi;


import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Description;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.util.AssertionErrors.assertTrue;

@ContextConfiguration
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ConsumeapiApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ConsumeapiApplicationTests {
    private int port=8080;
    private String realServer="http://localhost:8080";

    @Rule
    public WireMockRule wireMockRule = new WireMockRule();


    @Test
    @Description("Test for a service")
    public void exampleTest() {
        //Stub the endpoint
        stubFor(get(urlPathMatching("/departments/.*"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("<response>SUCCESS</response>")
                )
        );

        //Make a Request
        RestTemplate restTemplate = new RestTemplate();
        String resourceURL = realServer;
        ResponseEntity<String> response = restTemplate
                .getForEntity(resourceURL + "/departments/1",String.class);

        //Verify
        Assert.assertNotNull(response);
        assertTrue("status code not equal to 200",response.getStatusCode().equals(HttpStatus.OK));

    }



}
