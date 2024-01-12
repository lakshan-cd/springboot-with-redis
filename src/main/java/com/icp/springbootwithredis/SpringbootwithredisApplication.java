package com.icp.springbootwithredis;

import com.icp.springbootwithredis.entity.Product;
import com.icp.springbootwithredis.repository.ProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SpringBootApplication
@RestController
@RequestMapping("/product")
@EnableCaching
public class SpringbootwithredisApplication {

	@Autowired
	private ProductDao productDao;

	@PostMapping("/save")
	public Product save(@RequestBody Product product){
		return productDao.save(product);
	}


	@GetMapping("/getAll")
	public List<Product> getAllProducts(){
		return productDao.findALl();
	}


	@GetMapping("/getProductById/{id}")
	@Cacheable(key = "#id" , value = "Product", unless = "#result.price > 26000")
	public Product getProductById(@PathVariable int id){
		return productDao.findProductById(id);
	}

	@DeleteMapping("/Delete/{id}")
	@Cacheable(key = "#id" , value = "Product") //when delete from the og one it will remove from cache also
	public String deleteProductById(@PathVariable int id){
		return productDao.deleteById(id);
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringbootwithredisApplication.class, args);
	}

}
