package com.simran.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.simran.demo.dao.customerDAO;
import com.simran.demo.model.Clients;

@Service
public class CustomerService {
    private customerDAO CustomerDAO;

    @Autowired
    public CustomerService(@Qualifier("customer_mysql_repo") customerDAO customerDAO) {
        this.CustomerDAO = customerDAO;
    }

    public int insertCustomer(Clients customer) {
        return CustomerDAO.insertCustomer(customer);
    }

    public Clients getCustomerByID(int id) {
        return CustomerDAO.getCustomerByID(id);
    }

    public Clients getCustomerByNumber(String phone) {
        return CustomerDAO.getCustomerByNumber(phone);
    }

    public int updateCustomer(int id, Clients customer) {
        return CustomerDAO.updateCustomer(id, customer);
    }

    public int deleteCustomer(int id) {
        return CustomerDAO.deleteCustomer(id);
    }

    public List<Clients> getAllCustomer() {
        return CustomerDAO.getAllCustomer();
    }
}
