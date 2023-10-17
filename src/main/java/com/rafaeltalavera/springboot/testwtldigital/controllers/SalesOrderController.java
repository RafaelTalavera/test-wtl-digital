package com.rafaeltalavera.springboot.testwtldigital.controllers;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.rafaeltalavera.springboot.testwtldigital.models.entity.Customer;
import com.rafaeltalavera.springboot.testwtldigital.models.entity.ItemSale;
import com.rafaeltalavera.springboot.testwtldigital.models.entity.SalesOrder;
import com.rafaeltalavera.springboot.testwtldigital.models.service.ICustomerService;
import com.rafaeltalavera.springboot.testwtldigital.models.entity.Product;

import jakarta.validation.Valid;

@Secured("ROLE_ADMIN")
@Controller
@RequestMapping("/sales")
@SessionAttributes("salesOrder")
public class SalesOrderController {

    @Autowired
    private ICustomerService customerService;

    private final Logger log = LoggerFactory.getLogger(getClass());

	@GetMapping("/show/{id}")
	public String ver(@PathVariable(value="id") Long id, 
			Model model,
			RedirectAttributes flash) {
		SalesOrder salesOrder = customerService.findSalesOrderById(id);
		
		if(salesOrder == null) {
			flash.addFlashAttribute("error", "O pedido de compra não existe no banco de dados!");
			return "redirect:/list-customer";
		}
		
		model.addAttribute("salesOrder", salesOrder);
		model.addAttribute("titulo", "Sales order: ".concat(salesOrder.getDescription()));
		
		return "sales/show";
	}
    
    @GetMapping("/form/{customerId}")
    public String create(@PathVariable(value = "customerId") Long customerId, Model model, RedirectAttributes flash) {
       
        Customer customer = customerService.findOne(customerId);
        
        

        if (customer == null) {
            flash.addFlashAttribute("error", "O cliente não existe no banco de dados");
            return "redirect:/list-customer";
        }

        SalesOrder salesOrder = new SalesOrder();
        salesOrder.setCustomer(customer);

        model.addAttribute("salesOrder", salesOrder);
        model.addAttribute("titulo", "Criar pedido de compra");

        return "sales/form";
    }

    @GetMapping(value = "/product-load/{term}", produces = { "application/json" })
    public @ResponseBody List<Product> productLoad(@PathVariable String term) {
        return customerService.findByDescription(term);
    }

    @PostMapping("/form")
    public String save(@Valid SalesOrder salesOrder, BindingResult result, Model model,
                       @RequestParam(name = "item_id[]", required = false) Long[] itemId,
                       @RequestParam(name = "cantidad[]", required = false) Integer[] cantidad, 
                       RedirectAttributes flash,
                       SessionStatus status) {
    	
		if (result.hasErrors()) {
			model.addAttribute("titulo", "Criar pedido de compra");
			return "sales/form";
		}
			

        if (itemId == null || itemId.length == 0) {
        	model.addAttribute("titulo", "Criar pedido de compra");
            model.addAttribute("error", "Erro: O pedido de compra não pode ter linhas vazias");
            return "sales/form";
        }

        List<ItemSale> itemSales = new ArrayList<>(); // Crear una lista para almacenar los ItemSale

        for (int i = 0; i < itemId.length; i++) {
            Product product = customerService.findProductById(itemId[i]);
            
            if (product != null) {
                ItemSale itemSale = new ItemSale();
                itemSale.setQuantity(cantidad[i]);
                itemSale.setProduct(product);
                itemSale.setSalesOrder(salesOrder); // Asignar el SalesOrder al ItemSale
                itemSales.add(itemSale);

                log.info("ID: " + itemId[i].toString() + ", cantidad: " + cantidad[i].toString());
            }
        }

        if (!itemSales.isEmpty()) {
            salesOrder.setItems(itemSales); // Asignar la lista de ItemSale al SalesOrder
            customerService.saveSalesOrder(salesOrder);
            status.setComplete();
            flash.addFlashAttribute("success", "O pedido de compra foi criado com sucesso!");
        } else {
            flash.addFlashAttribute("error", "Erro ao criar fatura. Verifique os produtos.");
        }

        return "redirect:/show-customer/" + salesOrder.getCustomer().getId();
    }




    @GetMapping("/delete/{id}")
    public String delete(@PathVariable(value = "id") Long id, RedirectAttributes flash) {

        SalesOrder salesOrder = customerService.findSalesOrderById(id);

        if (salesOrder != null) {
            customerService.deleteSalesOrder(id);
            flash.addFlashAttribute("success", "O pedido de compra foi excluído com sucesso");
            return "redirect:/show-customer/" + salesOrder.getCustomer().getId();
        }
        flash.addFlashAttribute("error", "O pedido de compra não pôde ser excluído!");

        return "redirect:/list-customer";
    }
}
