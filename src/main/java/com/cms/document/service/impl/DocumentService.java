package com.cms.document.service.impl;


import com.cms.document.entity.ContractContent;
import com.cms.document.entity.ContractDocument;
import com.cms.document.repository.ContentRepository;
import com.cms.document.repository.DocumentRepository;
import com.cms.document.request.ContentInsertionRequest;
import com.cms.document.request.UpdatePdfContentRequest;
import com.cms.document.service.IDocumentService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class DocumentService implements IDocumentService {

    Logger logger = LoggerFactory.getLogger(DocumentService.class);

    @Autowired
    private DocumentRepository documentRepository;

    @Autowired
    private ContentRepository contentRepository;

    @Override
    public String savePdfContent(ContentInsertionRequest requet) {
        try{
            ContractContent content = new ContractContent(requet.getContentId(), requet.getContent());
            contentRepository.save(content);

            ContractDocument document = new ContractDocument();
            document.setId(requet.getId());
            document.setContractId(requet.getContractId());
            document.setSenderEmail(requet.getSenderEmail());
            document.setReceiverEmail(requet.getReceiverEmail());
            document.setContentId(requet.getContentId());
            document.setStatus(requet.getStatus());
            document.setCreatedBy(requet.getCreatedBy());
            document.setUpdatedBy(requet.getUpdatedBy());
            document.setCreatedTimestamp(new Date());
            document.setUpdatedTimestamp(new Date());

            documentRepository.save(document);
            return "Success!";
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }

    }

    @Override
    public String updatePdfContent(UpdatePdfContentRequest request) {
        try{
            Optional<ContractContent> content = contentRepository.findByContentId(request.getContentId());
            content.get().setContent(request.getContent());
            contentRepository.save(content.get());
            return "Success!";
        }
        catch (Exception e){
            e.printStackTrace();
            throw e;
        }

    }

    @Override
    public List<ContractDocument> getContractList() {
        try{
            List<ContractDocument> documentList = documentRepository.findAllByStatus("open");
            return documentList;
        }
        catch (Exception e){
            e.printStackTrace();
            throw e;
        }

    }

    @Override
    public ContractDocument getContractByContractId(Long contractId) {
        try{
            return documentRepository.findByContractId(contractId);
        }
        catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public String generatePdf(Long contractId) {
        try{
            ContractDocument contract = documentRepository.findByContractId(contractId);
            if(!ObjectUtils.isEmpty(contract)){
                Long contentId = contract.getContentId();
                Optional<ContractContent> contentObject = contentRepository.findByContentId(contentId);
                String content = contentObject.get().getContent();

                String xhtml = htmlToXhtml();
                xhtmlToPdf(xhtml,"output.pdf");
                return "PDF Created!";
            }else {
                logger.error("Contract Id is not valid!");
                throw new Exception("Contract Id is not valid");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static String htmlToXhtml() throws IOException {
        File html = new File("G:\\Genxcellence\\React\\demo.html");
//        String html = "<h1 style=color:red;text-align:center> Contract Management System </h1>" +
//                "<h2 style=color:green> Contract Management System </h2>";
        Document document = Jsoup.parse(html,"UTF-8");
        document.outputSettings().syntax(Document.OutputSettings.Syntax.xml);
        return document.html();
    }

    private static void xhtmlToPdf(String xhtml, String outFileName) throws IOException {
        File output = new File(outFileName);
        ITextRenderer iTextRenderer = new ITextRenderer();
        iTextRenderer.setDocumentFromString(xhtml);
        iTextRenderer.layout();
        OutputStream os = new FileOutputStream(output);
        iTextRenderer.createPDF(os);
        os.close();
    }

    @Override
    public String updateContractStatus(Long contractId, String status) {
        try{
            ContractDocument contract = documentRepository.findByContractId(contractId);
            if(!ObjectUtils.isEmpty(contract)){
                contract.setStatus(status);
                documentRepository.save(contract);
                return status;
            }else{
                logger.error("Contract Id is not valid!");
                throw new Exception("Contract Id is not Valid !");
            }

        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getDocumentContent(Long contentId) {
        Optional<ContractContent> content = contentRepository.findByContentId(contentId);
        String value = null;
        if(!ObjectUtils.isEmpty(content)){
            value = content.get().getContent();
        }
        return value;
    }


}
