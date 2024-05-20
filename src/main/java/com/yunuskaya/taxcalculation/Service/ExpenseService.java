package com.yunuskaya.taxcalculation.Service;

import com.yunuskaya.taxcalculation.model.Expense;
import com.yunuskaya.taxcalculation.model.Income;
import com.yunuskaya.taxcalculation.repository.ExpenseRepository;
import com.yunuskaya.taxcalculation.repository.IncomeRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExpenseService {

    private  final  ExpenseRepository expenseRepository;
    private final IncomeRepository incomeRepository;

    public ExpenseService (ExpenseRepository expenseRepository, IncomeRepository incomeRepository){
        this.expenseRepository = expenseRepository;
        this.incomeRepository = incomeRepository;
    }

    public List<Expense> getAllExpenses() {
        return expenseRepository.findAll();
    }

    @Transactional
    public Expense saveExpense(Expense expense) {
        // Gider eklenirken toplam geliri düşür
        Optional<Income> latestIncomeOptional = incomeRepository.findTopByOrderByIdDesc();
        if (latestIncomeOptional.isPresent()) {
            Income latestIncome = latestIncomeOptional.get();
            double updatedTotalIncome = latestIncome.getTotalIncome() - expense.getAmount();
            latestIncome.setTotalIncome(updatedTotalIncome);
            incomeRepository.save(latestIncome);
        }
        return expenseRepository.save(expense);
    }

    public double getTotalExpense() {
        return expenseRepository.findAll()
                .stream()
                .mapToDouble(Expense::getAmount)
                .sum();
    }
}
