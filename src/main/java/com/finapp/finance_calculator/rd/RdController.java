package com.finapp.finance_calculator.rd;

import com.finapp.finance_calculator.dto.RdRequestDTO;
import com.finapp.finance_calculator.dto.RdResponseDTO;

import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api/rd")
public class RdController {

	private final RdCalculatorSVC service;

    public RdController(RdCalculatorSVC service) {
        this.service = service;
    }

    @PostMapping
    public RdResponseDTO calculateRd(@RequestBody RdRequestDTO request) {
        return service.calculateRd(request);
    }
}
