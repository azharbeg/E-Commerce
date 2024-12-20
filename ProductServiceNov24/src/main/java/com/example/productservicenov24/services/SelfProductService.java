package com.example.productservicenov24.services;

import com.example.productservicenov24.exception.ProductNotFoundException;
import com.example.productservicenov24.models.Category;
import com.example.productservicenov24.models.Product;
import com.example.productservicenov24.repositories.CategoryRepository;
import com.example.productservicenov24.repositories.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("selfProductService")
public class SelfProductService implements ProductService{

  // first check category already present or not
    private CategoryRepository categoryRepository;

    private ProductRepository productRepository;

    public SelfProductService(CategoryRepository categoryRepository,
                              ProductRepository productRepository){

        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;

    }



    @Override
    public List<Product> getAllProduct() {

        return productRepository.findAll();
    }


     public Page<Product> getAllProductPaginated(int pageNo, int pageSize){
        return productRepository.findAll(
                PageRequest.of(pageNo, pageSize,
                        Sort.by("title").descending().and(Sort.by("price")))// this pageNo, pageSize given by the client
        );
    }

    @Override
    public Product getSingleProduct(long id) throws ProductNotFoundException {

        Optional<Product> product = productRepository.findById(id);
        if(product.isEmpty()){
            throw new ProductNotFoundException("Product with this" + id + " is not present in the database");
        }
        return product.get() ;
    }

    @Override
    public Product createProduct(String title, String description, double price, String imageUrl, String category) {

        // create object of product
        Product p = new Product();
        p.setTitle(title);
        p.setDescription(description);
        p.setPrice(price);
        p.setImageUrl(imageUrl);


        // may be receive category already present in db, then try to fetch this category
        Category categoryFromDb = categoryRepository.findByTitle(category);

        if(categoryFromDb == null){
            Category newCategory = new Category(); // create new category
            newCategory.setTitle(category); // or newCategory ko set kar dia

            categoryFromDb = newCategory;
        }
        // save this product into the product table.
       else{
            p.setCategory(categoryFromDb);
        }

        // now saving in the database
        Product createdProduct = productRepository.save(p);

        return createdProduct;
    }

    @Override
    public Page<Product> getAllProductPage(int page, int size) {
        return null;
    }

    @Override
    public Page<Product> getAllProductsPaginated(int pageNo, int pageSize) {
        return null;
    }
}
