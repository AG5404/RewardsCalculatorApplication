package com.example.rewards.models;

import java.time.LocalDate;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record TransactionRequest(Long customerId, LocalDate transactionDate,  @NotNull(message = "Amount cannot be null") @Positive(message = "Amount must be a positive value") @Min(value = 1, message = "Minimum transaction amount is 1.0") Double amount) {

}
