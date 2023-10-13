package com.rafaeltalavera.springboot.testwtldigital.models.service;

import java.util.List;

import com.rafaeltalavera.springboot.testwtldigital.models.entity.Product;

public interface IProductService {

	  public List<Product> findALL();
	  
	  public void save (Product product);
	  
	  public Product findOne(Long id);
	  
	  public void delete(Long id);
}
