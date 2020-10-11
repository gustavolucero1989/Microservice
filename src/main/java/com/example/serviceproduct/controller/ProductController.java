package com.example.serviceproduct.controller;

import com.example.serviceproduct.entity.Product;
import com.example.serviceproduct.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/products")
public class ProductController {
    final ProductService productService;

    //Inyeccion de dependencias
    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<Product>> listProduct(){
        List<Product> products = productService.listAllProduct();
        //Si la lista esta vacia
        if(products.isEmpty()){
            // Error 204
            return ResponseEntity.noContent().build();
        }
        // 202 OK
        return ResponseEntity.ok(products);
    }
}
