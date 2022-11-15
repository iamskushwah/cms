package com.cms.document.repository;

import com.cms.document.entity.ContractContent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContentRepository extends JpaRepository<ContractContent,Long> {

    Optional<ContractContent> findByContentId(Long contentId);

}
