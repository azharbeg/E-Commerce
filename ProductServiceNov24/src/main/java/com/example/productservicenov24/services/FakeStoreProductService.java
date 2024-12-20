package com.example.productservicenov24.services;

import com.example.productservicenov24.DTO.CreateProductRequestDto;
import com.example.productservicenov24.DTO.ErrorDTO;
import com.example.productservicenov24.DTO.FakeStoreProductDto;
import com.example.productservicenov24.exception.ProductNotFoundException;
import com.example.productservicenov24.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;


@Service("fakeStoreProductService")
public class FakeStoreProductService implements ProductService{

    // restTemplate is also the part of spring web
    // using restTemplate we can call external APIs

    @Autowired
    private RestTemplate restTemplate;

    // redis template use here
    private RedisTemplate redisTemplate;

    // now we will also inject the dependency of the above service
    @Autowired
    public FakeStoreProductService(RestTemplate restTemplate , RedisTemplate redisTemplate) {
        restTemplate = new RestTemplate();
        redisTemplate = new RedisTemplate();
    }

    @Override
    public List<Product> getAllProduct() {

        // yha se hum all products get krenge
       FakeStoreProductDto[] fakeStoreProductDtos =  restTemplate.getForObject("https://fakestoreapi.com/products",
                                                    FakeStoreProductDto[].class);

       List<Product> products = new ArrayList<>();
       for(FakeStoreProductDto fakeStoreProductDto : fakeStoreProductDtos){
           Product p = fakeStoreProductDto.toProduct();
           products.add(p);
       }
       return  products;
    }

    @Override
    public Product getSingleProduct(long id) throws ProductNotFoundException {
            /*
                here call the external fakestore product api,
                hum is api ko call krenge -> https://fakestoreapi.com/product/1
                to call above endPoint we need something (to call the 3rd party APIs we need the another dependency
                that dependencies is rest template)
             */

            // before going to the fakestore product server you will get try that data from the cache
            // if it's present in the cache, I Will not go to the fakestore directly retrun it else i will go

        //  first check for data in the cache
       Product product = (Product) redisTemplate.opsForHash().get("PRODUCTS", "product" + id);
       // here we are getting the object from the reds
        // am I trying to get an object over the network

       if(product != null){
           //cache hit
           return product;
       }
       // in cache miss
        
             FakeStoreProductDto fakeStoreProductDto = restTemplate.getForObject("https://fakestoreapi.com/products/1" + id,
                     FakeStoreProductDto.class);

             if(fakeStoreProductDto == null)
                 throw new ProductNotFoundException("Product with  " + id + " is not present with this api call..( chalo ji maza aaya) ");

             return fakeStoreProductDto.toProduct();
    }

    @Override
    public Product createProduct(String title, String description, double price, String imageUrl, String category) {
        return null;
    }

    //@Override
    public Product createProduct(String title,
                                 String description,
                                 double price,
                                 String imageUrl,
                                 String category,
                                 long id){
         FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();

         fakeStoreProductDto.setCategory(category);
         fakeStoreProductDto.setImage(imageUrl);
         fakeStoreProductDto.setTitle(title);
         fakeStoreProductDto.setDescription(description);
         fakeStoreProductDto.setPrice(price);

        FakeStoreProductDto fakeStoreProductDto1 =  restTemplate.postForObject("https://fakestoreapi.com/products",
                   fakeStoreProductDto,
                    FakeStoreProductDto.class);

        Product product = fakeStoreProductDto.toProduct();
            // before returning this product, we should store this product in
        //       the cache becoz  avoid subsequential calls

        redisTemplate.opsForHash().put("PRODUCTS", "product"+ id, product);
        // in this case are we sending an object over the network

        return product;
        /*
            this POST /product actually doesn't create a new object in the fakestore
                it's just a dummy api
         */

    }

    @Override
    public Page<Product> getAllProductPage(int page, int size) {
        return null;
    }

    @Override
    public Page<Product> getAllProductsPaginated(int pageNo, int pageSize) {
        return null;
    }
//    @ExceptionHandler(ProductNotFoundException.class)
//    public ResponseEntity<ErrorDTO> handleProductNotFoundException(ProductNotFoundException productNotFoundException){
//
//        ErrorDTO errorDTO = new ErrorDTO();
//
//        errorDTO.setMessage(productNotFoundException.getMessage());
//
//        ResponseEntity<ErrorDTO> responseEntity = new ResponseEntity<>(errorDTO, HttpStatus.NOT_FOUND);
//
//        return responseEntity;
//    }

}
