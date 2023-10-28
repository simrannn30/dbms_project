package com.simran.demo.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.simran.demo.dao.deliveryDAO;
import com.simran.demo.model.Deliveries;


@Repository("delivery_mysql_repo")
public class DeliveryMysql implements deliveryDAO{

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public int insertDeliveries(Deliveries delivery) {
        String query = "INSERT INTO Deliveries(D_ID,C_ID,Employee_Assigned,D_Status,D_Date,Amount,P_Status) VALUES (?,?,?,?,?,?,?);";
        Object[] args = new Object[] {
                delivery.getD_ID(),
                delivery.getC_ID(),
                delivery.getEmployee_Assigned(),
                delivery.getD_Status(),
                delivery.getD_Date(),
                delivery.getAmount(),
                delivery.getP_Status(),
        };
        return jdbcTemplate.update(query,args);
    }

    @Override
    @Deprecated
    public Deliveries getDeliveriesByID(String id) {
        String query = "SELECT * FROM Deliveries WHERE D_ID = ?;";
        Object[] args = new Object[]{
                id
        };
        return jdbcTemplate.queryForObject(query,args, BeanPropertyRowMapper.newInstance(Deliveries.class));
    }

    @Override
    public int updateDeliveries(String id, Deliveries delivery) {
        String query="UPDATE Deliveries SET C_ID=?,Employee_Assigned=?,D_Status=?,D_Date=?,Amount=?,P_Status=? WHERE D_ID = ?;";
        Object[] args = new Object[]{
                delivery.getC_ID(),
                delivery.getEmployee_Assigned(),
                delivery.getD_Status(),
                delivery.getD_Date(),
                delivery.getAmount(),
                delivery.getP_Status(),
                id
        };
        return jdbcTemplate.update(query,args);
    }

    @Override
    public int deleteDeliveries(String id) {
        String query="DELETE FROM Deliveries WHERE D_ID = ?;";
        Object[] args = new Object[]{
                id
        };
        return jdbcTemplate.update(query,args);
    }

    @Override
    public List<Deliveries> getAllDeliveries(){
        String query = "select * from Deliveries ;";
        return jdbcTemplate.query(query,BeanPropertyRowMapper.newInstance(Deliveries.class));
    }

    @Override
    public List<Deliveries> getDeliveriesofClients(String clientid){
    String query = "select * from Deliveries where C_ID = ?;";
    Object[] args = new Object[]{
                clientid
        };
    return jdbcTemplate.query(query,args, BeanPropertyRowMapper.newInstance(Deliveries.class));
    }
}
