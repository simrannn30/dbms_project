package com.simran.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.simran.demo.dao.deliveryDAO;
import com.simran.demo.model.Deliveries;

@Service
public class DeliveryService {
    private deliveryDAO DeliveryDAO;

    public DeliveryService(@Qualifier("delivery_mysql_repo") deliveryDAO deliveryDAO) {
        this.DeliveryDAO = deliveryDAO;
    }

    public int insertDeliveries(Deliveries delivery) {
        return DeliveryDAO.insertDeliveries(delivery);
    }

    public Deliveries getDeliveriesByID(String id) {
        return DeliveryDAO.getDeliveriesByID(id);
    }

    public Deliveries getPaidDeliveriesByID(String id) {
        return DeliveryDAO.getPaidDeliveriesByID(id);
    }

    public int updateDeliveries(String id, Deliveries delivery) {
        return DeliveryDAO.updateDeliveries(id, delivery);
    }

    public int deleteDeliveries(String id) {
        return DeliveryDAO.deleteDeliveries(id);
    }

    public List<Deliveries> getAllDeliveries() {
        return DeliveryDAO.getAllDeliveries();
    }

    public List<Deliveries> getPaidDeliveries() {
        return DeliveryDAO.getPaidDeliveries();
    }

    public List<Deliveries> getDeliveriesofClients(String id) {
        return DeliveryDAO.getDeliveriesofClients(id);
    }
}
