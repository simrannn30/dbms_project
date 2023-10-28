package com.simran.demo.controller;
import com.simran.demo.dao.customerDAO;
import com.simran.demo.model.Clients;
// import com.simran.demo.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping
public class customersController {

    @Autowired
    private customerDAO customerDAO;

    @GetMapping("/Customers")
    public String listCustomers(Model model, HttpSession session, @RequestParam(required = false) String id) {
        List<Clients> customers = new ArrayList<>();
        if (id == null || id == "") {
            customers = customerDAO.getAllCustomer();
        } else {
            String intid = id;
            try {
                Clients cust = customerDAO.getCustomerByID(intid);
                customers.add(cust);
            } catch (Exception e) {
                System.out.println("Customer NOT FOUND!");
                // System.out.println(e);
            }
        }
        model.addAttribute("customers", customers);
        return "Customers";
    }
    @GetMapping ("/Customers/create")
    public String createCustomer(Model model, HttpSession session)
    {
        // if(!authenticationService.isAuthenticated(session)){
        //     return "redirect:/login";
        // }
        Clients customers = new Clients();
        model.addAttribute("customers", customers);
        return "CustomerCreate";
    }
    
    @PostMapping ("/Customers/create")
    public String createCustomerPost(@ModelAttribute("customers") Clients customers, HttpSession session)
    {
        // if(!authenticationService.isAuthenticated(session)){
        //     return "redirect:/login";
        // }
        customerDAO.insertCustomer(customers);
        return "redirect:/Customers";
    }
    @GetMapping ("/Customers/delete/{id}")
    public String deleteCustomer(@PathVariable("id") String id, HttpSession session)
    {
        // if(!authenticationService.isAuthenticated(session)){
        //     return "redirect:/login";
        // }
        customerDAO.deleteCustomer(id);
        return "redirect:/Customers";
    }
    @GetMapping ("/Customers/edit/{id}")
    public String editCustomer(@PathVariable("id") String id, Model model, HttpSession session)
    {
        // if(!authenticationService.isAuthenticated(session)){
        //     return "redirect:/login";
        // }
        Clients customers = customerDAO.getCustomerByID(id);
        model.addAttribute("customers", customers);
        return "CustomerEdit.html";
    }

    @PostMapping ("/Customers/edit/{id}")
    public String editCustomerPost(@PathVariable("id") String id, @ModelAttribute("customers") Clients customers, HttpSession session)
    {
        // if(!authenticationService.isAuthenticated(session)){
        //     return "redirect:/login";
        // }
        customerDAO.updateCustomer(id,customers);
        return "redirect:/Customers";
    }
}
