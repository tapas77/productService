package com.example.product.services;

import com.example.product.dtos.ProductRequestDto;
import com.example.product.exceptions.InvalidProductIdException;
import com.example.product.exceptions.ProductDoesNotExist;
import com.example.product.models.Category;
import com.example.product.models.Product;
import com.example.product.repositories.CategoryRepository;
import com.example.product.repositories.ProductRepository;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Primary
@Qualifier("SelfProductService")
public class SelfProductService implements IProductService{

    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;

    @Autowired
    public SelfProductService(ProductRepository productRepository,CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Product addProduct(Product product){
        Optional<Category> categoryOptional = categoryRepository.findByName(product.getCategory().getName());
        if(categoryOptional.isEmpty()){
//            Category categoryTosave = new Category();
//            categoryTosave.setName(product.getCategory().getName());
//            Category savedCategory = categoryRepository.save(categoryTosave);
//            product.setCategory(savedCategory);


//            ------------------------------------------------------------------------------------------------
//            since we are using CascadeType.PERSIST so it will create a new category and add it to the product
//            ------------------------------------------------------------------------------------------------
        }
        else{
            product.setCategory(categoryOptional.get());
        }
        productRepository.save(product);
        return product;
    }

    @Override
    public Product getSingleProduct(Long id) throws InvalidProductIdException {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if(optionalProduct.isEmpty()){
            throw new InvalidProductIdException("product with id "+id+" is not found");
        }
        Product product = optionalProduct.get();
        return product;
    }

    @Override
    public List<Product> getAllProducts() {
        return null;
    }

    @Override
    public Product updateProduct(Long id, Product product) throws ProductDoesNotExist {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if(optionalProduct.isEmpty()){
            throw new ProductDoesNotExist("product with id "+id+" doesn't exist");
        }
        Product existingProduct = optionalProduct.get();

        Product updatedProduct = new Product();

        updatedProduct.setName(
                product.getName() == null ? existingProduct.getName() : product.getName()
        );
        updatedProduct.setDescription(
                product.getDescription() == null ? existingProduct.getDescription() : product.getDescription()
        );
        updatedProduct.setImage(
                product.getImage() == null ? existingProduct.getImage() : product.getImage()
        );
        updatedProduct.setPrice(
                product.getPrice() == 0 ? existingProduct.getPrice() : product.getPrice()
        );

        Optional<Category> categoryOptional = categoryRepository.findByName(product.getCategory().getName());
        if(categoryOptional.isEmpty()){
            Category categoryToSave = new Category();
            categoryToSave.setName(product.getCategory().getName());
            Category savedCategory = categoryRepository.save(categoryToSave);
            updatedProduct.setCategory(savedCategory);
        }
        else{
            updatedProduct.setCategory(categoryOptional.get());
        }
        Product savedUpdatedProduct = productRepository.save(updatedProduct);
        return savedUpdatedProduct;
    }
}
