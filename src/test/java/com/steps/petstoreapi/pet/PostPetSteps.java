package com.steps.petstoreapi.pet;

import com.example.petstoreapi.client.pet.PostPetPetClient;
import com.example.petstoreapi.model.pet.postpet.PostPetRequest;
import com.example.petstoreapi.model.pet.postpet.PostPetResponse;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class PostPetSteps {
    private PostPetRequest request;
    private PostPetResponse response;
    private final PostPetPetClient petClient;
    private List<String> photoUrlsList;

    @Given("send request to post pet ep {string}")
    public void sendRequestToPetEp(String test) {
        System.out.println(test);
        photoUrlsList = new ArrayList<>();
        photoUrlsList.add("tasdfa");
        photoUrlsList.add("fdagvafdg");
        request = PostPetRequest.builder()
                .id(1859198)
                .name("TESTTFA")
                .photoUrls(photoUrlsList)
                .status("1")
                .build();
        response = petClient.sendRequest(request);
    }

    @Then("check pet ep response message")
    public void checkPetEpResponseMessage() {
        petClient.checkResponse(response);
    }

}
