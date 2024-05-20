package com.yunuskaya.taxcalculation.repository;

import com.yunuskaya.taxcalculation.model.TaxReturn;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaxReturnRepository extends JpaRepository<TaxReturn, Long> {
}
