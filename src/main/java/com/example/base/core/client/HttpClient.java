package com.example.base.core.client;


import com.example.base.core.config.ApiConfig;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.response.Response;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.useRelaxedHTTPSValidation;


public abstract class HttpClient {
    private final ApiConfig apiConfig;

    public HttpClient(ApiConfig apiConfig) {
        this.apiConfig = apiConfig;
    }

    public Response post(String path, Object body) {



        return given().relaxedHTTPSValidation()
                .log().method()
                .log().uri()
                .log().body()
                .baseUri(apiConfig.url)
                .contentType(ContentType.JSON)
                .when()
                .body(body)
                .post(path)
                .then()
                .log().body()
                .extract().response();
    }


    public Response postWithHeader(String path, Header header, Object body) {

        return given().relaxedHTTPSValidation()
                .log().method()
                .log().uri()
                .log().headers()
                .log().body()
                .baseUri(apiConfig.url)
                .header(header)
                .contentType(ContentType.JSON)
                .when()
                .body(body)
                .post(path)
                .then()
                .log().body()
                .extract().response();
    }

    public Response postWithMultipleHeader(String path, Map<String, String> header , Object body) {

        return given().relaxedHTTPSValidation()
                .log().method()
                .log().uri()
                .log().headers()
                .log().body()
                .baseUri(apiConfig.url)
                .headers(header)
                .contentType(ContentType.JSON)
                .when()
                .body(body)
                .post(path)
                .then()
                .log().body()
                .extract().response();
    }

    public Response putWithHeader(String path, Header header, Object body) {

        return given().relaxedHTTPSValidation()
                .log().method()
                .log().uri()
                .log().headers()
                .log().body()
                .baseUri(apiConfig.url)
                .header(header)
                .contentType(ContentType.JSON)
                .when()
                .body(body)
                .put(path)
                .then()
                .log().body()
                .extract().response();
    }

    public Response postWithJwt(String path, Object body, String jwt) {

        return given().relaxedHTTPSValidation()
                .log().method()
                .log().uri()
                .log().headers()
                .log().body()
                .baseUri(apiConfig.url)
                .header("Authorization", "Bearer " + jwt)
                .contentType(ContentType.JSON)
                .when()
                .body(body)
                .post(path)
                .then()
                .log().body()
                .extract().response();
    }

    public Response get(String path) {

        return given().relaxedHTTPSValidation()
                .log().method()
                .log().uri()
                .log().body()
                .baseUri(apiConfig.url)
                .contentType(ContentType.JSON)
                .when()
                .get(path)
                .then()
                .log().body()
                .extract().response();
    }

    public Response getWithHeader(String path, Header header) {

        return given().relaxedHTTPSValidation()
                .log().method()
                .log().uri()
                .log().headers()
                .baseUri(apiConfig.url)
                .header(header)
                .contentType(ContentType.JSON)
                .when()
                .get(path)
                .then()
                .log().body()
                .extract().response();
    }

    public Response getWithMultipleHeader(String path, Map<String, String> headers) {

        return given().relaxedHTTPSValidation()
                .log().method()
                .log().uri()
                .log().headers()
                .baseUri(apiConfig.url)
                .headers(headers)
                .contentType(ContentType.JSON)
                .when()
                .get(path)
                .then()
                .log().body()
                .extract().response();
    }

    public Response getWithJwt(String path, String jwt) {

        return given().relaxedHTTPSValidation()
                .log().method()
                .log().uri()
                .log().headers()
                .baseUri(apiConfig.url)
                .header("Authorization", "Bearer " + jwt)
                .contentType(ContentType.JSON)
                .when()
                .get(path)
                .then()
                .log().body()
                .extract().response();
    }

    public Response getWithQueryParams(String path, Map<String, Object> query) {

        return given().relaxedHTTPSValidation()
                .log().method()
                .log().uri()
                .baseUri(apiConfig.url)
                .contentType(ContentType.JSON)
                .when()
                .queryParams(query)
                .get(path)
                .then()
                .log().body()
                .extract().response();
    }

    public Response getWithQueryParamsList(String path, Map<String, List<String>> query) {

        return given().relaxedHTTPSValidation()
                .log().method()
                .log().uri()
                .baseUri(apiConfig.url)
                .contentType(ContentType.JSON)
                .when()
                .queryParams(query)
                .get(path)
                .then()
                .log().body()
                .extract().response();
    }

    public Response getWithHeaderAndQueryParams(String path, Header header, Map<String, String> query) {

        return given().relaxedHTTPSValidation()
                .log().method()
                .log().uri()
                .log().headers()
                .baseUri(apiConfig.url)
                .header(header)
                .contentType(ContentType.JSON)
                .when()
                .queryParams(query)
                .get(path)
                .then()
                .log().body()
                .extract().response();
    }

    public Response getWithQueryParamsAndJwt(String path, Map<String, String> params, String jwt) {

        return given().relaxedHTTPSValidation()
                .log().method()
                .log().uri()
                .log().headers()
                .baseUri(apiConfig.url)
//                .auth().oauth2(jwt)
                .header("Authorization", "Bearer " + jwt)
                .contentType(ContentType.JSON)
                .queryParams(params)
                .when()
                .get(path)
                .then()
                .log().body()
                .extract().response();
    }
}
