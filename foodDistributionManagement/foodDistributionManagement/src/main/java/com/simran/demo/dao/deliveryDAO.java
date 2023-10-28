package com.simran.demo.dao;
import com.simran.demo.model.Deliveries;

import java.util.List;

public interface deliveryDAO {

    int insertDeliveries(Deliveries deliveries);
    //INSERT INTO Deliveries VALUES (customer);
    int deleteDeliveries(String id);
    //delete from Deliveries where O_ID = id;
    int updateDeliveries(String id, Deliveries deliveries);
     //update Deliveries set C_id = ? , Employee_Assigned = ? , D_status = ? D_date = ? Amount = ? P_status ? where D_ID = id ;
    Deliveries getDeliveriesByID(String id);
     //select * from Deliveries where D_ID = id;
    Deliveries getPaidDeliveriesByID(String id);
     //select * from Deliveries where D_ID = id and P_status = Paid;
    List<Deliveries> getAllDeliveries();
     //select * from Deliveries;
    List<Deliveries> getPaidDeliveries();
     // select * from Deliveries where P_status = Paid;
    List<Deliveries> getDeliveriesofClients(String id);
    //SELECT * FROM Deliveries WHERE C_ID = id;

}