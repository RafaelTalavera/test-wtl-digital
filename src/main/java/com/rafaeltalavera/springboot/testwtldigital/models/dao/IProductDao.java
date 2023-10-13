package com.rafaeltalavera.springboot.testwtldigital.models.dao;

import org.springframework.data.repository.CrudRepository;
import com.rafaeltalavera.springboot.testwtldigital.models.entity.Product;

public interface IProductDao extends CrudRepository<Product, Long> {

}
