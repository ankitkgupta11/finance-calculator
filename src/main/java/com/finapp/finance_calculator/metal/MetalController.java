package com.finapp.finance_calculator.metal;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.finapp.finance_calculator.dto.MetalPriceResponseDTO;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api/metals")
public class MetalController {

	private final MetalSVC metalService;

	public MetalController(MetalSVC metalService) {
		this.metalService = metalService;
	}

	@CrossOrigin(origins = "http://localhost:8081")
	@GetMapping("/prices")
	public List<MetalPriceResponseDTO> getLatestPrices() {
		return metalService.getLatestPrices();
	}

	// 🔹 Get 7-Day Trend
	@GetMapping("/{metalId}/trend")
	public List<BigDecimal> getTrend(@PathVariable Long metalId) {
		return metalService.getLast7Prices(metalId);
	}
}