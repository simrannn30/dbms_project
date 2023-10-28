package com.simran.demo.api;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.simran.demo.model.Manufacturers;

import com.simran.demo.services.SupplierService;

@RestController
@RequestMapping("api/v1/supplier")
public class SupplierApi {
    private SupplierService supplierService;

    public SupplierApi(SupplierService supplierService){
        this.supplierService=supplierService;
    }

    @PostMapping
    public int insertSupplier(@RequestBody Manufacturers supplier) {
        return supplierService.insertSupplier(supplier);
    }

    @GetMapping(path = "{id}")
    public Manufacturers getSupplierByID(@PathVariable("id") String id) {
        return supplierService.getSupplierByID(id);
    }

    @PutMapping(path = "{id}")
    public int updateSupplier(@PathVariable("id") String id,@RequestBody Manufacturers supplier) {
        return supplierService.updateSupplier(id,supplier);
    }

    @DeleteMapping(path = "{id}")
    public int deleteSupplier(@PathVariable("id") String id) {
        return supplierService.deleteSupplier(id);
    }

    @GetMapping
    public List<Manufacturers> getAllSuppliers(){
        return supplierService.getAllSuppliers();
    }
}
