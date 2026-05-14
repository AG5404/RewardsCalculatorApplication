package com.example.rewards.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.rewards.models.RewardResponse;
import com.example.rewards.service.RewardService;

@RestController
@RequestMapping("/api/v1")
public class RewardsController {

	private RewardService rewardService;
	
	public RewardsController(RewardService rewardService) {
		this.rewardService = rewardService;
	}

	@GetMapping("/rewards/customers/{customerId}")
	public ResponseEntity<RewardResponse> getRewardsByCustomer(@PathVariable Long customerId) {
	    RewardResponse response = rewardService.calculateRewardsByCustomer(customerId);
	    return ResponseEntity.ok(response);
	}
	
	@GetMapping("/rewards")
	public ResponseEntity<List<RewardResponse>> getAllRewards(){
		return ResponseEntity.ok(rewardService.getAllRewards());
	}
}
