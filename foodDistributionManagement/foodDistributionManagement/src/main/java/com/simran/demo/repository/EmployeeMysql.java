package com.simran.demo.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.simran.demo.dao.employeeDAO;
import com.simran.demo.model.Deliveries;
import com.simran.demo.model.Employees;


@Repository("employee_mysql_repo")
public class EmployeeMysql implements employeeDAO {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public int insertEmployee(Employees employee) {
        String query = "INSERT INTO Employees(E_ID, E_Name, Designation, Contact, Address, Salary) VALUES (?,?,?,?,?,?);";
        Object[] args = new Object[] {
                employee.getE_ID(),
                employee.getE_Name(),
                employee.getDesignation(),
                employee.getContact(),
                employee.getAddress(),
                employee.getSalary(),
        };
        return jdbcTemplate.update(query,args);
    }

    @Override
    @Deprecated
    public Employees getEmployeeByID(String id) {
        String query = "SELECT * FROM Employees WHERE E_ID = ?;";
        Object[] args = new Object[]{
                id
        };
        return jdbcTemplate.queryForObject(query,args, BeanPropertyRowMapper.newInstance(Employees.class));
    }

    @Override
    @Deprecated
    public Employees getEmployeeByContact(String phone) {
        String query = "SELECT * FROM Employees WHERE Employees.Contact=?;";
        Object[] args = new Object[]{
                phone
        };
        return jdbcTemplate.queryForObject(query,args, BeanPropertyRowMapper.newInstance(Employees.class));
    }


    @Override
    public int updateEmployee(String id, Employees employee) {
        String query="UPDATE Employees SET E_Name=?,Designation=?,Contact=?,Address=?,Salary=? WHERE E_ID = ?;";
        Object[] args = new Object[]{
                employee.getE_Name(),
                employee.getDesignation(),
                employee.getContact(),
                employee.getAddress(),
                employee.getSalary(),
                id
        };
        return jdbcTemplate.update(query,args);
    }

    @Override
    public int deleteEmployee(String id) {
        String query="DELETE FROM Employees WHERE E_ID = ?;";
        Object[] args = new Object[]{
                id
        };
        return jdbcTemplate.update(query,args);
    }

    @Override
    public List<Employees> getAllEmployee(){
        String query = "select * from Employees ;";
        return jdbcTemplate.query(query,BeanPropertyRowMapper.newInstance(Employees.class));
    }

    @Override
    @Deprecated
    public List<Deliveries> getServedByEmployee(String id) {
        String query = "select * from Deliveries where Employee_Assigned=?;";
        Object[] args = new Object[]{
                id
        };
        return jdbcTemplate.query(query,args,BeanPropertyRowMapper.newInstance(Deliveries.class));
    }

    @Override
    public int updatePassword(String id, String password) {
        String query = "UPDATE Employees SET password=? WHERE E_ID=?";
        Object[] args = new Object[]{
                password,
                id
        };
        return jdbcTemplate.update(query,args);
    }
}
