package com.yunuskaya.taxcalculation.Controller;

import com.yunuskaya.taxcalculation.Service.TaxService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tax")
public class TaxController {

    private final TaxService taxService;

    public TaxController(TaxService taxService) {
        this.taxService = taxService;
    }
    @GetMapping("/calculate")
    public double calculateTax(){
        return taxService.calculateTax();
    }

}
