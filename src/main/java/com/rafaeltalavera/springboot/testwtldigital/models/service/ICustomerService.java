package com.rafaeltalavera.springboot.testwtldigital.models.service;

import org.springframework.data.domain.Pageable;
import java.util.List;

import org.springframework.data.domain.Page;

import com.rafaeltalavera.springboot.testwtldigital.models.entity.Customer;
import com.rafaeltalavera.springboot.testwtldigital.models.entity.Product;
import com.rafaeltalavera.springboot.testwtldigital.models.entity.SalesOrder;

public interface ICustomerService {

	public List<Customer> findALL();

	public Page<Customer> findALL(Pageable pageable);

	public void save(Customer customer);

	public void delete(Long id);
	
	public Customer findOne(Long id);

	public List<Product> findByDescription(String term);
	
	public void saveSalesOrder(SalesOrder salesOrder);
	
	public Product findProductById(Long id);
	
	public SalesOrder findSalesOrderById(Long id);
	
	public void deleteSalesOrder(Long id);



}
