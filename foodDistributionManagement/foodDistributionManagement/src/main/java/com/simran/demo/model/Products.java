package com.simran.demo.model;

public class Products {
    private String P_ID;
    private String P_Name;
    private String M_ID;
    private Long Stock;
    public String getP_ID() {
        return P_ID;
    }
    public void setP_ID(String p_ID) {
        P_ID = p_ID;
    }
    public String getP_Name() {
        return P_Name;
    }
    public void setP_Name(String p_Name) {
        P_Name = p_Name;
    }
    public String getM_ID() {
        return M_ID;
    }
    public void setM_ID(String m_ID) {
        M_ID = m_ID;
    }
    public Long getStock() {
        return Stock;
    }
    public void setStock(Long stock) {
        Stock = stock;
    }
}
