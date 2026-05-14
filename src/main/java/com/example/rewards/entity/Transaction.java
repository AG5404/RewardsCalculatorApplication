package com.example.rewards.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="transactions")
public class Transaction {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionId;

    @Column(name = "customer_id", nullable = false)
    private Long customerId;

    @Column(nullable = false)
    private LocalDate transactionDate;
    
    @Column(nullable = false)
    private Double amount;

	public Transaction() {
	}

	public Transaction(Long customerId, LocalDate transactionDate, Double amount) {
		this.customerId = customerId;
		this.transactionDate = transactionDate;
		this.amount = amount;
	}

	public Transaction(Long transactionId, Long customerId, LocalDate transactionDate, Double amount) {
		this.transactionId = transactionId;
		this.customerId = customerId;
		this.transactionDate = transactionDate;
		this.amount = amount;
	}

	public Long getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(Long transactionId) {
		this.transactionId = transactionId;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public LocalDate getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(LocalDate transactionDate) {
		this.transactionDate = transactionDate;
	}
	
	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}
	
	
    
    

}
