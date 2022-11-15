package com.cms.document.controller;

import com.cms.document.entity.ContractDocument;
import com.cms.document.request.ContentInsertionRequest;
import com.cms.document.request.UpdatePdfContentRequest;
import com.cms.document.service.IDocumentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/documents")
public class DocumentController {

    Logger logger = LoggerFactory.getLogger(DocumentController.class);

    @Autowired
    private IDocumentService service;


    /**
     * This api insert the document with signer details, and content.
     *
     * @param request
     * @return message
     */

    @PutMapping("/document")
    public ResponseEntity<String> createDocument(@RequestBody ContentInsertionRequest request){
        return new ResponseEntity<>(service.savePdfContent(request), HttpStatus.OK);
    }


    /**
     * This api update the content using content id.
     *
     * @param request
     * @return message
     */

    @PostMapping("/document_content")
    public ResponseEntity<String> updatePdfContent(@RequestBody UpdatePdfContentRequest request){
        return new ResponseEntity<>(service.updatePdfContent(request),HttpStatus.OK);
    }


    /**
     * This api get the list of open document.
     *
     * @param
     * @return List of document
     */

    @GetMapping("/document_list")
    public ResponseEntity<List<ContractDocument>> getContractList(){
        return new ResponseEntity<>(service.getContractList(),HttpStatus.OK);
    }


    /**
     * This api get the document by id.
     *
     * @param contractId
     * @return document
     */

    @GetMapping("/document/{contractId}")
    public ResponseEntity<ContractDocument> getContractByContractId(@PathVariable(required = true) Long contractId){
        return new ResponseEntity<>(service.getContractByContractId(contractId),HttpStatus.OK);
    }


    /**
     * This api generate the pdf.
     *
     * @param contractId
     * @return message
     */

    @PostMapping("/pdf/{contractId}")
    public ResponseEntity<String> generatePdf(@PathVariable(required = true) Long contractId){
        return new ResponseEntity<>(service.generatePdf(contractId),HttpStatus.OK);
    }


    /**
     * This api update the document status.
     *
     * @param contractId
     * @param status
     * @return status
     */

    @PostMapping("/status/{contractId}/{status}")
    public ResponseEntity<String> updateContractStatus(@PathVariable(required = true) Long contractId,
                                       @PathVariable(required = true) String status){
        return new ResponseEntity<>(service.updateContractStatus(contractId,status),HttpStatus.OK);
    }

    /**
     * This api get the document content by contentId.
     *
     * @param contentId
     * @param
     * @return String
     */

    @GetMapping("/document_content/{contentId}")
    public ResponseEntity<String> getDocumentContent(@PathVariable(required = true) Long contentId){
        return new ResponseEntity<>(service.getDocumentContent(contentId),HttpStatus.OK);
    }
}
