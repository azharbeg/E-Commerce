package com.example.productservicenov24.controllers;


import com.example.productservicenov24.DTO.CreateProductRequestDto;
import com.example.productservicenov24.exception.ProductNotFoundException;
import com.example.productservicenov24.models.Product;
import com.example.productservicenov24.services.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // means this class receive request from the server please make sure ready for it
public class ProductController {

    public ProductService productService;

    // inject dependency through constructor
    public ProductController(@Qualifier("fakeStoreProductService")ProductService productService) {
        this.productService = productService;
    }

    /*
     at the end of the day what is api call?
     api call = method in my controller
     */

    /*
       this (below) is the api call look like
     */

    /* api url-
    GET /products
     */

//    @GetMapping("/products")
//   public List<Product> getAllProducts(){
//
//        return productService.getAllProduct();
//    }

    /*
    GET /products/{id}
     */

    @GetMapping("/products/{id}")
    public Product getSingleProduct(@PathVariable("id") long id) throws ProductNotFoundException {

        return productService.getSingleProduct(id);
    }


    /*
       API for get all Products.
       GET /products
     */
    @GetMapping("/products")
    public List<Product> getAllProducts(){

        return productService.getAllProduct();
    }

    // pagination api
    @GetMapping("/products/paginated/")
    public Page<Product> getAllProductsExperiment(@RequestParam("pageNo") int pageNo, @RequestParam("pageSize") int pageSize){
               Page<Product> productPage =  productService.getAllProductsPaginated(pageNo,pageSize);
               // h.w google has to convert Page<v> to Product<y>
                return productPage;
    }

    /*
    crate a product
    {
        title :
        description :
        price:
        category:
     }  // this is the object outside the system , we receive it in the payload - request body

    POST /products
     */

    @PostMapping("/products")
    public Product createProduct(@RequestBody CreateProductRequestDto createProductRequestDto){

        return productService.createProduct(createProductRequestDto.getTitle(),
                createProductRequestDto.getDescription(),
                createProductRequestDto.getPrice(),
                createProductRequestDto.getImage(),
                createProductRequestDto.getCategory());
    }
}
