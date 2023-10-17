package com.rafaeltalavera.springboot.testwtldigital.models.dao;

import org.springframework.data.repository.CrudRepository;
import com.rafaeltalavera.springboot.testwtldigital.models.entity.SalesOrder;

public interface ISalesOrderDao extends  CrudRepository<SalesOrder, Long> {

}
