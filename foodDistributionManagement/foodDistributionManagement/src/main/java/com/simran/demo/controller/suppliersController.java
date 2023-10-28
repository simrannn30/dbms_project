package com.simran.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.simran.demo.dao.supplierDAO;
import com.simran.demo.model.Manufacturers;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping
public class suppliersController {
    @Autowired
    private supplierDAO supplierDAO;

    @GetMapping("/suppliers")
    public String listSuppliers(Model model, HttpSession session, @RequestParam(required = false) String id) {
        List<Manufacturers> suppliers = new ArrayList<>();
        if (id == null || id == "") {
            suppliers = supplierDAO.getAllSuppliers();
        } else {
            try {
                Manufacturers cust = supplierDAO.getSupplierByID(id);
                suppliers.add(cust);
            } catch (Exception e) {
                System.out.println("Supplier NOT FOUND!");
                // System.out.println(e);
            }
        }
        model.addAttribute("suppliers", suppliers);
        return "suppliers";
    }
    @GetMapping ("/suppliers/create")
    public String createSupplier(Model model, HttpSession session)
    {
        // if(!authenticationService.isAuthenticated(session)){
        //     return "redirect:/login";
        // }
        Manufacturers suppliers = new Manufacturers();
        model.addAttribute("suppliers", suppliers);
        return "supplierCreate";
    }
    
    @PostMapping ("/suppliers/create")
    public String createCustomerPost(@ModelAttribute("suppliers") Manufacturers suppliers, HttpSession session)
    {
        // if(!authenticationService.isAuthenticated(session)){
        //     return "redirect:/login";
        // }
        supplierDAO.insertSupplier(suppliers);
        return "redirect:/suppliers";
    }
    @GetMapping ("/suppliers/delete/{id}")
    public String deleteSupplier(@PathVariable("id") String id, HttpSession session)
    {
        // if(!authenticationService.isAuthenticated(session)){
        //     return "redirect:/login";
        // }
        supplierDAO.deleteSupplier(id);
        return "redirect:/supplier";
    }
    @GetMapping ("/suppliers/edit/{id}")
    public String editSupplier(@PathVariable("id") String id, Model model, HttpSession session)
    {
        // if(!authenticationService.isAuthenticated(session)){
        //     return "redirect:/login";
        // }
        Manufacturers suppliers = supplierDAO.getSupplierByID(id);
        model.addAttribute("suppliers", suppliers);
        return "supplierEdit.html";
    }

    @PostMapping ("/suppliers/edit/{id}")
    public String editSupplierPost(@PathVariable("id") String id, @ModelAttribute("suppliers") Manufacturers suppliers, HttpSession session)
    {

        // if(!authenticationService.isAuthenticated(session)){
        //     return "redirect:/login";
        // }
        supplierDAO.updateSupplier(id,suppliers);
        return "redirect:/suppliers";
    }
}
