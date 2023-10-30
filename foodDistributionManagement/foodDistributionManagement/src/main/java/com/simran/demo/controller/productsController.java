package com.simran.demo.controller;
import com.simran.demo.dao.productDAO;
import com.simran.demo.model.Products;
// import com.simran.demo.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping
public class productsController {

    @Autowired
    private productDAO productDAO;

    @GetMapping("/Products")
    public String listProducts(Model model, HttpSession session, @RequestParam(required = false) String id) {
        List<Products> products = new ArrayList<>();
        if (id == null || id == "") {
            products = productDAO.getAllProduct();
        } else {
            String intid = id;
            try {
                Products cust = productDAO.getProductByID(intid);
                products.add(cust);
            } catch (Exception e) {
                System.out.println("Product NOT FOUND!");
                // System.out.println(e);
            }
        }
        model.addAttribute("products", products);
        return "Products";
    }
    @GetMapping ("/Products/create")
    public String createProduct(Model model, HttpSession session)
    {
        // if(!authenticationService.isAuthenticated(session)){
        //     return "redirect:/login";
        // }
        Products products = new Products();
        model.addAttribute("products", products);
        return "ProductCreate";
    }
    
    @PostMapping ("/Products/create")
    public String createProductPost(@ModelAttribute("products") Products products, HttpSession session)
    {
        // if(!authenticationService.isAuthenticated(session)){
        //     return "redirect:/login";
        // }
        products.setStock((long)0);
        productDAO.insertProduct(products);
        return "redirect:/Products";
    }
    @GetMapping ("/Products/delete/{id}")
    public String deleteProduct(@PathVariable("id") String id, HttpSession session)
    {
        // if(!authenticationService.isAuthenticated(session)){
        //     return "redirect:/login";
        // }
        productDAO.deleteProduct(id);
        return "redirect:/Products";
    }
    @GetMapping ("/Products/edit/{id}")
    public String editProduct(@PathVariable("id") String id, Model model, HttpSession session)
    {
        // if(!authenticationService.isAuthenticated(session)){
        //     return "redirect:/login";
        // }
        Products products = productDAO.getProductByID(id);
        model.addAttribute("products", products);
        return "ProductEdit.html";
    }

    @PostMapping ("/Products/edit/{id}")
    public String editProductPost(@PathVariable("id") String id, @ModelAttribute("products") Products products, HttpSession session)
    {
        // if(!authenticationService.isAuthenticated(session)){
        //     return "redirect:/login";
        // }
        productDAO.updateProduct(id,products);
        return "redirect:/Products";
    }
}
