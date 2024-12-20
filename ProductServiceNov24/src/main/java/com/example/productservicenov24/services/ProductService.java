package com.example.productservicenov24.services;


import com.example.productservicenov24.DTO.CreateProductRequestDto;
import com.example.productservicenov24.exception.ProductNotFoundException;
import com.example.productservicenov24.models.Product;
import org.springframework.data.domain.Page;

import java.util.List;

// this ProductService can have multiple implementation
public interface ProductService {

    List<Product> getAllProduct();

    Product getSingleProduct(long id) throws ProductNotFoundException;

    Product createProduct(String title,
                          String description,
                          double price,
                          String imageUrl,
                          String category);

    Page<Product> getAllProductPage(int page, int size);


    Page<Product> getAllProductsPaginated(int pageNo, int pageSize);
}
