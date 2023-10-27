package com.simran.demo.dao;
import com.simran.demo.model.Employees;
import com.simran.demo.model.Deliveries;
import java.util.List;

public interface employeeDAO {
    int insertEmployee(Employees employee);
    //INSERT INTO Employees VALUES (employee);
    int deleteEmployee(int id);
    //DELETE FROM Employees WHERE E_ID = id;
    int updateEmployee(int id, Employees employee);
    //UPDATE Employees SET E_Name=?, Designation=?,Contact=?,Address=?,Salary=? WHERE E_ID=id;
    Employees getEmployeeByID(int id);
    //SELECT * from Employees where E_Id =id;
    Employees getEmployeeByContact(String contact);
    //SELECT * FROM Employees WHERE Contact=contact;
    List<Employees> getAllEmployee();
    //SELECT * from Employees ;
    List<Deliveries> getServedByEmployee(int id);
    //SELECT * from Deliveries where Employee_Assigned=id;
    int updatePassword(int id, String password);
}
