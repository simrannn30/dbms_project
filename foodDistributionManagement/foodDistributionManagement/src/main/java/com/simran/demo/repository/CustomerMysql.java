package com.simran.demo.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.DeprecatedConfigurationProperty;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.simran.demo.dao.customerDAO;
import com.simran.demo.model.Clients;


@Repository("customer_mysql_repo")
public class CustomerMysql implements customerDAO {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public int insertCustomer(Clients customer) {
        String query = "INSERT INTO Customer(name,phone,email,DOB,address) VALUES (?,?,?,?,?);";
        Object[] args = new Object[] {
                customer.getC_Name(),
                customer.getContact(),
                customer.getAddress(),
        };
        return jdbcTemplate.update(query,args);
    }

    @Override
    public Clients getCustomerByID(int id) {
        String query = "SELECT * FROM Customer WHERE customerID = ?;";
        Object[] args = new Object[]{
                id
        };
        return jdbcTemplate.queryForObject(query,args, BeanPropertyRowMapper.newInstance(Clients.class));
    }

    @Override
    public Clients getCustomerByNumber(String phone) {
        String query = "SELECT * FROM Customer WHERE Customer.phone=?;";
        Object[] args = new Object[]{
                phone
        };
        return jdbcTemplate.queryForObject(query,args, BeanPropertyRowMapper.newInstance(Clients.class));
    }


    @Override
    public int updateCustomer(int id, Clients customer) {
        String query="UPDATE Customer SET name=?,phone=?,address=? WHERE customerID = ?;";
        Object[] args = new Object[]{
                customer.getC_Name(),
                customer.getContact(),
                customer.getAddress(),
                id
        };
        return jdbcTemplate.update(query,args);
    }

    @Override
    public int deleteCustomer(int id) {
        String query="DELETE FROM Customer WHERE customerID = ?;";
        Object[] args = new Object[]{
                id
        };
        return jdbcTemplate.update(query,args);
    }

    @Override
    public List<Clients> getAllCustomer(){
        String query = "select * from Customer ;";
        return jdbcTemplate.query(query,BeanPropertyRowMapper.newInstance(Clients.class));

    }
}
