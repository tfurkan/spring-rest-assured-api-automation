package com.wiremock.basemock.client;

import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.wiremock.basemock.constants.BaseConstants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;


@Slf4j
@RequiredArgsConstructor
public class BaseMock2 {

    //public WireMock wm=new WireMock(8080);

    @Rule
    public static WireMockRule wireMockRule = new WireMockRule(options().port(8080).httpsPort(8443).dynamicPort());

    @BeforeClass
    public static void upServer() {
        WireMock.configureFor(BaseConstants.MOCK_HOST_IP, BaseConstants.MOCK_HOST_PORT);
        wireMockRule.start();
    }

    @AfterClass
    public static void closeServer() {
        wireMockRule.stop();
    }

}
