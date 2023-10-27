package com.simran.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.simran.demo.dao.orderDAO;
import com.simran.demo.model.Orders;

@Service
public class OrderService {
    private orderDAO OrderDAO;

    public OrderService(@Qualifier("order_mysql_repo") orderDAO orderDAO) {
        this.OrderDAO = orderDAO;
    }

    public int insertOrders(Orders order) {
        return OrderDAO.insertOrders(order);
    }

    public Orders getOrdersByID(String id) {
        return OrderDAO.getOrdersByID(id);
    }

    public int updateOrders(String id, Orders order) {
        return OrderDAO.updateOrders(id, order);
    }

    public int deleteOrders(String id) {
        return OrderDAO.deleteOrders(id);
    }

    public List<Orders> getAllOrders() {
        return OrderDAO.getAllOrders();
    }

    public List<Orders> getOrdersfromManufacturer(String id) {
        return OrderDAO.getOrdersfromManufacturer(id);
    }
}
