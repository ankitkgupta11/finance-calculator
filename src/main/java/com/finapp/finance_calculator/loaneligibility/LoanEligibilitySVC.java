package com.finapp.finance_calculator.loaneligibility;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.finapp.finance_calculator.dto.LoanEligibilityRequestDTO;
import com.finapp.finance_calculator.dto.LoanEligibilityResponseDTO;
import com.finapp.finance_calculator.dto.SIPYearlyBreakDownDTO;
import com.finapp.finance_calculator.dto.SipRequestDTO;
import com.finapp.finance_calculator.dto.SipResponseDTO;

@Service
public class LoanEligibilitySVC {

    public LoanEligibilityResponseDTO checkEligibility(LoanEligibilityRequestDTO request) {

        LoanEligibilityResponseDTO response = new LoanEligibilityResponseDTO();

        // 1️⃣ CIBIL Check
        if (request.getCibilScore() < 650) {
            response.setEligible(false);
            response.setMessage("CIBIL score too low 🚫");
            return response;
        }

        // 2️⃣ Age Check
        int maturityAge = request.getAge() + request.getTenureYears();

        int maxAgeLimit;

        if ("SALARIED".equalsIgnoreCase(request.getEmploymentType())) {
            maxAgeLimit = 60;
        } else if ("SELF_EMPLOYED".equalsIgnoreCase(request.getEmploymentType())) {
            maxAgeLimit = 65;
        } else {
            response.setEligible(false);
            response.setMessage("Invalid employment type 🚫");
            return response;
        }

        if (maturityAge > maxAgeLimit) {
            response.setEligible(false);
            response.setMessage("Age exceeds eligibility at loan maturity 🚫");
            return response;
        }

        // 3️⃣ FOIR Based on Employment Type
        double income = request.getMonthlyIncome();
        double existingEmi = request.getExistingEmi();

        double maxFoir;

        if ("SALARIED".equalsIgnoreCase(request.getEmploymentType())) {
            maxFoir = (income < 50000) ? 0.5 :
                      (income <= 100000) ? 0.6 : 0.65;
        } else {
            // SELF_EMPLOYED slightly stricter
            maxFoir = (income < 50000) ? 0.45 :
                      (income <= 100000) ? 0.55 : 0.6;
        }

        double maxAllowedEmi = income * maxFoir - existingEmi;

        if (maxAllowedEmi <= 0) {
            response.setEligible(false);
            response.setMessage("Income insufficient for new loan 🚫");
            return response;
        }

        // 4️⃣ Reverse EMI to Loan Amount
        double monthlyRate = request.getAnnualRate() / 12 / 100;
        int totalMonths = request.getTenureYears() * 12;

        double factor = (monthlyRate * Math.pow(1 + monthlyRate, totalMonths))
                / (Math.pow(1 + monthlyRate, totalMonths) - 1);

        double eligibleLoanAmount = maxAllowedEmi / factor;

        response.setEligible(true);
        response.setMaxAllowedEmi(round(maxAllowedEmi));
        response.setMaxEligibleLoanAmount(round(eligibleLoanAmount));
        response.setFoirPercentage(round(maxFoir * 100));
        response.setMessage("Eligible for loan ✅");

        return response;
    }

    private double round(double value) {
        return Math.round(value * 100.0) / 100.0;
    }
}
