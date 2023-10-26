package com.simran.demo.dao;
import com.simran.demo.model.Clients;

import java.util.List;

public interface customerDAO {
     int insertCustomer(Clients customer);
    //INSERT INTO Customer VALUES (customer);
    Clients getCustomerByID(int id);
    //SELECT * FROM Customer WHERE customerID = id;
    Clients getCustomerByNumber(String phone);
    //SELECT * FROM Customer WHERE Customer.phone=phone;
    int updateCustomer(int id,Clients customer);
    //UPDATE Customer SET name=?,phone=?,email=?,dateOfBirth=?,address=? WHERE customerID = id;
    int deleteCustomer(int id);

    List<Clients> getAllCustomer();
    //DELETE FROM Customer WHERE customerID = id;
}
