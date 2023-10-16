package com.rafaeltalavera.springboot.testwtldigital.models.service;

import org.springframework.data.domain.Pageable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.rafaeltalavera.springboot.testwtldigital.models.dao.ICustomerDao;
import com.rafaeltalavera.springboot.testwtldigital.models.dao.IProductDao;
import com.rafaeltalavera.springboot.testwtldigital.models.dao.ISalesOrderDao;
import com.rafaeltalavera.springboot.testwtldigital.models.entity.Customer;
import com.rafaeltalavera.springboot.testwtldigital.models.entity.Product;
import com.rafaeltalavera.springboot.testwtldigital.models.entity.SalesOrder;

@Service
public class CustomerServiceImplement implements ICustomerService{
	
	@Autowired
	ICustomerDao customerDao;
	
	@Autowired
	IProductDao productDao;
	
	@Autowired 
	ISalesOrderDao salesOrderDao;
	

	@Override
	@Transactional(readOnly=true)
	public List<Customer> findALL() {
		return (List<Customer>) customerDao.findAll();
	}
	
	@Override
	@Transactional
	public void save(Customer customer) {	
		customerDao.save(customer);
	}

	@Override
	@Transactional(readOnly=true) 
	public Customer findCustomerById(Long id) {
		return customerDao.findById(id).orElse(null);
	}
	
	@Override
	@Transactional
	public void delete(Long id) {
		customerDao.deleteById(id);
		
	}

	@Override
	@Transactional(readOnly=true) 
	public Page<Customer> findALL(Pageable pageable) {
		return customerDao.findAll(pageable);
	}

	@Override
	@Transactional(readOnly=true) 
	public List<Product> findByDescription(String term) {
		return productDao.findByDescription(term);
	}

	@Override
	@Transactional
	public void saveSalesOrder(SalesOrder salesOrder) {
		salesOrderDao.save(salesOrder);
		
	}

	@Override
	@Transactional(readOnly=true) 
	public Product findProductById(Long id) {
		return productDao.findById(id).orElse(null);
	}

	@Override
	@Transactional(readOnly=true)
	public SalesOrder findSalesOrderById(Long id) {
		return salesOrderDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void deleteSalesOrder(Long id) {
		salesOrderDao.deleteById(id);
	}
		
	}













