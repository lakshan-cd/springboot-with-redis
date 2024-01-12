package com.icp.springbootwithredis;

import com.icp.springbootwithredis.entity.Product;
import com.icp.springbootwithredis.repository.ProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SpringBootApplication
@RestController
@RequestMapping("/product")
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
	public Product getProductById(@PathVariable int id){
		return productDao.findProductById(id);
	}

	@DeleteMapping("/Delete/{id}")
	public String deleteProductById(@PathVariable int id){
		return productDao.deleteById(id);
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringbootwithredisApplication.class, args);
	}

}
