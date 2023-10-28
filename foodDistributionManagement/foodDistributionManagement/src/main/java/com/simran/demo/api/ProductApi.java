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

import com.simran.demo.model.Products;
import com.simran.demo.services.ProductService;

@RestController
@RequestMapping("api/v1/product")
public class ProductApi {
    
    private ProductService productService;

    public ProductApi(ProductService productService){
        this.productService=productService;
    }

    @PostMapping
    public int insertProduct(@RequestBody Products product) {
        return productService.insertProduct(product);
    }

    @GetMapping(path = "{id}")
    public Products getProductByID(@PathVariable("id") String id) {
        return productService.getProductByID(id);
    }

    @PutMapping(path = "{id}")
    public int updateProduct(@PathVariable("id") String id,@RequestBody Products product) {
        return productService.updateProduct(id,product);
    }

    @DeleteMapping(path = "{id}")
    public int deleteProduct(@PathVariable("id") String id) {
        return productService.deleteProduct(id);
    }

    @GetMapping
    public List<Products> getAllProduct(){
        return productService.getAllProduct();
    }
}
