package com.example.rewards.models;

import java.time.LocalDateTime;

public record APIError(int statusCode, String errorMessage, String message, LocalDateTime timestamp, String path) {

}
