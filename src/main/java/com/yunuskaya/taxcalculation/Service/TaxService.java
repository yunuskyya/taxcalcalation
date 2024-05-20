package com.yunuskaya.taxcalculation.Service;

import org.springframework.stereotype.Service;

@Service
public class TaxService {

    private final IncomeService incomeService;
    private final ExpenseService expenseService;

    public TaxService(IncomeService incomeService, ExpenseService expenseService) {
        this.incomeService = incomeService;
        this.expenseService = expenseService;
    }

    public double calculateTax() {
        double totalIncome = incomeService.getTotalIncome();
        double totalExpenses = expenseService.getTotalExpense();
        double taxableIncome = totalIncome - totalExpenses;
        double taxRate = 0.20; // %20 vergi oranı
        return taxableIncome * taxRate;
    }
}
