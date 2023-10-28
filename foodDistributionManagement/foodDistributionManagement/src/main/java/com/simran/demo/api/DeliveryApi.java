package com.simran.demo.api;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.simran.demo.model.BillingDetails;
import com.simran.demo.model.Deliveries;
import com.simran.demo.services.BillingService;
import com.simran.demo.services.DeliveryService;

import java.util.List;

@RestController
@RequestMapping("api/v1/delivery")
public class DeliveryApi {
    
    private DeliveryService deliveryService;
    private BillingService billingService;

    public DeliveryApi(DeliveryService deliveryService){
        this.deliveryService=deliveryService;
    }

    @PostMapping
    public int insertDeliveries(@RequestBody Deliveries delivery) {
        return deliveryService.insertDeliveries(delivery);
    }

    @GetMapping(path = "{id}")
    public Deliveries getDeliveriesByID(@PathVariable("id") String id) {
        return deliveryService.getDeliveriesByID(id);
    }

    @PutMapping(path = "{id}")
    public int updateDeliveries(@PathVariable("id") String id,@RequestBody Deliveries delivery) {
        return deliveryService.updateDeliveries(id,delivery);
    }

    @DeleteMapping(path = "{id}")
    public int deleteDeliveries(@PathVariable("id") String id) {
        return deliveryService.deleteDeliveries(id);
    }

    @GetMapping
    public List<Deliveries> getAllDeliveries(){
        return deliveryService.getAllDeliveries();
    }

    @GetMapping(path = "{clientid}")
    public List<Deliveries> getDeliveriesofClients(@PathVariable("clientid") String clientid){
        return deliveryService.getDeliveriesofClients(clientid);
    }

    @GetMapping(path = "/view/{id}")
    public List<BillingDetails> getCustomerOrderItemByCustomerOrder(@PathVariable("id") String id){
        return billingService.getCustomerOrderItemByCustomerOrder(id);
    }
}
