package com.finapp.finance_calculator.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.finapp.finance_calculator.vo.CalculationHistoryVO;

@Repository
public interface CalculationHistoryRepository
        extends JpaRepository<CalculationHistoryVO, Long> {
}
