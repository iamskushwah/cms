package com.cms.user.repository;

import com.cms.user.entity.OTPVerification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OTPVerificationRepository extends JpaRepository<OTPVerification,Long> {

    OTPVerification findByUserId(Long userId);

}
