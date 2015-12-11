package com.example;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.example.enitities.Customer;
import com.example.repositories.CustomerRepository;


    @Controller
    @SpringBootApplication
	public class SampleController {

	    @RequestMapping("/")
	    @ResponseBody
	    String home() {
	        return "Hello World!";
	    }
	    
	    public static void main(String[] args) {
			SpringApplication.run(SampleController.class);
		}
	    
	    @Autowired
	    public CustomerRepository repository;
	    
	   	    
	    @RequestMapping(value = "/customers", method = RequestMethod.GET)
	    @ResponseBody
	    public ModelAndView customers() {		
			List <Customer> customers =  repository.findAll();
			System.out.println(customers);
			
	    	ModelAndView mav =  new ModelAndView("customers/list");
	    	mav.addObject("customers", customers);
	    	return mav;
	    }
	    
	    @RequestMapping(value="/customer/create", method=RequestMethod.GET)
	    public String CustomerForm(Model model) {
	        model.addAttribute("customer", new Customer());
	        return "customers/create";
	    }
	    
	    @RequestMapping(value="/customer/create", method=RequestMethod.POST)
	   
	    public String CustomerSubmit(@ModelAttribute Customer customer, Model model) {
	    	
	    	model.addAttribute("customer", customer);
	        repository.save(customer);
	        return "customers/create";
	    }
	    
	    @RequestMapping(value="/customer/{id}/update", method=RequestMethod.GET)
	    
	    public String customerEditForm(@PathVariable("id") long id, Model model){
	    	Customer  customer = repository.findById(id);
	    	model.addAttribute("customer", customer);
	        return "customers/create";	
	    }
	    
	    
	    @RequestMapping(value="/customer/{id}/update", method=RequestMethod.POST)
		   
	    public String CustomerEditSubmit(@PathVariable("id") long id, @ModelAttribute Customer customer, Model model) {
	    	customer.setId(id);
	    	model.addAttribute("customer", customer);
	        repository.save(customer);
	        return "customers/create";
	    }
	    
	    
	    @RequestMapping(value="/customer/{id}/delete", method=RequestMethod.GET)
	    
	    public String customerDelete(@PathVariable("id") long id, Model model){
	    	repository.delete(id);
	    	return "redirect:/customers";
	    }
	    
	    
	}

