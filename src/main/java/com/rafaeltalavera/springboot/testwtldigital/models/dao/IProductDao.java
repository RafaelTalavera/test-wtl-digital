package com.rafaeltalavera.springboot.testwtldigital.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.rafaeltalavera.springboot.testwtldigital.models.entity.Product;

public interface IProductDao extends CrudRepository<Product, Long> {


	@Query("select p from Product p where p.description like %?1%")
	public List<Product> findByDescription(String term);

}
