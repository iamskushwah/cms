package com.cms.document.service;

import com.cms.document.entity.ContractDocument;
import com.cms.document.request.ContentInsertionRequest;
import com.cms.document.request.UpdatePdfContentRequest;

import java.util.List;

public interface IDocumentService {

    public String savePdfContent(ContentInsertionRequest request);
    public String updatePdfContent(UpdatePdfContentRequest request);
    public List<ContractDocument> getContractList();
    public ContractDocument getContractByContractId(Long contractId);
    public String generatePdf(Long contractId);
    public String updateContractStatus(Long contractId,String status);
    String getDocumentContent(Long contentId);
}
