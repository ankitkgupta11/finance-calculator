package com.finapp.finance_calculator.bikepurchasingpower;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.finapp.finance_calculator.dto.IdNameDTO;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")

public class BikeController {

    private final BikeAffordabilitySVC service;
    public BikeController(BikeAffordabilitySVC service) {
    	this.service=service;
    }

    @GetMapping("/bikes/companies")
    public List<IdNameDTO> getCompanies() {
        return service.getCompanies();
    }
	
    @GetMapping("/bikes/models/{companyId}")
    public List<IdNameDTO> getModels(@PathVariable Long companyId) {
        return service.getModels(companyId);
    }
}
