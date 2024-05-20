package com.yunuskaya.taxcalculation.Service;

import com.yunuskaya.taxcalculation.model.Income;
import com.yunuskaya.taxcalculation.repository.IncomeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class IncomeService {

    private  final IncomeRepository incomeRepository;

    public IncomeService(IncomeRepository incomeRepository) {
        this.incomeRepository = incomeRepository;
    }

    public List<Income> getAllIncomes() {
        return incomeRepository.findAll();
    }

    public Income saveIncome(Income income) {
        double currentTotalIncome = getTotalIncome();
        income.setTotalIncome(currentTotalIncome + income.getAmount());
        return incomeRepository.save(income);
    }

    public double getTotalIncome() {
        List<Income> incomes = incomeRepository.findAll();
        return incomes.stream().mapToDouble(Income::getTotalIncome).sum();
    }
}
