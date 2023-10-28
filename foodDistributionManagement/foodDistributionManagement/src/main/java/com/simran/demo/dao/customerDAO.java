package com.simran.demo.dao;
import com.simran.demo.model.Clients;

import java.util.List;

public interface customerDAO {
     int insertCustomer(Clients customer);
    //INSERT INTO Customer VALUES (customer);
    Clients getCustomerByID(String id);
    //SELECT * FROM Customer WHERE customerID = id;
    Clients getCustomerByNumber(String phone);
    //SELECT * FROM Customer WHERE Customer.phone=phone;
    int updateCustomer(String id,Clients customer);
    //UPDATE Customer SET name=?,phone=?,email=?,dateOfBirth=?,address=? WHERE customerID = id;
    int deleteCustomer(String id);
    //DELETE FROM Customer WHERE customerID = id;
    List<Clients> getAllCustomer();
}
