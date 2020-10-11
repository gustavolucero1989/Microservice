package com.example.serviceproduct.controller;

import com.example.serviceproduct.entity.Category;
import com.example.serviceproduct.entity.Product;
import com.example.serviceproduct.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
    public ResponseEntity<List<Product>> listProduct(@RequestParam(name = "categoryId",
            required = false) Long categoryId) {
        List<Product> products = new ArrayList<>();
        // Si la categoria es nula
        if (null == categoryId) {
            products = productService.listAllProduct();
            // Si es vacia la lista
            if (products.isEmpty()) {
                //Error 204
                return ResponseEntity.noContent().build();
            }
        } else {
            // findByCategory devuelve una lista de product (Implementacion en capa
            // ProductServiceImpl)
            products = productService.finByCategory(Category.builder().id(categoryId).build());
            //Si no encontramos la categoria
            if (products.isEmpty()) {
                // Error 404
                return ResponseEntity.notFound().build();
            }
        }
        // 200 OK
        return ResponseEntity.ok(products);
    }

    @GetMapping({"/{id}"})
    public ResponseEntity<Product> getProduct(@PathVariable("id") Long id){
        Product product = productService.getProduct(id);
        if(product == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(product);
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product){
        Product productCreate = productService.createProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable("id") Long id, @RequestBody Product product){
        product.setId(id);
        Product productDB = productService.updateProduct(product);
        if(productDB==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(productDB);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable("id") Long id){
        Product productDelete = productService.deleteProduct(id);
        if (productDelete == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(productDelete);
    }
    
    @GetMapping (value = "/{id}/stock")
    public ResponseEntity<Product> updateStockProduct(@PathVariable  Long id ,
                                                      @RequestParam(name = "quantity",
                                                              required = true) Double quantity){
        Product product = productService.updateStock(id, quantity);
        if (product == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(product);
    }
}