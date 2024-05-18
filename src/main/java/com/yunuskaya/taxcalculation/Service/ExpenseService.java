package com.yunuskaya.taxcalculation.Service;

import com.yunuskaya.taxcalculation.model.Expense;
import com.yunuskaya.taxcalculation.model.Income;
import com.yunuskaya.taxcalculation.repository.ExpenseRepository;

import java.util.List;

public class ExpenseService {
    private  ExpenseRepository expenseRepository;




    public List <Expense> getAllExpense(){
        return expenseRepository.findAll();
    }

    public Expense saveExpense(Expense expense){
        return expenseRepository.save(expense);
    }


}
