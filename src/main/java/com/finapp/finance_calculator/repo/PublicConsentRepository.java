package com.finapp.finance_calculator.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.finapp.finance_calculator.vo.PublicConsentLogVO;

public interface PublicConsentRepository
        extends JpaRepository<PublicConsentLogVO, Long> {
	
	boolean existsByAnonidAndConsentTypeAndVersion(
	        String anonid,
	        String consentType,
	        String version
	);
}