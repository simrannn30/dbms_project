package com.simran.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;


import com.simran.demo.dao.supplierDAO;

import com.simran.demo.model.Manufacturers;

@Service
public class SupplierService {
    private supplierDAO supplierDAO;

    public SupplierService(@Qualifier("supplier_mysql_repo") supplierDAO supplierDAO) {
        this.supplierDAO = supplierDAO;
    }

    public int insertSupplier(Manufacturers supplier) {
        return supplierDAO.insertSupplier(supplier);
    }

    public Manufacturers getSupplierByID(String id) {
        return supplierDAO.getSupplierByID(id);
    }

    public int updateSupplier(String id, Manufacturers supplier) {
        return supplierDAO.updateSupplier(id, supplier);
    }

    public int deleteSupplier(String id) {
        return supplierDAO.deleteSupplier(id);
    }

    public List<Manufacturers> getAllSuppliers() {
        return supplierDAO.getAllSuppliers();
    }
}
