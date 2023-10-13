package com.rafaeltalavera.springboot.testwtldigital.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.rafaeltalavera.springboot.testwtldigital.models.dao.ICustomerDao;
import com.rafaeltalavera.springboot.testwtldigital.models.entity.Customer;

import jakarta.validation.Valid;


@Controller
public class CustomerController {
	
	@Autowired
	private ICustomerDao customerDao;

	 @RequestMapping(value = "/list-customer", method = RequestMethod.GET) 
	public String listCustomer (Model model) {
		model.addAttribute("titulo", "Lista de clientes");
		model.addAttribute("customer",customerDao.findAll());
		return "list-customer";
		
		
	}
	 
	 @RequestMapping(value = "/form-customer")
	 public String create(Map<String, Object> model) {
		 
		 Customer customer = new Customer();
		 model.put("customer", customer);
		 model.put("titulo", "Formulario de Cliente");
		 		 
		return "form-customer";
		 
	 }
	 
	 @RequestMapping(value="form-customer", method=RequestMethod.POST)
	 public String save(@Valid Customer customer, BindingResult result, Model model) {
		
	
		 
		 if(result.hasErrors()) {
			 model.addAttribute("titulo", "Formulario de Cliente");
			return "form-customer";
		 }
		 
		 customerDao.save(customer);

		 
		return "redirect:list-customer";
	 }
}
