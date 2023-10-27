package com.simran.demo.dao;
import com.simran.demo.model.Orders;
// import com.simran.demo.model.Manufacturers;
import java.util.List;

public interface orderDAO
{
     int insertOrders(Orders orders);
    //INSERT INTO Orders VALUES (customer);
    int deleteOrders(String id);
    //delete from orders where O_ID = id ;
    int updateOrders(String id , Orders orders);
    //update orders set M_id = ? , O_date = ? , Amount = ? where O_ID = id ;
    Orders getOrdersByID(String id);
    //select * from Orders where O_ID = id ;
    List<Orders> getAllOrders();
    //select * from Orders ;
    List<Orders> getOrdersfromManufacturer(String id) ;
    // select * from orders where M_ID = id ;
    //List<Manufacturers> getmanufacturerfromorder(String id) ;
    // select * from manufacturer where O_id = id ;



}
