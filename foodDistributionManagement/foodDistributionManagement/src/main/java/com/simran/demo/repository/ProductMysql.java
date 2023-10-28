package com.simran.demo.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.simran.demo.dao.productDAO;
import com.simran.demo.model.Products;


@Repository("product_mysql_repo")
public class ProductMysql implements productDAO {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public int insertProduct(Products product) {
        String query = "INSERT INTO Products(P_ID,P_Name,M_ID,Stock) VALUES (?,?,?,?);";
        Object[] args = new Object[] {
                product.getP_ID(),
                product.getP_Name(),
                product.getM_ID(),
                product.getStock(),
        };
        return jdbcTemplate.update(query,args);
    }

    @Override
    @Deprecated
    public Products getProductByID(String id) {
        String query = "SELECT * FROM Products WHERE P_ID = ?;";
        Object[] args = new Object[]{
                id
        };
        return jdbcTemplate.queryForObject(query,args, BeanPropertyRowMapper.newInstance(Products.class));
    }

    @Override
    public int updateProduct(String id, Products product) {
        String query="UPDATE Products SET P_Name=?,M_ID=?,Stock=? WHERE P_ID = ?;";
        Object[] args = new Object[]{
                product.getP_Name(),
                product.getM_ID(),
                product.getStock(),
                id
        };
        return jdbcTemplate.update(query,args);
    }

    @Override
    public int deleteProduct(String id) {
        String query="DELETE FROM Products WHERE P_ID = ?;";
        Object[] args = new Object[]{
                id
        };
        return jdbcTemplate.update(query,args);
    }

    @Override
    public List<Products> getAllProduct(){
        String query = "select * from Products ;";
        return jdbcTemplate.query(query,BeanPropertyRowMapper.newInstance(Products.class));
    }
}
