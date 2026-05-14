package com.example.rewards.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.rewards.entity.Transaction;
import com.example.rewards.models.TransactionRequest;
import com.example.rewards.models.TransactionResponse;
import com.example.rewards.service.TransactionService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1")
public class TransactionController {

	private TransactionService transactionService;
	
	public TransactionController(TransactionService transactionService) {
		this.transactionService = transactionService;
	}

	@PostMapping("/transactions")
	public ResponseEntity<TransactionResponse> addTransaction(@Valid @RequestBody TransactionRequest request) {
	    Transaction transaction = transactionService.saveTransaction(request);
	    
	    TransactionResponse response = new TransactionResponse(
	        transaction.getTransactionId(),
	        transaction.getCustomerId(),
	        transaction.getTransactionDate(),
	        transaction.getAmount()
	    );
	    
	    return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
	
	@GetMapping("/transactions")
	public ResponseEntity<List<TransactionResponse>> getAllTransaction() {
		List<TransactionResponse> transactionList = transactionService.getAllTransactions();
		return ResponseEntity.ok(transactionList);
		
	}
}
