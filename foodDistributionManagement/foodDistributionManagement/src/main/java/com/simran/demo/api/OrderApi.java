package com.simran.demo.api;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.simran.demo.model.Orders;
import com.simran.demo.services.OrderService;

import java.util.List;

@RestController
@RequestMapping("api/v1/order")
public class OrderApi {
    
    private OrderService orderService;

    public OrderApi(OrderService orderService){
        this.orderService=orderService;
    }

    @PostMapping
    public int insertOrders(@RequestBody Orders order) {
        return orderService.insertOrders(order);
    }

    @GetMapping(path = "{id}")
    public Orders getOrdersByID(@PathVariable("id") String id) {
        return orderService.getOrdersByID(id);
    }

    @PutMapping(path = "{id}")
    public int updateOrders(@PathVariable("id") String id,@RequestBody Orders order) {
        return orderService.updateOrders(id,order);
    }

    @DeleteMapping(path = "{id}")
    public int deleteOrders(@PathVariable("id") String id) {
        return orderService.deleteOrders(id);
    }

    @GetMapping
    public List<Orders> getAllOrders(){
        return orderService.getAllOrders();
    }

    @GetMapping(path = "{manufacturerid}")
    public List<Orders> getOrdersfromManufacturer(@PathVariable("manufacturerid") String manufacturerid){
        return orderService.getOrdersfromManufacturer(manufacturerid);
    }
}
