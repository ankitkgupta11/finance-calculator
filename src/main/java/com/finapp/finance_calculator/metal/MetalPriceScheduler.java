package com.finapp.finance_calculator.metal;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Random;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.finapp.finance_calculator.repo.MetalPriceRepository;
import com.finapp.finance_calculator.repo.MetalRepository;
import com.finapp.finance_calculator.vo.MetalPriceVO;
import com.finapp.finance_calculator.vo.MetalVO;

@Component
public class MetalPriceScheduler {

	private final MetalRepository metalRepository;
	private final MetalPriceRepository metalPriceRepository;
	private final MetalSVC metalService;

	private final Random random = new Random();

	public MetalPriceScheduler(MetalRepository metalRepository, MetalPriceRepository metalPriceRepository,
			MetalSVC metalService) {
		this.metalRepository = metalRepository;
		this.metalPriceRepository = metalPriceRepository;
		this.metalService = metalService;
	}

	@Scheduled(cron = "0 0 10 * * ?") // 10 AM daily
	public void updatePrices() {

		List<MetalVO> metals = metalRepository.findAll();

		for (MetalVO metal : metals) {

			// 🔹 Get last saved price
			MetalPriceVO lastPrice = metalPriceRepository.findTopByMetalIdOrderByCreatedAtDesc(metal.getId());

			BigDecimal newPrice;

			if (lastPrice == null) {

				// First time initialization price
				newPrice = getInitialPrice(metal);

			} else {

				BigDecimal previousPrice = lastPrice.getPrice();

				// Generate small fluctuation between -0.5% to +0.5%
				double percentChange = (random.nextDouble() - 0.5) / 100;
				// e.g. -0.005 to +0.005

				BigDecimal multiplier = BigDecimal.valueOf(1 + percentChange);

				newPrice = previousPrice.multiply(multiplier).setScale(2, RoundingMode.HALF_UP);
			}

			// Save new price using service (auto calculates % change)
			metalService.saveNewPrice(metal, newPrice);
		}
	}

	// 🔹 Initial base price (Realistic Indian Approx Values)
	private BigDecimal getInitialPrice(MetalVO metal) {

		if ("GOLD".equalsIgnoreCase(metal.getName())) {

			if ("24K".equalsIgnoreCase(metal.getPurity())) {
				return new BigDecimal("6200.00");
			}
			if ("22K".equalsIgnoreCase(metal.getPurity())) {
				return new BigDecimal("5680.00");
			}
			if ("18K".equalsIgnoreCase(metal.getPurity())) {
				return new BigDecimal("4650.00");
			}

		} else if ("SILVER".equalsIgnoreCase(metal.getName())) {
			return new BigDecimal("75.00");
		} else if ("PLATINUM".equalsIgnoreCase(metal.getName())) {
			return new BigDecimal("2800.00");
		} else if ("COPPER".equalsIgnoreCase(metal.getName())) {
			return new BigDecimal("750.00");
		} else if ("ZINC".equalsIgnoreCase(metal.getName())) {
			return new BigDecimal("250.00");
		}

		return BigDecimal.ZERO;
	}
}