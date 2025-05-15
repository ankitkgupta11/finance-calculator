package com.finapp.finance_calculator.emi;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")

public class FinanceController {

    @PostMapping("/emi")
    public ResponseEntity<EMIResponseDTO> calculateEMI(@RequestBody EMIRequestDTO request) {
        EMIResponseDTO result = EMICalculatorUtil.calculateEMI(request);
        return ResponseEntity.ok(result);
    }
}
