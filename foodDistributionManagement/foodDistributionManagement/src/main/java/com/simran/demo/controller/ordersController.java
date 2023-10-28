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

import com.simran.demo.dao.billingDAO;
import com.simran.demo.dao.orderDAO;
import com.simran.demo.model.BillingDetails;
import com.simran.demo.model.Orders;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping
public class ordersController {
    
    @Autowired
    private orderDAO orderDAO;

    @Autowired
    private billingDAO billingDAO;

    @GetMapping("/SupplierOrders")
    public String listOrders(Model model, HttpSession session, @RequestParam(required = false) String id, @RequestParam(required = false) String manufacturerid) {
        List<Orders> orders = new ArrayList<>();
        if ((id == null || id == "") && (manufacturerid == null || manufacturerid == "")) {
            orders = orderDAO.getAllOrders();
        } 
        else if(manufacturerid == null || manufacturerid == ""){
            try {
                Orders del = orderDAO.getOrdersByID(id);
                orders.add(del);
            } catch (Exception e) {
                System.out.println("Order NOT FOUND1!");
                // System.out.println(e);
            }
        }
        else if(id == null || id == ""){
            try {
                System.out.println(manufacturerid);
                List<Orders> del = orderDAO.getOrdersfromManufacturer(manufacturerid);
                
                for(int i=0; i<del.size(); i++){
                    orders.add(del.get(i));
                }
            } catch (Exception e) {
                System.out.println("Order NOT FOUND2!");
                // System.out.println(e);
            }
        }
        else{
            System.out.println("Search by one filter only!");
        }
        model.addAttribute("orders", orders);
        return "SupplierOrders";
    }
    @GetMapping ("/SupplierOrders/create")
    public String createOrder(Model model, HttpSession session)
    {
        // if(!authenticationService.isAuthenticated(session)){
        //     return "redirect:/login";
        // }
        Orders orders = new Orders();
        model.addAttribute("orders", orders);
        return "OrderCreate";
    }
    
    @PostMapping ("/SupplierOrders/create")
    public String createOrderPost(@ModelAttribute("orders") Orders orders, HttpSession session)
    {
        // if(!authenticationService.isAuthenticated(session)){
        //     return "redirect:/login";
        // }
        orderDAO.insertOrders(orders);
        return "redirect:/SupplierOrders";
    }
    @GetMapping ("/SupplierOrders/delete/{id}")
    public String deleteOrder(@PathVariable("id") String id, HttpSession session)
    {
        // if(!authenticationService.isAuthenticated(session)){
        //     return "redirect:/login";
        // }
        orderDAO.deleteOrders(id);
        return "redirect:/SupplierOrders";
    }
    @GetMapping ("/SupplierOrders/edit/{id}")
    public String editOrder(@PathVariable("id") String id, Model model, HttpSession session)
    {
        // if(!authenticationService.isAuthenticated(session)){
        //     return "redirect:/login";
        // }
        
        Orders orders = orderDAO.getOrdersByID(id);
        model.addAttribute("orders", orders);
        System.out.println(orders.getO_Date());
        return "OrderEdit.html";
    }

    @PostMapping ("/SupplierOrders/edit/{id}")
    public String editOrderPost(@PathVariable("id") String id, @ModelAttribute("orders") Orders orders, HttpSession session)
    {
        // if(!authenticationService.isAuthenticated(session)){
        //     return "redirect:/login";
        // }
        orderDAO.updateOrders(id,orders);
        return "redirect:/SupplierOrders";
    }


    @GetMapping("/SupplierOrders/view/{id}")
    public String searchSupplierOrder(@PathVariable("id") String id, Model model, HttpSession session) {
        // if(!authenticationService.isAuthenticated(session)){
        // return "redirect:/login";
        // }
        List<BillingDetails> items = new ArrayList<>();
        items = billingDAO.getSupplierOrderItemBySupplierOrder(id);
        model.addAttribute("items", items);

        return "billings";
    }

}
