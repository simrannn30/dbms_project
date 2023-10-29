package com.simran.demo.controller;

import com.simran.demo.dao.*;
import com.simran.demo.model.*;
import org.springframework.stereotype.Controller;
// import com.simran.demo.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping
public class paymentsController {

    @Autowired
    private orderDAO orderDAO;

    @Autowired
    private deliveryDAO deliveryDAO;

    @GetMapping("/Payments")
    public String listPayments(Model model, HttpSession session, @RequestParam(required = false) String id) {
        List<Orders> orders = new ArrayList<>();
        List<Deliveries> deliveries = new ArrayList<>();

        if (id == null || id == "") {
            orders = orderDAO.getAllOrders();
            deliveries = deliveryDAO.getPaidDeliveries();

        } else {
            String intid = id;
            try {
                Orders ords = orderDAO.getOrdersByID(intid);
                orders.add(ords);
            } catch (Exception e) {
                System.out.println("Order NOT FOUND!");
                // System.out.println(e);
            }
            try {
                Deliveries devs = deliveryDAO.getPaidDeliveriesByID(intid); // Replace with your method to get a delivery by ID.
                deliveries.add(devs);
            } catch (Exception e) {
                System.out.println("Delivery NOT FOUND!");
            }
        }
        model.addAttribute("orders", orders);
        model.addAttribute("deliveries", deliveries);
        
        return "Payments"; 
    }
}
