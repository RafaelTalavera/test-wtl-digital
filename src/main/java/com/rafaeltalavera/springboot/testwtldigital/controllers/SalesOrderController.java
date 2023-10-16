package com.rafaeltalavera.springboot.testwtldigital.controllers;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

//anotacion 
@Controller
@RequestMapping("/sales")
@SessionAttributes("sales")
public class SalesOrderController {

    @Autowired
    private ICustomerService customerService;

    private final Logger log = LoggerFactory.getLogger(getClass());

    @GetMapping("/form/{customerId}")
    public String create(@PathVariable(value = "customerId") Long customerId, Model model, RedirectAttributes flash) {
       
        Customer customer = customerService.findCustomerById(customerId);

        if (customer == null) {
            flash.addFlashAttribute("error", "El cliente no existe en la base de datos");
            return "redirect:/list-customer";
        }

        SalesOrder salesOrder = new SalesOrder();
        salesOrder.setCustomer(customer);

        model.addAttribute("sales", salesOrder);
        model.addAttribute("titulo", "Crear pedido de compra");

        return "sales/form";
    }

    @GetMapping(value = "/product-load/{term}", produces = { "application/json" })
    public @ResponseBody List<Product> productLoad(@PathVariable String term) {
        return customerService.findByDescription(term);
    }

    @PostMapping("/form")
    public String save(@Valid SalesOrder salesOrder, 
                       @RequestParam(name = "item_id[]", required = false) Long[] itemId,
                       @RequestParam(name = "cantidad[]", required = false) Integer[] cantidad, 
                       RedirectAttributes flash,
                       SessionStatus status) {

        if (itemId == null || itemId.length == 0) {
            flash.addFlashAttribute("error", "Error: La factura NO PUEDE tener líneas.");
            return "sales/form";
        }

        for (int i = 0; i < itemId.length; i++) {
            Product product = customerService.findProductById(itemId[i]);

            ItemSale linea = new ItemSale();
            linea.setQuantity(cantidad[i]);
            linea.setProduct(product);
            salesOrder.addItemOrder(linea);

            log.info("ID: " + itemId[i].toString() + ", cantidad: " + cantidad[i].toString());
        }


        customerService.saveSalesOrder(salesOrder);
        status.setComplete();

        flash.addFlashAttribute("success", "Factura creada con éxito!");



        return "redirect:/show-customer/" + salesOrder.getCustomer().getId();
    }



    @GetMapping("/delete/{id}")
    public String delete(@PathVariable(value = "id") Long id, RedirectAttributes flash) {

        SalesOrder salesOrder = customerService.findSalesOrderById(id);

        if (salesOrder != null) {
            customerService.deleteSalesOrder(id);
            flash.addFlashAttribute("success", "El pedido de compra fue eliminado con éxito");
            return "redirect:/show-customer/" + salesOrder.getCustomer().getId();
        }
        flash.addFlashAttribute("error", "El pedido de compra no pudo ser eliminado!");

        return "redirect:/list-customer";
    }
}
