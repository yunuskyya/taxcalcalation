package com.yunuskaya.taxcalculation.repository;

import com.yunuskaya.taxcalculation.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseRepository extends JpaRepository<Expense,Long> {
}
