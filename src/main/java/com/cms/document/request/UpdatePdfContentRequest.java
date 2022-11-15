package com.cms.document.request;

import lombok.Data;

@Data
public class UpdatePdfContentRequest {

    private Long contentId;
    private String content;
}
