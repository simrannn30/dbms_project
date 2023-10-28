package com.simran.demo.services;

import com.simran.demo.dao.billingDAO;
import com.simran.demo.model.BillingDetails;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class BillingService {
    public billingDAO billingDAO;

    public BillingService(@Qualifier("billing_mysql_repo") billingDAO billingDAO) {
        this.billingDAO = billingDAO;
    }

    public int insertOrderItem(BillingDetails OrderItem){
        return billingDAO.insertOrderItem(OrderItem);
    }
    public int deleteOrderItem(String id, String pid, BillingDetails OrderItem){
        return billingDAO.deleteOrderItem(id, pid, OrderItem);
    }
    public List<BillingDetails> getCustomerOrderItemByCustomerOrder(String id){
        return billingDAO.getCustomerOrderItemByCustomerOrder(id);
    }
    public List<BillingDetails> getSupplierOrderItemBySupplierOrder(String id){
        return billingDAO.getSupplierOrderItemBySupplierOrder(id);
    }
}
