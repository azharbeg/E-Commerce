package com.example.productservicenov24.repositories;

import com.example.productservicenov24.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category,String> {

   Category findByTitle(String title); //JPA method
    /*
    Internally what jpa do
        select * from Category
          where title like 'titleName';

          once jpa will give the response from here jpa
          will convert the row to a category object and return

     */

    // It's possible the category is not present with in certain Id
    // In this case we will return an Optional category
    Optional<Category> findById(Long id);
}
