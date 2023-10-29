package com.simran.demo.dao;

import java.util.List;

import com.simran.demo.model.BillingDetails;

public interface billingDAO {
    int insertOrderItem(BillingDetails OrderItem);
    // INSERT INTO CustomerOrderItem VALUES (customerOrderItem);
    // productid also needed
    // int updateOrderItem(String id, String pid, BillingDetails OrderItem);
    int deleteOrderItem(String id, String pid, BillingDetails OrderItem);
    List<BillingDetails> getCustomerOrderItemByCustomerOrder(String id);
    // SELECT * FROM CustomerOrderItem WHERE CustomerOrderItem.orderID =
    // customerOrder.orderID;
    List<BillingDetails> getSupplierOrderItemBySupplierOrder(String id);
}
