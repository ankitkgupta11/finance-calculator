package com.finapp.finance_calculator.bikepurchasingpower;

import java.util.List;

import org.springframework.stereotype.Service;

import com.finapp.finance_calculator.dto.IdNameDTO;
import com.finapp.finance_calculator.repo.BikeCompanyRepository;
import com.finapp.finance_calculator.repo.BikeModelRepository;
import com.finapp.finance_calculator.vo.BikeCompanyVO;

@Service
public class BikeAffordabilitySVC {
	private final BikeCompanyRepository companyRepo;
	private final BikeModelRepository bikeModelRepository;

	public BikeAffordabilitySVC(BikeCompanyRepository companyRepo, BikeModelRepository bikeModelRepository) {
		this.companyRepo = companyRepo;
		this.bikeModelRepository = bikeModelRepository;
	}

	public List<IdNameDTO> getCompanies() {
        return companyRepo.findAll()
                .stream()
                .map(c -> new IdNameDTO(c.getId(), c.getName()))
                .toList();
    }
	
	public List<IdNameDTO> getModels(Long companyId) {
        return bikeModelRepository.findByCompanyId(companyId)
                .stream()
                .map(m -> new IdNameDTO(m.getId(), m.getName()))
                .toList();
    }
}
