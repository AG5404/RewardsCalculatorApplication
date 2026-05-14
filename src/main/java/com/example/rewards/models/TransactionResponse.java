package com.example.rewards.models;

import java.time.LocalDate;

public record TransactionResponse(Long transactionId, Long customerId, LocalDate transactionDate,  Double amount) { 

}
