package com.example.productservicenov24;

import com.example.productservicenov24.models.Product;
import com.example.productservicenov24.projections.ProductProjection;
import com.example.productservicenov24.repositories.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class ProductServiceNov24ApplicationTests {


        @Autowired
        ProductRepository productRepository;

                 @Test
                void contextLoads() {

                }

                @Test
                void testingQueries(){
                     List<Product> products = productRepository.
                             findByCategory_Title("electronics");
                      System.out.println(products);

                      // yha hum test kar rhe hai ki jo query humne reppository mai likhi vo kaam
                    //  kar rhi hai ya nhi
                      List<Product> productList = productRepository.findAllByCategory_Id( 1L);


                      // test hql query
                    List<ProductProjection> productProjectionos = productRepository.getTitlesAndIdOfAllProductsWithGivenCategoryName("electronics");

                    for(ProductProjection productProjection : productProjectionos){
                        System.out.println(productProjection.getId());
                        System.out.println(productProjection.getTitle());
                    }



                 }



}
