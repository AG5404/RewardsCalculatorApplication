package com.example.rewards.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.rewards.entity.Customer;
import com.example.rewards.models.CustomerRequest;
import com.example.rewards.models.CustomerResponse;
import com.example.rewards.service.CustomerService;

@RestController
@RequestMapping("/api/v1")
public class CustomerController {

	private CustomerService customerService;
	
	public CustomerController(CustomerService customerService) {
		this.customerService = customerService;
	}

	@PostMapping("/customers")
	public ResponseEntity<CustomerResponse> createUser(@RequestBody CustomerRequest customerRequest){
		Customer customer  = customerService.saveCustomer(customerRequest);
		CustomerResponse customerResponse = new CustomerResponse(customer.getCustomerId(), customer.getCustomerName(), customer.getCustomerEmail());
		return ResponseEntity.status(HttpStatus.CREATED).body(customerResponse);
	}
	
	@GetMapping("/customers")
	public ResponseEntity<List<CustomerResponse>> getAllCustomers() {
		List<CustomerResponse> customerList = customerService.getAllCustomers();
		return ResponseEntity.ok(customerList);
		
	}
}
