package com.example.rewards.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.rewards.entity.Customer;
import com.example.rewards.models.CustomerRequest;
import com.example.rewards.models.CustomerResponse;
import com.example.rewards.repository.CustomerRepository;

@Service
public class CustomerService {

	private CustomerRepository customerRepository;

	public CustomerService(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}
	
	
	public Customer saveCustomer(CustomerRequest customerRequest) {
		Customer customer = new Customer(customerRequest.customerName(), customerRequest.customerEmail());
		return customerRepository.save(customer);
	}
	
	public List<CustomerResponse> getAllCustomers(){
		List<Customer> allCustomers = customerRepository.findAll();
		List<CustomerResponse> customerList = new ArrayList<>();
		for(Customer customer : allCustomers) {
			customerList.add(new CustomerResponse(customer.getCustomerId(), customer.getCustomerName(), customer.getCustomerEmail()));
		}
		
		return customerList;
	}
}
