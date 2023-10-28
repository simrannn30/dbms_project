package com.simran.demo.model;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class Orders {
    private String O_ID;
    private String M_ID;
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private Date O_Date;
    private Long Amount;
    public String getO_ID() {
        return O_ID;
    }
    public void setO_ID(String o_ID) {
        O_ID = o_ID;
    }
    public String getM_ID() {
        return M_ID;
    }
    public void setM_ID(String m_ID) {
        M_ID = m_ID;
    }
    public Date getO_Date() {
        return O_Date;
    }
    public void setO_Date(Date o_Date) {
        this.O_Date = o_Date;
    }
    public Long getAmount() {
        return Amount;
    }
    public void setAmount(Long amount) {
        Amount = amount;
    }
}
