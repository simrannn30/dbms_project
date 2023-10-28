package com.simran.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.simran.demo.dao.productDAO;
import com.simran.demo.model.Products;

@Service
public class ProductService {
    private productDAO ProductDAO;

    public ProductService(@Qualifier("product_mysql_repo") productDAO productDAO) {
        this.ProductDAO = productDAO;
    }

    public int insertProduct(Products product) {
        return ProductDAO.insertProduct(product);
    }

    public Products getProductByID(String id) {
        return ProductDAO.getProductByID(id);
    }

    public int updateProduct(String id, Products product) {
        return ProductDAO.updateProduct(id, product);
    }

    public int deleteProduct(String id) {
        return ProductDAO.deleteProduct(id);
    }

    public List<Products> getAllProduct() {
        return ProductDAO.getAllProduct();
    }
}
