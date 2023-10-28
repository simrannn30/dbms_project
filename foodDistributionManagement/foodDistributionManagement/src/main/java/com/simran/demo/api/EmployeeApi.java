package com.simran.demo.api;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.simran.demo.model.Employees;
import com.simran.demo.model.Deliveries;
import com.simran.demo.services.EmployeeService;

@RestController
@RequestMapping("api/v1/employee")
public class EmployeeApi {
    
    private EmployeeService employeeService;

    public EmployeeApi(EmployeeService employeeService){
        this.employeeService=employeeService;
    }

    @PostMapping
    public int insertEmployee(@RequestBody Employees employee) {
        return employeeService.insertEmployee(employee);
    }

    @GetMapping(path = "{id}")
    public Employees getEmployeeByID(@PathVariable("id") int id) {
        return employeeService.getEmployeeByID(id);
    }
    @GetMapping(path="contact/{contact}")
    public Employees getEmployeeByContact(@PathVariable("contact") String contact) {
        return employeeService.getEmployeeByContact(contact);
    }

    @PutMapping(path = "{id}")
    public int updateEmployee(@PathVariable("id") int id,@RequestBody Employees employee) {
        return employeeService.updateEmployee(id,employee);
    }

    @DeleteMapping(path = "{id}")
    public int deleteEmployee(@PathVariable("id") int id) {
        return employeeService.deleteEmployee(id);
    }

    @GetMapping
    public List<Employees> getAllEmployee(){
        return employeeService.getAllEmployee();
    }

    @GetMapping(path = "{deliveryid}")
    public List<Deliveries> getServedByEmployee(@PathVariable("deliveryid") int deliveryid){
        return employeeService.getServedByEmployee(deliveryid);
    }
}
