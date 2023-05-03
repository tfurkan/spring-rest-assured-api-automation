package com.example.petstoreapi.client;

import com.example.base.client.CommonClient;
import com.example.base.core.client.HttpClient;
import com.example.base.core.config.AppSettings;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public abstract class PetStoreApiClient extends HttpClient  {

    public PetStoreApiClient() {
        super(AppSettings.Instance.petstoreApi);
    }
}
