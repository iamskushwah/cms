package com.cms.user.request;

import lombok.Data;

@Data
public class RegisterUserRequest {

    private String firstName;
    private String lastName;
    private String email;
    private Long phoneNumber;
    private String password;

}
