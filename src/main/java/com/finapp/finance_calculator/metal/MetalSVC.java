package com.finapp.finance_calculator.metal;



import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.finapp.finance_calculator.dto.MetalPriceResponseDTO;
import com.finapp.finance_calculator.repo.MetalPriceRepository;
import com.finapp.finance_calculator.repo.MetalRepository;
import com.finapp.finance_calculator.vo.MetalPriceVO;
import com.finapp.finance_calculator.vo.MetalVO;

@Service
public class MetalSVC {

    private final MetalRepository metalRepository;
    private final MetalPriceRepository metalPriceRepository;

    public MetalSVC(MetalRepository metalRepository,
                        MetalPriceRepository metalPriceRepository) {
        this.metalRepository = metalRepository;
        this.metalPriceRepository = metalPriceRepository;
    }

    // 🔹 Get Latest Prices
    public List<MetalPriceResponseDTO> getLatestPrices() {

        List<MetalVO> metals = metalRepository.findAll();

        List<MetalPriceResponseDTO> response = new ArrayList<>();

        for (MetalVO metal : metals) {

            MetalPriceVO latest =
                    metalPriceRepository.findTopByMetalIdOrderByCreatedAtDesc(metal.getId());

            if (latest != null) {
                response.add(new MetalPriceResponseDTO(
                		metal.getId(),
                        metal.getName(),
                        metal.getPurity(),
                        latest.getPrice(),
                        latest.getPriceChangePercent(),
                        metal.getUnit(),
                        latest.getCreatedAt()
                ));
            }
        }

        return response;
    }

    // 🔹 Get Last 7 Prices For Trend
    public List<BigDecimal> getLast7Prices(Long metalId) {

        List<MetalPriceVO> prices =
                metalPriceRepository.findTop7ByMetalIdOrderByCreatedAtDesc(metalId);

        return prices.stream()
                .sorted(Comparator.comparing(MetalPriceVO::getCreatedAt))
                .map(MetalPriceVO::getPrice)
                .collect(Collectors.toList());
    }

    // 🔹 Save Price (Used by Scheduler)
    public void saveNewPrice(MetalVO metal, BigDecimal newPrice) {

    	MetalPriceVO previous =
                metalPriceRepository.findTopByMetalIdOrderByCreatedAtDesc(metal.getId());

        BigDecimal changePercent = BigDecimal.ZERO;

        if (previous != null) {
            BigDecimal oldPrice = previous.getPrice();

            if (oldPrice.compareTo(BigDecimal.ZERO) > 0) {
                changePercent = newPrice.subtract(oldPrice)
                        .divide(oldPrice, 4, RoundingMode.HALF_UP)
                        .multiply(BigDecimal.valueOf(100));
            }
        }

        MetalPriceVO metalPrice = new MetalPriceVO();
        metalPrice.setMetal(metal);
        metalPrice.setPrice(newPrice);
        metalPrice.setPriceChangePercent(changePercent);
        metalPrice.setCreatedAt(LocalDateTime.now());

        metalPriceRepository.save(metalPrice);
    }
}