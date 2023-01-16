package com.cosmostaker.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cosmostaker.spring.entity.Customer;
import com.cosmostaker.spring.service.CustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	
	// Injecting the CustomerService
	@Autowired
	private CustomerService customerService;
	
	
	@GetMapping("/list")
	public String listCustomers(Model theModel) {
		
		// Getting the customers from the DAO
		List<Customer> theCustomers = customerService.getCustomers();
		
		// Adding the customers to the model
		theModel.addAttribute("customers", theCustomers);
		
		return "listCustomers";
	}
	
	
	@GetMapping("/add") 
	public String showFormForAdd(Model theModel) {
		
		// Model attribute to bind form data
		Customer theCustomer = new Customer();
		theModel.addAttribute("customer", theCustomer);
		
		return "customerForm";
	}
	
	
	@PostMapping("/saveCustomer")
	public String saveCustomer(@ModelAttribute("customer") Customer theCustomer ) {
		
		// Saving customer using service
		customerService.saveCustomer(theCustomer);
		
		return "redirect:/customer/list";
	}
	
	
	@GetMapping("/update")
	public String showFormForUpdate(@RequestParam("customerId") int theId, Model theModel) {
		
		// Get the customer from the service
		Customer theCustomer = customerService.getCustomer(theId);
		
		// Set customer as a model attribute to pre-populate the form
		theModel.addAttribute("customer", theCustomer);
		
		// Send over to our form
		return "customerForm";
	}
	
	
	@GetMapping("/delete")
	public String deleteCustomer(@RequestParam("customerId") int theId) {
		// Delete the customer
		customerService.deleteCustomer(theId);
		
		return "redirect:/customer/list";
	}
}