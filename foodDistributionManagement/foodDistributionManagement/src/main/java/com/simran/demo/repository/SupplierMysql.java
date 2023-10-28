package com.simran.demo.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.simran.demo.dao.supplierDAO;
import com.simran.demo.model.Manufacturers;

@Repository("supplier_mysql_repo")
public class SupplierMysql implements supplierDAO{
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public int insertSupplier(Manufacturers supplier) {
        String query = "INSERT INTO Manufacturers(M_ID, M_Name, Contact, Address, POC) VALUES (?,?,?,?,?);";
        Object[] args = new Object[] {
                supplier.getM_ID(),
                supplier.getM_Name(),
                supplier.getContact(),
                supplier.getAddress(),
                supplier.getPOC()
        };
        return jdbcTemplate.update(query,args);
    }

    @Override
    @Deprecated
    public Manufacturers getSupplierByID(String id) {
        String query = "SELECT * FROM manufacturers WHERE M_ID = ?;";
        Object[] args = new Object[]{
                id
        };
        return jdbcTemplate.queryForObject(query,args, BeanPropertyRowMapper.newInstance(Manufacturers.class));
    }


    @Override
    public int updateSupplier(String id, Manufacturers supplier) {
        String query="UPDATE Manufacturers SET M_Name=?,Contact=?,Address=?, POC=? WHERE M_ID = ?;";
        Object[] args = new Object[]{
                supplier.getM_Name(),
                supplier.getContact(),
                supplier.getAddress(),
                supplier.getPOC(),
                id
        };
        return jdbcTemplate.update(query,args);
    }

    @Override
    public int deleteSupplier(String id) {
        String query="DELETE FROM Manufacturers WHERE M_ID = ?;";
        Object[] args = new Object[]{
                id
        };
        return jdbcTemplate.update(query,args);
    }

    @Override
    public List<Manufacturers> getAllSuppliers(){
        String query = "select * from Manufacturers ;";
        return jdbcTemplate.query(query,BeanPropertyRowMapper.newInstance(Manufacturers.class));
    }
}
