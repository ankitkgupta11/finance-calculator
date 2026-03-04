package com.finapp.finance_calculator.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.finapp.finance_calculator.vo.MetalVO;

public interface MetalRepository extends JpaRepository<MetalVO, Long> {

    Optional<MetalVO> findByNameAndPurity(String name, String purity);
}