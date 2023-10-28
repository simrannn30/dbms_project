package com.simran.demo.model;

public class BillingDetails {
    private String Bill_No;
    private String P_ID;
    private Long Quantity;
    private Long Rate;
    public String getBill_No() {
        return Bill_No;
    }
    public void setBill_No(String bill_No) {
        Bill_No = bill_No;
    }
    public String getP_ID() {
        return P_ID;
    }
    public void setP_ID(String p_ID) {
        P_ID = p_ID;
    }
    public Long getQuantity() {
        return Quantity;
    }
    public void setQuantity(Long quantity) {
        Quantity = quantity;
    }
    public Long getRate() {
        return Rate;
    }
    public void setRate(Long rate) {
        Rate = rate;
    }
}
