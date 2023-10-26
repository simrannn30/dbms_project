package com.simran.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.simran.demo.model.Clients;
import com.simran.demo.services.CustomerService;

@Controller
@RequestMapping
public class customersController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/Customers")
    public String showCustomerList(Model model) {
        List<Clients> customers = customerService.getAllCustomer();
        model.addAttribute("customers", customers);
        return "Customers"; // This should match the name of your HTML file.
    }
}
