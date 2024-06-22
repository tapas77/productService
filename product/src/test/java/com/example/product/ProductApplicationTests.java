package com.example.product;

import com.example.product.dtos.ProductWithIdNamePrice;
import com.example.product.models.Product;
import com.example.product.repositories.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
class ProductApplicationTests {

	@Autowired
	private ProductRepository productRepository;

	@Test
	void contextLoads() {
	}

//	@Test
//	public void getSomeData(){
//		Optional<Product> optionalProduct = productRepository.findByName("macbook");
//		if(optionalProduct.isEmpty()){
//			return;
//		}
//		Product product = optionalProduct.get();
//		System.out.println();
//		System.out.println(product.getId()+" "+ product.getPrice());
//		System.out.println();
//		System.out.println();
//
//	}
//	@Test
//	public void getSomeListData(){
//		List<Product> productList = productRepository.findTop5DistinctProductByName("macbook");
//		for(Product product : productList){
//			System.out.println(product.getId()+" "+ product.getPrice());
//		}
//	}
	@Test
	public void something(){
		Optional<Product> productOptional = productRepository.something(2L);
		Product product = productOptional.get();
		System.out.println(product.getId()+" "+ product.getPrice());
	}

	@Test
	public void somethingSpecific(){
		ProductWithIdNamePrice p1 = productRepository.somethingSpecific(2L);
		System.out.println(p1.getId() +" " +p1.getName() + " "+p1.getPrice());
	}
}
