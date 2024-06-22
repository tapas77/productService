package com.example.product.repositories;

import com.example.product.dtos.ProductWithIdNamePrice;
import com.example.product.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByName(String name);
    Optional<Product> findById(Long id);
    List<Product> findByNameAndDescriptionAndPriceGreaterThan(String tittle, String description, int price);
    List<Product> findTop5DistinctProductByName(String name);

    Product save(Product product);

//    -------------------Using hqls----------------
    @Query("select p from Product p where p.id = :Tapas")
    Optional<Product> something(@Param("Tapas") Long id);

    @Query("select p.id as id, p.name as name, p.price as price from Product p where p.id = :id")
//    always get product then get all the required attributes
    ProductWithIdNamePrice somethingSpecific(@Param("id") Long id);
}
