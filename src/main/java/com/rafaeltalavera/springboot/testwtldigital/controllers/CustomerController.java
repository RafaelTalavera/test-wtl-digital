package com.rafaeltalavera.springboot.testwtldigital.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import com.rafaeltalavera.springboot.testwtldigital.models.entity.Customer;
import com.rafaeltalavera.springboot.testwtldigital.models.service.ICustomerService;
import com.rafaeltalavera.springboot.testwtldigital.util.paginator.PageRender;

import jakarta.validation.Valid;

@Controller
@SessionAttributes("customer")
public class CustomerController {

	@Autowired
	private ICustomerService customerService;
	
	
	@GetMapping(value="/show-customer/{id}")
	public String show(@PathVariable(value="id")Long id, Map<String, Object> model, RedirectAttributes flash) {
		
		Customer customer = customerService.findCustomerById(id);
		
		if(customer==null) {
			flash.addAttribute("error", "O cliente não foi encontrado no banco de dados");
			return "redirect:/list-customer";
		}
		
		model.put("customer", customer);
		model.put("titulo", "Detalhe do cliente: " + customer.getFullName());
		
		return "show-customer";
		
	}

	@RequestMapping(value = "/list-customer", method = RequestMethod.GET)
	public String listCustomer(@RequestParam(name="page", defaultValue="0") int page,Model model) {
		
		Pageable pageRequest = PageRequest.of(page, 4);
		
		Page<Customer> customer = customerService.findALL(pageRequest);

		PageRender<Customer> pageRender = new PageRender<>("/list-customer",customer);
		model.addAttribute("titulo", "Lista de clientes");
		model.addAttribute("customer", customer);
		model.addAttribute("page",pageRender);
		return "list-customer";

	}

	@RequestMapping(value = "/form-customer")
	public String create(Map<String, Object> model) {

		Customer customer = new Customer();
		model.put("customer", customer);
		model.put("titulo", "Formulário de cliente");

		return "form-customer";

	}

	@RequestMapping(value = "/form-customer/{id}")
	public String edit(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) {

		Customer customer = null;

		if (id > 0) {
			customer = customerService.findCustomerById(id);
			if (customer == null) {
				flash.addFlashAttribute("erro", "O ID do cliente não existe no BBDD!");
				return "redirect:/list-customer";
			}
		} else {
			flash.addFlashAttribute("erro", "O ID do cliente não pode ser zero!");
			return "redirect:/list-customer";
		}

		model.put("customer", customer);
		model.put("titulo", "Editar clientes");
		return "form-customer";

	}

	@RequestMapping(value = "form-customer", method = RequestMethod.POST)
	public String save(@Valid Customer customer, BindingResult result, Model model, RedirectAttributes flash,
			SessionStatus status) {

		if (result.hasErrors()) {
			model.addAttribute("titulo", "Formulário de cliente");
			return "form-customer";
		}
		String mensajeFlash = (customer.getId() != null) ? "Cliente editado com sucesso!" : "Cliente editado com sucesso!";
		customerService.save(customer);
		status.setComplete();
		flash.addFlashAttribute("success", mensajeFlash);
		return "redirect:list-customer";
	}

	@RequestMapping(value = "/delete/{id}")
	public String delete(@PathVariable(value = "id") Long id, RedirectAttributes flash) {

		if (id > 0) {
			customerService.delete(id);
			flash.addFlashAttribute("success", "Cliente excluído com sucesso!");
		}
		return "redirect:/list-customer";
	}

 	 
 

}
