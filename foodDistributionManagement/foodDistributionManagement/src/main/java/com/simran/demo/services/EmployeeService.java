package com.simran.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.simran.demo.dao.employeeDAO;
import com.simran.demo.model.Employees;

@Service
public class EmployeeService {
    private employeeDAO employeeDAO;

    public EmployeeService(@Qualifier("employee_mysql_repo") employeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    public int insertEmployee(Employees employee) {
        return employeeDAO.insertEmployee(employee);
    }

    public Employees getEmployeeByID(String id) {
        return employeeDAO.getEmployeeByID(id);
    }

    public Employees getEmployeeByContact(String contact) {
        return employeeDAO.getEmployeeByContact(contact);
    }

    public int updateEmployee(String id, Employees employee) {
        return employeeDAO.updateEmployee(id, employee);
    }

    public int deleteEmployee(String id) {
        return employeeDAO.deleteEmployee(id);
    }

    public List<Employees> getAllEmployee() {
        return employeeDAO.getAllEmployee();
    }
}
