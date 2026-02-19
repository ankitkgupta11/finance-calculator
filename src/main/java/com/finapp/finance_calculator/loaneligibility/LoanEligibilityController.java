package com.finapp.finance_calculator.loaneligibility;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.finapp.finance_calculator.dto.LoanEligibilityRequestDTO;
import com.finapp.finance_calculator.dto.LoanEligibilityResponseDTO;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api/loan-eligibility")
public class LoanEligibilityController {

	private final LoanEligibilitySVC service;

	public LoanEligibilityController(LoanEligibilitySVC service) {
		this.service = service;
	}

	@PostMapping
	public LoanEligibilityResponseDTO check(@RequestBody LoanEligibilityRequestDTO request) {
		return service.checkEligibility(request);
	}
}
