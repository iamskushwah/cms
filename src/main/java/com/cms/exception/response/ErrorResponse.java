package com.cms.exception.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorResponse {
    private int Status;
    private String error;
    private String message;
    private String timestamp;

}
