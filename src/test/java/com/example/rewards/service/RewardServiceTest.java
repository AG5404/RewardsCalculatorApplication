package com.example.rewards.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.rewards.entity.Customer;
import com.example.rewards.entity.Transaction;
import com.example.rewards.models.RewardResponse;
import com.example.rewards.repository.CustomerRepository;
import com.example.rewards.repository.TransactionRepository;

@ExtendWith(MockitoExtension.class)
public class RewardServiceTest {
	
	@Mock
    private TransactionRepository transactionRepository;

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private RewardService rewardService;
    
    @Test
    void testRewardsCalculator() {
        assertEquals(650, rewardService.rewardsCalculator(400.0), "Points for $400 should be 650");
        assertEquals(30, rewardService.rewardsCalculator(80.0), "Points for $80 should be 30");
        assertEquals(0, rewardService.rewardsCalculator(40.0), "Points for $40 should be 0");
        assertEquals(0, rewardService.rewardsCalculator(-10.0), "Negative amounts should be 0");
     
    }
    
     @Test
     void testCalculateRewardsByCustomer() {       
        	Long customerId = 1L;
            Customer mockCustomer = new Customer(customerId, "test1", "test1@example.com");
            
            Transaction t1 = new Transaction(1L, customerId,  LocalDate.of(2026, 1, 10), 120.0);
            Transaction t2 = new Transaction(1L, customerId, LocalDate.of(2026, 1, 20),  85.0);  
            Transaction t3 = new Transaction(1L, customerId, LocalDate.of(2026, 2, 05), 50.0);  

            when(customerRepository.findById(customerId)).thenReturn(Optional.of(mockCustomer));
            when(transactionRepository.findByCustomerId(customerId)).thenReturn(Arrays.asList(t1, t2, t3));

           
            RewardResponse result = rewardService.calculateRewardsByCustomer(customerId);

            assertEquals("test1", result.customerName());
            assertEquals(125, result.monthlyPoints().get("JANUARY"));
            assertEquals(0, result.monthlyPoints().get("FEBRUARY"));
            assertEquals(125, result.totalPoints());
        }
}
