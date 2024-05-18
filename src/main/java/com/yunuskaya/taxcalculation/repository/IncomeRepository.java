package com.yunuskaya.taxcalculation.repository;

import com.yunuskaya.taxcalculation.model.Income;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IncomeRepository extends JpaRepository<Income, Long> {

}
