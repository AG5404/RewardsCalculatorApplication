package com.example.rewards.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.rewards.entity.Customer;
import com.example.rewards.entity.Transaction;
import com.example.rewards.exception.ResourceNotFoundException;
import com.example.rewards.models.CustomerResponse;
import com.example.rewards.models.RewardResponse;
import com.example.rewards.repository.CustomerRepository;
import com.example.rewards.repository.TransactionRepository;

@Service
public class RewardService {
	
	private CustomerRepository customerRepository;
	private TransactionRepository transactionRepository;
	
	public RewardService(CustomerRepository customerRepository, TransactionRepository transactionRepository) {
		this.customerRepository = customerRepository;
		this.transactionRepository =transactionRepository;
	}

	public RewardResponse calculateRewardsByCustomer(Long customerId) {
	  
		Customer customer = customerRepository.findById(customerId)
							.orElseThrow(() -> new ResourceNotFoundException("Customer with id " + customerId +" is not found"));

	    List<Transaction> transactions = transactionRepository.findByCustomerId(customerId);
	    
	    Map<String, Integer> monthlyPoints = pointsCalculator(transactions);

	    int totalPoints = monthlyPoints.values().stream().reduce(0,Integer::sum);

	    return new RewardResponse(customer.getCustomerId(),customer.getCustomerName(),monthlyPoints,totalPoints);
	}
	
	public Map<String, Integer> pointsCalculator(List<Transaction> transactions){
		Map<String, Integer> map = new HashMap<>();
		
		for(Transaction transaction : transactions) {
			String month = transaction.getTransactionDate().getMonth().name();
			int rewards =  rewardsCalculator(transaction.getAmount());
			map.put(month, map.getOrDefault(month, 0) + rewards);
		}
		return map;
	}
 	 
	
	public int rewardsCalculator(double amount) {
			int rewards =0;
			
			if(amount <=50)
				return 0;
			
			if(amount >100) {
				rewards += (amount-100)*2;
				rewards += 50;	
			}
			else {
				rewards += (amount -50);
			}
			
			return rewards;
	}
	
	public List<CustomerResponse> getAllCustomers(){
		List<Customer> allCustomers = customerRepository.findAll();
		List<CustomerResponse> customerList = new ArrayList<>();
		for(Customer customer : allCustomers) {
			customerList.add(new CustomerResponse(customer.getCustomerId(), customer.getCustomerName(), customer.getCustomerEmail()));
		}
		
		return customerList;
	}
	
	public List<RewardResponse> getAllRewards(){
		List<Customer> customers = customerRepository.findAll();
		return customers.stream()
				.map(customer -> calculateRewardsByCustomer(customer.getCustomerId()))
				.collect(Collectors.toList());
	}
}
