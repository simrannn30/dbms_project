package com.simran.demo.model;
import java.util.Date;

public class Deliveries {
    private String D_ID;
    private String C_ID;
    private String Employee_Assigned;
    private String D_Status;
    private Date D_Date;
    private Long Amount;
    private String P_Status;
    public String getD_ID() {
        return D_ID;
    }
    public void setD_ID(String d_ID) {
        D_ID = d_ID;
    }
    public String getC_ID() {
        return C_ID;
    }
    public void setC_ID(String c_ID) {
        C_ID = c_ID;
    }
    public String getEmployee_Assigned() {
        return Employee_Assigned;
    }
    public void setEmployee_Assigned(String employee_Assigned) {
        Employee_Assigned = employee_Assigned;
    }
    public String getD_Status() {
        return D_Status;
    }
    public void setD_Status(String d_Status) {
        D_Status = d_Status;
    }
    public Date getD_Date() {
        return D_Date;
    }
    public void setD_Date(Date d_Date) {
        D_Date = d_Date;
    }
    public Long getAmount() {
        return Amount;
    }
    public void setAmount(Long amount) {
        Amount = amount;
    }
    public String getP_Status() {
        return P_Status;
    }
    public void setP_Status(String p_Status) {
        P_Status = p_Status;
    }
}
