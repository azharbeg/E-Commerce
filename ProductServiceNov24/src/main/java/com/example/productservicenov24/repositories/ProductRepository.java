package com.example.productservicenov24.repositories;

import com.example.productservicenov24.models.Category;
import com.example.productservicenov24.models.Product;
import com.example.productservicenov24.projections.ProductProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

    // save the product object and save it in the database and return a product
    Product save(Product p);

    // here we need a way to get all the product
    @Override
    List<Product> findAll(); // get the all product and return a list of product


     /*
        select * from products where title
        like 'iphone' limit 10 offset 20..
        this is excetly how we will implement the pagination
     */
  // below line for the pagination
    @Override
    Page<Product> findAll(Pageable pageable);


    // for find by ID

    Optional<Product> findById(Long id); // It's returing an optional product becoz It might be possible this current id is not present in databasee


    List<Product>findByCategory(Category category);

    List<Product> findByCategory_Title(String categoryName);


    // find all category with a certain category
    List<Product> findAllByCategory_Id(Long categoryId);

    // we can write any query ORM Behind the seen take care of it
    List<Product> findByTitleStartingWithAndIdEqualsAndPriceLessThen(String startingWith, Long id, double price);

    /*
     if we are interested only
       title, id of product then HQL Help us
       below is hql
     */
    @Query("select p.title as title, p.id as id from Product as p where p.category.title = :categoryName")
    List<ProductProjection> getTitlesAndIdOfAllProductsWithGivenCategoryName(@Param("categoryName") String categoryName);

    //Native Sql queries
    @Query(value = "select * from products p where p.id = 1 and p.title = :productTitle", nativeQuery = true)
    List<ProductProjection> getTitlesAndIdOfAllProductsWithCategoryNameEquals(@Param("productTitle")String productTitle);


}
