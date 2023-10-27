package com.simran.demo.model;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@AllArgsConstructor
public class Employees {
    private int E_ID;
    private String E_Name;
    private String Designation;
    private String Contact;
    private String Email;
    private String Address;
    private Long Salary;
    public int getE_ID() {
        return E_ID;
    }
    public void setE_ID(int e_ID) {
        this.E_ID = e_ID;
    }
    public String getE_Name() {
        return E_Name;
    }
    public void setE_Name(String e_Name) {
        this.E_Name = e_Name;
    }
    public String getDesignation() {
        return Designation;
    }
    public void setDesignation(String designation) {
        this.Designation = designation;
    }
    public String getContact() {
        return Contact;
    }
    public void setContact(String contact) {
        this.Contact = contact;
    }
    public String getAddress() {
        return Address;
    }
    public void setAddress(String address) {
        this.Address = address;
    }
    public Long getSalary() {
        return Salary;
    }
    public void setSalary(Long salary) {
        this.Salary = salary;
    }
    public String getEmail() {
        return Email;
    }
    public void setEmail(String email) {
        this.Email = email;
    }
    private String Password;
    public String getPassword() {
        return Password;
    }
    public void setPassword(String password) {
        this.Password = password;
    }
}