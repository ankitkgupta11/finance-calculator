package com.finapp.finance_calculator.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.finapp.finance_calculator.vo.MetalPriceVO;

import java.util.List;

public interface MetalPriceRepository extends JpaRepository<MetalPriceVO, Long> {

    List<MetalPriceVO> findTop7ByMetalIdOrderByCreatedAtDesc(Long metalId);

    MetalPriceVO findTopByMetalIdOrderByCreatedAtDesc(Long metalId);
}