package com.example.rewards.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.rewards.entity.Customer;
import com.example.rewards.entity.Transaction;
import com.example.rewards.exception.ResourceNotFoundException;
import com.example.rewards.models.CustomerResponse;
import com.example.rewards.models.TransactionRequest;
import com.example.rewards.models.TransactionResponse;
import com.example.rewards.repository.CustomerRepository;
import com.example.rewards.repository.TransactionRepository;

@Service
public class TransactionService {

	private TransactionRepository transactionRepository;
	private CustomerRepository customerRepository;
	
	public TransactionService(TransactionRepository transactionRepository, CustomerRepository customerRepository) {
		this.transactionRepository = transactionRepository;
		this.customerRepository = customerRepository;
	}
	
	public Transaction saveTransaction(TransactionRequest request) {
		customerRepository.findById(request.customerId())
				.orElseThrow(() -> new ResourceNotFoundException("Customer with id " + request.customerId() +" is not found"));	
		
		Transaction transaction = new Transaction();
        transaction.setCustomerId(request.customerId());
        transaction.setAmount(request.amount());
        transaction.setTransactionDate(
            request.transactionDate() != null ? request.transactionDate() : LocalDate.now()
        );

        return transactionRepository.save(transaction);
    }
	
	public List<TransactionResponse> getAllTransactions(){
		List<Transaction> allTransactions = transactionRepository.findAll();
		List<TransactionResponse> transactionList = new ArrayList<>();
		for(Transaction transaction : allTransactions) {
			transactionList.add(new TransactionResponse(transaction.getTransactionId(),transaction.getCustomerId(), transaction.getTransactionDate(), transaction.getAmount()));
		}
		
		return transactionList;
	}
}
