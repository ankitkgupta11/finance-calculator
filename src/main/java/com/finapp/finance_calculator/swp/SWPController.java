package com.finapp.finance_calculator.swp;

import com.finapp.finance_calculator.dto.SwpRequestDTO;
import com.finapp.finance_calculator.dto.SwpResponseDTO;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class SWPController {

    private final SWPCalculatorSVC swpService = new SWPCalculatorSVC();

    @PostMapping("/swp")
    public SwpResponseDTO calculateSwp(@RequestBody SwpRequestDTO request) {
        return swpService.calculateSwp(request);
    }

}
