package com.finapp.finance_calculator.tnc;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.finapp.finance_calculator.dto.ConsentRequestDTO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/consent")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:8081")
public class ConsentController {

    private ConsentSVC service;
    
    public ConsentController(ConsentSVC service) {
        this.service = service;
    }

    @PostMapping("/agree")
    public ResponseEntity<?> agree(
            @RequestBody ConsentRequestDTO requestDTO,
            HttpServletRequest httpRequest, HttpServletResponse response) {

        service.saveConsent(requestDTO, httpRequest, response);

        return ResponseEntity.ok("Consent recorded successfully");
    }
}