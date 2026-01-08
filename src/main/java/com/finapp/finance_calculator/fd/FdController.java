package com.finapp.finance_calculator.fd;

import com.finapp.finance_calculator.dto.FdRequestDTO;
import com.finapp.finance_calculator.dto.FdResponseDTO;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/fd")
public class FdController {

    private final FdCalculatorSVC service;

    public FdController(FdCalculatorSVC service) {
        this.service = service;
    }

    @PostMapping
    public FdResponseDTO calculateFd(@RequestBody FdRequestDTO request) {
        return service.calculateFd(request);
    }
}
