package com.simran.demo.dao;

import java.util.List;

import com.simran.demo.model.Products;

public interface productDAO {
     int insertProduct(Products product);
    //INSERT INTO Product VALUES (product);
    Products getProductByID(String id);
    //SELECT * FROM Product WHERE productID = id;
    int updateProduct(String id,Products product);
    //UPDATE Product SET name=?,phone=?,email=?,dateOfBirth=?,address=? WHERE productID = id;
    int deleteProduct(String id);
    //DELETE FROM Product WHERE productID = id;
    List<Products> getAllProduct();
}
