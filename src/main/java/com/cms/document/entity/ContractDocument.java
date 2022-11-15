package com.cms.document.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "document")
@Data
public class ContractDocument {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @JsonProperty("contract_id")
    private Long contractId;

    @JsonProperty("sender_email")
    private String senderEmail;

    @JsonProperty("receiver_email")
    private String receiverEmail;

    @JsonProperty("content_id")
    private Long contentId;

    @JsonProperty("created_by")
    private String createdBy;

    @JsonProperty("updated_by")
    private String updatedBy;

    @JsonProperty("created_timestamp")
    private Date createdTimestamp;

    @JsonProperty("updated_timestamp")
    private Date updatedTimestamp;

    @JsonProperty("status")
    private String status;
}
