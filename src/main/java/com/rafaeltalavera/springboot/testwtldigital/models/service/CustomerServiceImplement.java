package com.rafaeltalavera.springboot.testwtldigital.models.service;

import org.springframework.data.domain.Pageable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rafaeltalavera.springboot.testwtldigital.models.dao.ICustomerDao;
import com.rafaeltalavera.springboot.testwtldigital.models.entity.Customer;

@Service
public class CustomerServiceImplement implements ICustomerService{
	
	@Autowired
	ICustomerDao customerDao;

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
	public Customer findOne(Long id) {
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









}



