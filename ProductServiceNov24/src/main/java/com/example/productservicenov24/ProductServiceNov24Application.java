package com.example.productservicenov24;

import com.example.productservicenov24.models.Product;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProductServiceNov24Application {

    public static void main(String[] args) {
        SpringApplication.run(ProductServiceNov24Application.class, args);
    }

    Product p1 = new Product();

    public Product getP1() {
        return null;
     }

}
