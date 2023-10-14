package com.rafaeltalavera.springboot.testwtldigital.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.rafaeltalavera.springboot.testwtldigital.models.entity.Customer;
import com.rafaeltalavera.springboot.testwtldigital.models.entity.SalesOrder;
import com.rafaeltalavera.springboot.testwtldigital.models.service.ICustomerService;

@Controller
@RequestMapping("/sales")
@SessionAttributes("sales")
public class SalesOrderController {

	@Autowired
	private ICustomerService customerService;

	@GetMapping("/form/{customerId}")
	public String create(@PathVariable(value = "customerId") Long customerId, Map<String, Object> model,
			RedirectAttributes flash) {

		Customer customer = customerService.findOne(customerId);

		if (customer == null) {
			flash.addAttribute("error", "o cliente não existe no banco de dados");
			return "redirect:/list-customer";

		}

		SalesOrder salesOrder = new SalesOrder();
		salesOrder.setCustomer(customer);
		
		model.put("sales", salesOrder);
		model.put("titulo", "criar pedido de compra");

		return "sales/form";

	}
}
