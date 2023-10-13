package com.rafaeltalavera.springboot.testwtldigital.models.service;

import org.springframework.data.domain.Pageable;
import java.util.List;

import org.springframework.data.domain.Page;

import com.rafaeltalavera.springboot.testwtldigital.models.entity.Customer;


public interface ICustomerService {
	
	  public List<Customer> findALL();
	  
	  public Page<Customer> findALL(Pageable pageable);
	   
	  public void save (Customer customer);
	  
	  public Customer findOne(Long id);
	  
	  public void delete(Long id);
	    
}
