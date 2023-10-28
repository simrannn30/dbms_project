package com.simran.demo.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.simran.demo.dao.billingDAO;
import com.simran.demo.model.BillingDetails;

@Repository("billing_mysql_repo")
public class BillingMysql implements billingDAO {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public int insertOrderItem(BillingDetails OrderItem) {
        String query = "insert into Billing_Details(Bill_No, P_ID, Quantity, Rate) values(?,?,?,?);";
        Object[] args = new Object[] {
                OrderItem.getBill_No(),
                OrderItem.getP_ID(),
                OrderItem.getQuantity(),
                OrderItem.getRate()
        };
        return jdbcTemplate.update(query, args);
    }

    @Override
    public int deleteOrderItem(String id, String pid, BillingDetails OrderItem) {
        String query = "Update Billing_Details set P_ID=?, Quantity=?, Rate=? where Bill_No=? and P_ID=?;";
        Object[] args = new Object[] {
                OrderItem.getP_ID(),
                OrderItem.getQuantity(),
                OrderItem.getRate(),
                id,
                pid
        };
        return jdbcTemplate.update(query, args);
    }

    @Override
    public List<BillingDetails> getCustomerOrderItemByCustomerOrder(String id) {
        String query = "Select * from Billing_Details where Bill_No=?;";
        Object[] args = new Object[] {
                id
        };
        return jdbcTemplate.query(query, args, BeanPropertyRowMapper.newInstance(BillingDetails.class));
    }

    @Override
    public List<BillingDetails> getSupplierOrderItemBySupplierOrder(String id) {
        String query = "Select * from Billing_Details where Bill_No=?;";
        Object[] args = new Object[] {
                id
        };
        return jdbcTemplate.query(query, args, BeanPropertyRowMapper.newInstance(BillingDetails.class));
    }

}
