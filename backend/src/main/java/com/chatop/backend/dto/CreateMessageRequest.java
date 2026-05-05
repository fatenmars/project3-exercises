package com.chatop.backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CreateMessageRequest {

    @JsonProperty("rental_id")
    private Long rentalId;
    @JsonProperty("user_id")
    private Long userId;
    private String message;

    public Long getRentalId() {
        return rentalId;
    }

    public Long getUserId() {
        return userId;
    }

    public String getMessage() {
        return message;
    }

    public void setRentalId(Long rentalId) {
        this.rentalId = rentalId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
