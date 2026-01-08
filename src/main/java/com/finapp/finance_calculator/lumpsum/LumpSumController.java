package com.finapp.finance_calculator.lumpsum;

import com.finapp.finance_calculator.dto.LumpSumRequestDTO;
import com.finapp.finance_calculator.dto.LumpSumResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/lumpsum")
@RequiredArgsConstructor
public class LumpSumController {

    private final LumpSumCalculatorService lumpSumCalculatorService = new LumpSumCalculatorService();

    @PostMapping
    public LumpSumResponseDTO calculateLumpSum(@RequestBody LumpSumRequestDTO request) {
        return lumpSumCalculatorService.calculateLumpSum(request);
    }
}
