package com.cms.document.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

@Data
public class ContentInsertionRequest {

    private Long contentId;
    private String content;
    private Long id;
    private Long contractId;
    private String senderEmail;
    private String receiverEmail;
    private String createdBy;
    private String updatedBy;
    private String status;
}
