package com.yunuskaya.taxcalculation.Controller;
import com.yunuskaya.taxcalculation.Service.IncomeService;
import com.yunuskaya.taxcalculation.model.Income;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/incomes")
public class IncomeController {

    private final  IncomeService incomeService;

    public IncomeController(IncomeService incomeService) {
        this.incomeService = incomeService;
    }

    @GetMapping
    public List<Income> getAllIncomes() {
        return incomeService.getAllIncomes();
    }
    @PostMapping
    public Income saveIncome(@RequestBody Income income) {
        return incomeService.saveIncome(income);
    }

    @GetMapping("/total")
    public double getTotalIncome() {
        return incomeService.getTotalIncome();
    }


}


