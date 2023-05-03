package com.example.base.client;

import com.example.base.core.config.AppSettings;
import com.example.base.model.TokenRequestWithAppKey;
import io.restassured.http.ContentType;
import lombok.Data;

import static io.restassured.RestAssured.given;


@Data
public class TokenClient  {

    public static String JWT;

    public String getJwtWithAppKey()
    {
        TokenRequestWithAppKey tokenRequestWithAppKey = TokenRequestWithAppKey.builder()
                .appKey("AF7F2A37-CC4B-4F1C-87FD-FF3642F67ECB")
                .username("eazakli_newcheckout@gmail.com")
                .password("Sardes.123")
                .deviceFingerPrint("")
                .build();

        return JWT = given()
                .baseUri(AppSettings.Instance.loginUrlWithAppKey)
                .contentType(ContentType.JSON)
                .when()
                .body(tokenRequestWithAppKey)
                .post("api/Service/Login")
                .then()
                .extract()
                .path("JwtToken");
    }

}

