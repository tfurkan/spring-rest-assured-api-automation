package com.example.petstoreapi.model.pet.postpet;

import com.example.petstoreapi.model.BasePetStoreApiReponse;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;

import java.util.List;

@Getter
public class PostPetResponse extends BasePetStoreApiReponse {
    @SerializedName("id")
    private int id;
    @SerializedName("category")
    private category category;
    @SerializedName("name")
    private String name;
    @SerializedName("photoUrls")
    private List<String> photoUrls;
    @SerializedName("tags")
    private List<tags> tags;
    @SerializedName("status")
    private String status;
    @Getter
    public static class category {
        @SerializedName("id")
        private int id;
        @SerializedName("name")
        private String name;
    }

    @Getter
    public static class tags {
        @SerializedName("id")
        private int id;
        @SerializedName("name")
        private String name;
    }
}
