package com.simran.demo.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.simran.demo.dao.orderDAO;
import com.simran.demo.model.Orders;


@Repository("order_mysql_repo")
public class OrderMysql implements orderDAO{

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public int insertOrders(Orders order) {
        String query = "INSERT INTO Orders(O_ID,M_ID,O_Date,Amount) VALUES (?,?,?,?);";
        Object[] args = new Object[] {
                order.getO_ID(),
                order.getM_ID(),
                order.getO_Date(),
                order.getAmount(),
        };
        return jdbcTemplate.update(query,args);
    }

    @Override
    @Deprecated
    public Orders getOrdersByID(String id) {
        String query = "SELECT * FROM Orders WHERE O_ID = ?;";
        Object[] args = new Object[]{
                id
        };
        return jdbcTemplate.queryForObject(query,args, BeanPropertyRowMapper.newInstance(Orders.class));
    }

    @Override
    public int updateOrders(String id, Orders order) {
        String query="UPDATE Orders SET M_ID=?,O_Date=?,Amount=? WHERE O_ID = ?;";
        Object[] args = new Object[]{
                order.getM_ID(),
                order.getO_Date(),
                order.getAmount(),
                id
        };
        return jdbcTemplate.update(query,args);
    }

    @Override
    public int deleteOrders(String id) {
        String query="DELETE FROM Orders WHERE O_ID = ?;";
        Object[] args = new Object[]{
                id
        };
        return jdbcTemplate.update(query,args);
    }

    @Override
    public List<Orders> getAllOrders(){
        String query = "select * from Orders ;";
        return jdbcTemplate.query(query,BeanPropertyRowMapper.newInstance(Orders.class));
    }

    @Override
    public List<Orders> getOrdersfromManufacturer(String manufacturerid){
    String query = "select * from Orders where M_ID = ?;";
    Object[] args = new Object[]{
                manufacturerid
        };
    return jdbcTemplate.query(query,args, BeanPropertyRowMapper.newInstance(Orders.class));
    }
}
