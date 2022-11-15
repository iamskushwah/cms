package com.cms.user.request;

import lombok.Data;

@Data
public class VarifyOTPRequest {
    private Long userId;
    private String otp;
}

