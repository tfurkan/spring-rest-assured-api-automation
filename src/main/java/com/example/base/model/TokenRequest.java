package com.example.base.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TokenRequest {
    public String em;
    public String p;
}
