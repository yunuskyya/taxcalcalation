package com.yunuskaya.taxcalculation.Service;

import com.yunuskaya.taxcalculation.model.TaxReturn;
import com.yunuskaya.taxcalculation.repository.TaxReturnRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TaxReturnService {

    private final IncomeService incomeService;

    private final ExpenseService expenseService;

    private final TaxService taxService;

    private final TaxReturnRepository taxReturnRepository;

    public TaxReturnService(IncomeService incomeService, ExpenseService expenseService, TaxService taxService, TaxReturnRepository taxReturnRepository) {
        this.incomeService = incomeService;
        this.expenseService = expenseService;
        this.taxService = taxService;
        this.taxReturnRepository = taxReturnRepository;
    }


    public TaxReturn createTaxReturn() {
        double totalIncome = incomeService.getTotalIncome();
        double totalExpenses = expenseService.getTotalExpense();
        double taxableIncome = totalIncome - totalExpenses;
        double taxAmount = taxService.calculateTax();

        TaxReturn taxReturn = new TaxReturn();
        taxReturn.setDate(LocalDate.now());
        taxReturn.setTotalIncome(totalIncome);
        taxReturn.setTotalExpense(totalExpenses);
        taxReturn.setTaxableIncome(taxableIncome);
        taxReturn.setTaxAmount(taxAmount);

        return taxReturnRepository.save(taxReturn);
    }


    public List<TaxReturn> findAll() {
        return taxReturnRepository.findAll();
    }

    public TaxReturn findById(Long id) {
        return taxReturnRepository.findById(id).orElseThrow(() -> new RuntimeException("Tax Return not found"));
    }
}
