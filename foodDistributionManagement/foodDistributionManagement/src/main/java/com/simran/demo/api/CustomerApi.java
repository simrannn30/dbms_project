package com.simran.demo.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.simran.demo.model.Clients;
import com.simran.demo.services.CustomerService;

@RestController
@RequestMapping("api/v1/customer")
public class CustomerApi {
    
    private CustomerService customerService;

    @Autowired
    public CustomerApi(CustomerService customerService){
        this.customerService=customerService;
    }

    @PostMapping
    public int insertCustomer(@RequestBody Clients customer) {
        return customerService.insertCustomer(customer);
    }

    @GetMapping(path = "{id}")
    public Clients getCustomerByID(@PathVariable("id") int id) {
        return customerService.getCustomerByID(id);
    }
    @GetMapping(path="phone/{phone}")
    public Clients getCustomerByNumber(@PathVariable("phone") String phone) {
        return customerService.getCustomerByNumber(phone);
    }

    @PutMapping(path = "{id}")
    public int updateCustomer(@PathVariable("id") int id,@RequestBody Clients customer) {
        return customerService.updateCustomer(id,customer);
    }

    @DeleteMapping(path = "{id}")
    public int deleteCustomer(@PathVariable("id") int id) {
        return customerService.deleteCustomer(id);
    }

    @GetMapping
    public List<Clients> getAllCustomer(){
        return customerService.getAllCustomer();
    }
}
