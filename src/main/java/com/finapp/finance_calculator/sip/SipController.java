package com.finapp.finance_calculator.sip;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.finapp.finance_calculator.dto.SipRequestDTO;
import com.finapp.finance_calculator.dto.SipResponseDTO;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/sip")
@RequiredArgsConstructor
public class SipController {

    private final SipCalculatorSVC sipCalculatorService = new SipCalculatorSVC();

    @PostMapping
    public SipResponseDTO calculateSip(@RequestBody SipRequestDTO request) {
        return sipCalculatorService.calculateSip(request);
    }
}
