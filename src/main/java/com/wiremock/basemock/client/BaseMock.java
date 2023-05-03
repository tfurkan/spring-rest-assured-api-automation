package com.wiremock.basemock.client;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.wiremock.basemock.constants.BaseConstants;
import io.restassured.response.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static io.restassured.RestAssured.given;



public class BaseMock {

    //public WireMock wm=new WireMock(8080);
    WireMockServer wireMockServer;
    @BeforeEach
    public void setup () {
        wireMockServer = new WireMockServer(8090);
        wireMockServer.start();
        setupStub();
    }



    @AfterEach
    public void teardown () {
        wireMockServer.stop();
    }

    public void setupStub() {
        wireMockServer.stubFor(get(urlEqualTo("/an/endpoint"))
                .willReturn(aResponse().withHeader("Content-Type", "text/plain")
                        .withStatus(200)
                        .withBodyFile("json/glossary.json")));
    }

    @Test
    public void testStatusCodePositive() {
        given().
                when().
                get("http://localhost:8090/an/endpoint").
                then().
                assertThat().statusCode(200);
    }
    @Test
    public void testStatusCodeNegative() {
        given().
                when().
                get("http://localhost:8090/another/endpoint").
                then().
                assertThat().statusCode(404);
    }
    @Test
    public void testResponseContents() {
        Response response =  given().when().get("http://localhost:8090/an/endpoint");
        String title = response.jsonPath().get("glossary.title");
        System.out.println(title);
        Assert.assertEquals("example glossary", title);
    }
}
