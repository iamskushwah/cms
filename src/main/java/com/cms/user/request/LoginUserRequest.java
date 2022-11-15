package com.cms.user.request;

import lombok.Data;

@Data
public class LoginUserRequest {
    private String email;
    private String password;
}
