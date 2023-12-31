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
import com.simran.demo.dao.deliveryDAO;
import com.simran.demo.dao.employeeDAO;
import com.simran.demo.dao.productDAO;
import com.simran.demo.dao.customerDAO;
import com.simran.demo.model.BillingDetails;
import com.simran.demo.model.Deliveries;
import com.simran.demo.model.Products;
import com.simran.demo.model.Employees;
import com.simran.demo.model.Clients;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping
public class deliveriesController {

    @Autowired
    private deliveryDAO deliveryDAO;

    @Autowired
    private billingDAO billingDAO;

    @Autowired
    private productDAO productDAO;

    @Autowired
    private employeeDAO employeeDAO;

    @Autowired
    private customerDAO customerDAO;

    @GetMapping("/CustomerDeliveries")
    public String listDeliveries(Model model, HttpSession session, @RequestParam(required = false) String id,
            @RequestParam(required = false) String clientid) {
        List<Deliveries> deliveries = new ArrayList<>();
        if ((id == null || id == "") && (clientid == null || clientid == "")) {
            
            deliveries = deliveryDAO.getAllDeliveries();
        } else if (clientid == null || clientid == "") {
            try {
                Deliveries del = deliveryDAO.getDeliveriesByID(id);
                deliveries.add(del);
            } catch (Exception e) {
                System.out.println("Delivery NOT FOUND1!");
                // System.out.println(e);
            }
        } else if (id == null || id == "") {
            try {
                List<Deliveries> del = deliveryDAO.getDeliveriesofClients(clientid);

                for (int i = 0; i < del.size(); i++) {
                    deliveries.add(del.get(i));
                }
            } catch (Exception e) {
                System.out.println("Delivery NOT FOUND!");
                // System.out.println(e);
            }
        } else {
            System.out.println("Search by one filter only!");
        }
        model.addAttribute("deliveries", deliveries);
        return "CustomerDeliveries";
    }

    @GetMapping("/CustomerDeliveries/create")
    public String createDelivery(Model model, HttpSession session) {
        // if(!authenticationService.isAuthenticated(session)){
        // return "redirect:/login";
        // }
        List<Employees> employees = employeeDAO.getAllEmployee();
        List<Clients> customers = customerDAO.getAllCustomer();
        Deliveries deliveries = new Deliveries();
        model.addAttribute("deliveries", deliveries);
        model.addAttribute("employees", employees);
        model.addAttribute("customers", customers);
        return "DeliveryCreate";
    }

    @PostMapping("/CustomerDeliveries/create")
    public String createDeliveryPost(@ModelAttribute("deliveries") Deliveries deliveries, HttpSession session) {
        // if(!authenticationService.isAuthenticated(session)){
        // return "redirect:/login";
        // }
        deliveries.setAmount((long)0);
        deliveryDAO.insertDeliveries(deliveries);
        return "redirect:/CustomerDeliveries";
    }

    @GetMapping("/CustomerDeliveries/delete/{id}")
    public String deleteDelivery(@PathVariable("id") String id, HttpSession session) {
        // if(!authenticationService.isAuthenticated(session)){
        // return "redirect:/login";
        // }
        deliveryDAO.deleteDeliveries(id);
        return "redirect:/CustomerDeliveries";
    }

    @GetMapping("/CustomerDeliveries/edit/{id}")
    public String editDelivery(@PathVariable("id") String id, Model model, HttpSession session) {
        // if(!authenticationService.isAuthenticated(session)){
        // return "redirect:/login";
        // }

        Deliveries deliveries = deliveryDAO.getDeliveriesByID(id);
        model.addAttribute("deliveries", deliveries);
        System.out.println(deliveries.getD_Date());
        return "DeliveryEdit.html";
    }

    @PostMapping("/CustomerDeliveries/edit/{id}")
    public String editDeliveryPost(@PathVariable("id") String id, @ModelAttribute("deliveries") Deliveries deliveries,
            HttpSession session) {
        // if(!authenticationService.isAuthenticated(session)){
        // return "redirect:/login";
        // }
        deliveryDAO.updateDeliveries(id, deliveries);
        return "redirect:/CustomerDeliveries";
    }

    @GetMapping("/CustomerDeliveries/view/{id}")
    public String searchCustomerOrder(@PathVariable("id") String id, Model model, HttpSession session) {
        // if(!authenticationService.isAuthenticated(session)){
        // return "redirect:/login";
        // }
        List<BillingDetails> items = new ArrayList<>();
        items = billingDAO.getCustomerOrderItemByCustomerOrder(id);



        model.addAttribute("items", items);

        return "billings";
    }

    
    @GetMapping("/CustomerDeliveries/view/{id}/create")
    public String createCustomerOrder(@PathVariable("id") String id, Model model, HttpSession session) {
        // if(!authenticationService.isAuthenticated(session)){
        // return "redirect:/login";
        // }
        BillingDetails orderItem = new BillingDetails();
        orderItem.setBill_No(id);
        model.addAttribute("orderItem", orderItem);
        return "billingsCreate";
    }

    @PostMapping("/CustomerDeliveries/view/{id}/create")
    public String createCustomerOrder(@ModelAttribute("orderItem") BillingDetails orderItem, HttpSession session, @PathVariable("id") String id) {
        // if(!authenticationService.isAuthenticated(session)){
        // return "redirect:/login";
        // }
        billingDAO.insertOrderItem(orderItem);

        Deliveries deliveries = deliveryDAO.getDeliveriesByID(orderItem.getBill_No());
        deliveries.setAmount(deliveries.getAmount()+orderItem.getRate()*orderItem.getQuantity());
        deliveryDAO.updateDeliveries(deliveries.getD_ID(),deliveries);

        Products products = productDAO.getProductByID(orderItem.getP_ID());
        long x = products.getStock() - orderItem.getQuantity();
        products.setStock(x);
        productDAO.updateProduct(products.getP_ID(), products);
        return "redirect:/CustomerDeliveries/view/{id}";
    }
}
