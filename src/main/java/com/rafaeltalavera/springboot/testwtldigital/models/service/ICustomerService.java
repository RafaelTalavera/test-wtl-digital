package com.rafaeltalavera.springboot.testwtldigital.models.service;

import java.util.List;

import com.rafaeltalavera.springboot.testwtldigital.models.entity.Customer;


public interface ICustomerService {
	
	  public List<Customer> findALL();
	  
	  public void save (Customer customer);
	  
	  public Customer findOne(Long id);
	  
	  public void delte(Long id);
	    
}
