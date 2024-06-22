package com.example.product.controllers;

import com.example.product.dtos.ErrorResponseDto;
import com.example.product.dtos.ProductRequestDto;
import com.example.product.dtos.ProductWrapper;
import com.example.product.exceptions.InvalidProductIdException;
import com.example.product.exceptions.ProductDoesNotExist;
import com.example.product.models.Category;
import com.example.product.models.Product;
import com.example.product.services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

//@Component
@RestController
public class ProductController {
//    @Autowired

    private IProductService productService;
    @Autowired
    public ProductController(@Qualifier("FakeStoreProductService") IProductService productService) {
//        we can also mention @Qualifier("SelfProductService") to use our created service instead of fakestore
        this.productService = productService;
    }

    //get all products
    @GetMapping("/products")
    public List<Product> getAllProducts() {

        return productService.getAllProducts()
                .stream()
                .filter(product -> product.getName().startsWith("A"))
                .collect(Collectors.toList());
    }

//        get single product with id
    @GetMapping("/products/{id}")
    public ResponseEntity<ProductWrapper> getSingleProduct(@PathVariable("id") Long id) throws InvalidProductIdException {
        ResponseEntity<ProductWrapper> response;
        //commenting the try catch block because we are using exception handler in controllerAdvice
        Product singleProduct = productService.getSingleProduct(id);
        ProductWrapper productWrapper = new ProductWrapper(singleProduct, "Successfully retrived the data");
        response = new ResponseEntity<>(productWrapper, HttpStatus.OK);
//        } catch(InvalidProductIdException e){
//            ProductWrapper productWrapper = new ProductWrapper(null, "Product is not present");
//            response = new ResponseEntity<>(productWrapper,HttpStatus.INTERNAL_SERVER_ERROR);
//        }
        return response;

    }


    //        update a product
    @PostMapping("/products")
    public Product addProduct(@RequestBody ProductRequestDto productRequestDto) {
        Product product = new Product();
        product.setName(productRequestDto.getTittle());
        product.setDescription(productRequestDto.getDescription());
        product.setPrice(productRequestDto.getPrice());
        product.setImage(productRequestDto.getImage());
        product.setCategory(new Category());
        product.getCategory().setName(productRequestDto.getCategory());
        Product savedProduct = productService.addProduct(product);
        return savedProduct;
    }

    @PutMapping("/products/{id}")
    public Product updateProduct(@PathVariable("id") Long id,
                                 @RequestBody ProductRequestDto productRequestDto) throws ProductDoesNotExist {
        Product product = new Product();
        product.setName(productRequestDto.getTittle());
        product.setDescription(productRequestDto.getDescription());
        product.setPrice(productRequestDto.getPrice());
        product.setImage(productRequestDto.getImage());
        product.setCategory(new Category());
        product.getCategory().setName(productRequestDto.getCategory());

        return productService.updateProduct(id, product);
    }

    @DeleteMapping("/products/{id}")
    public boolean deleteProduct(@PathVariable("id") Long id) {
        return true;
    }
}
