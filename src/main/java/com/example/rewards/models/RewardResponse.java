package com.example.rewards.models;

import java.util.Map;

public record RewardResponse(Long customerId, String customerName, Map<String, Integer> monthlyPoints, int totalPoints) {
}
