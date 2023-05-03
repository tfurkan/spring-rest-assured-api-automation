/*
package com.wiremock.basemock.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.wiremock.basemock.client.BaseMock;
import lombok.extern.slf4j.Slf4j;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

@Slf4j
public class MockServices extends BaseMock {


    public static String buildJson(Object body) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(body);
        return jsonString;
    }

    public static String buildXml(Object body) throws JsonProcessingException {
        ObjectMapper xmlMapper = new XmlMapper();
        xmlMapper.enable(SerializationFeature.INDENT_OUTPUT);
        String xml = xmlMapper.writeValueAsString(body);
        return xml.replaceAll("\\r|\\n", "");
    }

    public static void createForGetMock(String url, String jsonBody) {
        BaseMock.upServer();
        stubFor(get(urlEqualTo(url))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withStatus(200)
                        .withBody(jsonBody)));

        log.warn(jsonBody);
    }

    public static void createForXmlPostMock(String url, String xmlBody,String ip) {
        BaseMock.upServer();
         stubFor(post(urlEqualTo(url)).withRequestBody(containing("<IPAddress>172.26.14.30</IPAddress>"))
                .willReturn(aResponse()
                      //  .withHeader("<?xmlversion=\\\"1.0\\\"","encoding=\\\"utf-8\\\"?>")
                        .withStatus(200)
                        .withFixedDelay(2000)
                        //.withTransformers("response-template")
                        .withBody("<?xml version=\"1.0\" encoding=\"utf-8\"?>"+ xmlBody)));

        log.warn(xmlBody);
    }

}
*/
