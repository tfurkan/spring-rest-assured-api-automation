package com.example.base.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TokenRequestWithAppKey {
    public String appKey;
    public String password;
    public String username;
    public String deviceFingerPrint;
}