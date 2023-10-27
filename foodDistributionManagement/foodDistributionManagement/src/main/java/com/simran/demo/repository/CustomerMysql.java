package com.simran.demo.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
        String query = "INSERT INTO Clients(C_ID,C_Name,Contact,Address) VALUES (?,?,?,?);";
        Object[] args = new Object[] {
                customer.getC_ID(),
                customer.getC_Name(),
                customer.getContact(),
                customer.getAddress(),
        };
        return jdbcTemplate.update(query,args);
    }

    @Override
    @Deprecated
    public Clients getCustomerByID(String id) {
        String query = "SELECT * FROM Clients WHERE C_ID = ?;";
        Object[] args = new Object[]{
                id
        };
        return jdbcTemplate.queryForObject(query,args, BeanPropertyRowMapper.newInstance(Clients.class));
    }

    @Override
    @Deprecated
    public Clients getCustomerByNumber(String phone) {
        String query = "SELECT * FROM Clients WHERE Clients.Contact=?;";
        Object[] args = new Object[]{
                phone
        };
        return jdbcTemplate.queryForObject(query,args, BeanPropertyRowMapper.newInstance(Clients.class));
    }


    @Override
    public int updateCustomer(String id, Clients customer) {
        String query="UPDATE Clients SET C_Name=?,Contact=?,Address=? WHERE C_ID = ?;";
        Object[] args = new Object[]{
                customer.getC_Name(),
                customer.getContact(),
                customer.getAddress(),
                id
        };
        return jdbcTemplate.update(query,args);
    }

    @Override
    public int deleteCustomer(String id) {
        String query="DELETE FROM Clients WHERE C_ID = ?;";
        Object[] args = new Object[]{
                id
        };
        return jdbcTemplate.update(query,args);
    }

    @Override
    public List<Clients> getAllCustomer(){
        String query = "select * from Clients ;";
        return jdbcTemplate.query(query,BeanPropertyRowMapper.newInstance(Clients.class));
    }
}
