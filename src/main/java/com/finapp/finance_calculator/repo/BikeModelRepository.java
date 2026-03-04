package com.finapp.finance_calculator.repo;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.finapp.finance_calculator.vo.BikeModelVO;

public interface BikeModelRepository extends JpaRepository<BikeModelVO, Long> {

    List<BikeModelVO> findByCompanyId(Long companyId);
}