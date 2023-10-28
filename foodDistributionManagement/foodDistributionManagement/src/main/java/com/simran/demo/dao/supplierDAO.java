package com.simran.demo.dao;

import com.simran.demo.model.Manufacturers;
import java.util.List;

public interface supplierDAO {
    int insertSupplier(Manufacturers supplier);
    Manufacturers getSupplierByID(String id);
    int updateSupplier(String id,Manufacturers supplier);
    int deleteSupplier(String id);

    List<Manufacturers> getAllSuppliers();
}
