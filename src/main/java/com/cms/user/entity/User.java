package com.cms.user.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;


import javax.persistence.*;

@Entity
@Table(name = "user")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JsonProperty("firstname")
    private String firstName;

    @JsonProperty("lastname")
    private String lastName;

    @JsonProperty("email")
    private String email;

    @Column(length = 60)
    @JsonProperty("password")
    private String password;

    @JsonProperty("phonenumber")
    private Long phoneNumber;

    @JsonProperty("contract_id")
    private Long contractId;

    @JsonProperty("role")
    private String role;

    @JsonProperty("enabled")
    private Boolean enabled = false;

}
