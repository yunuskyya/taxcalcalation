package com.yunuskaya.taxcalculation.Controller;

import com.yunuskaya.taxcalculation.Service.ExpenseService;
import com.yunuskaya.taxcalculation.model.Expense;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {

    private final ExpenseService expenseService;

    public ExpenseController (ExpenseService expenseService){
        this.expenseService = expenseService;
    }
    @GetMapping
    public List<Expense> getAllExpense(){
        return expenseService.getAllExpenses();
    }

    @PostMapping
    public Expense createExpense(@RequestBody Expense expense){
        return expenseService.saveExpense(expense);

    }

    @GetMapping("/total")
    public double getTotalExpense(){
        return expenseService.getTotalExpense();
    }
}
