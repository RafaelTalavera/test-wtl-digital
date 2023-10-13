package com.rafaeltalavera.springboot.testwtldigital.models.service;

import java.util.List;


import com.rafaeltalavera.springboot.testwtldigital.models.entity.SalesOrder;

public interface ISalesOrdenService {
	
	  public List<SalesOrder> findALL();
	  
	  public void save (SalesOrder salesOrder);
	  
	  public SalesOrder findOne(Long id);
	  
	  public void delete(Long id);
	
	

}
