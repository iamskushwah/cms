package com.cms.document.repository;

import com.cms.document.entity.ContractDocument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocumentRepository extends JpaRepository<ContractDocument,Long> {

    ContractDocument findByContractId(Long contractId);
    List<ContractDocument> findAllByStatus(String status);

}
