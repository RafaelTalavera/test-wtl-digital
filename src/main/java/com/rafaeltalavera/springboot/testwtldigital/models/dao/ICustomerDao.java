package com.rafaeltalavera.springboot.testwtldigital.models.dao;

import org.springframework.data.repository.CrudRepository;
import com.rafaeltalavera.springboot.testwtldigital.models.entity.Customer;

public interface ICustomerDao extends CrudRepository<Customer, Long>  {

}
