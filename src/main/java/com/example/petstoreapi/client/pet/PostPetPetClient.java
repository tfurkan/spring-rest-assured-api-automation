package com.example.petstoreapi.client.pet;

import com.example.base.core.client.HttpClient;
import com.example.base.core.config.ApiConfig;
import com.example.base.core.config.AppSettings;
import com.example.petstoreapi.client.PetStoreApiClient;
import com.example.petstoreapi.model.pet.postpet.PostPetRequest;
import com.example.petstoreapi.model.pet.postpet.PostPetResponse;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import io.restassured.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.testng.asserts.SoftAssert;

import static com.example.petstoreapi.constants.PetStoreApiConstants.PetStoreApiEpPaths.POST_MOCK;
import static com.example.petstoreapi.constants.PetStoreApiConstants.PetStoreApiEpPaths.POST_PET_PATH;
import static io.restassured.RestAssured.given;
import io.restassured.matcher.ResponseAwareMatcher;
import io.restassured.module.mockmvc.matcher.RestAssuredMockMvcMatchers;
import io.restassured.module.mockmvc.response.MockMvcResponse;

@Component
public class PostPetPetClient extends PetStoreApiClient {




    public PostPetResponse sendRequest(PostPetRequest request) {

        return post(POST_PET_PATH, request)
                .then()
                .extract()
                .as(PostPetResponse.class, ObjectMapperType.GSON);

    }

    public void checkResponse(PostPetResponse response) {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertNotNull(response);
        softAssert.assertAll();

    }

    public Response restAssuredMock(){
        return (Response) RestAssuredMockMvc
                .given()
                .auth().none()
                .contentType("application/son")
                .body("{\"title\": \"Effective Java\"}")
                .when()
                .post(POST_MOCK)
                .then()
                .statusCode(401);
    }
}
