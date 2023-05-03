package com.example.petstoreapi.model.pet.postpet;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class PostPetRequest {
    private int id;
    private category category;
    private String name;
    private List<String> photoUrls;
    private List<tags> tags;
    private String status;
    @Builder
    @Data
    public static class category {
        private int id;
        private String name;
    }

    @Builder
    @Data
    public static class tags {
        private int id;
        private String name;
    }

}
