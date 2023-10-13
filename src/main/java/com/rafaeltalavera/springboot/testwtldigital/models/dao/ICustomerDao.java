package com.rafaeltalavera.springboot.testwtldigital.models.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.rafaeltalavera.springboot.testwtldigital.models.entity.Customer;

public interface ICustomerDao extends PagingAndSortingRepository<Customer, Long>, CrudRepository<Customer, Long>  {

}
