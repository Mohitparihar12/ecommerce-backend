package com.mohit.ecommerce.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.util.List;

public class PlaceOrderRequest {
    @NotNull(message = "Customer ID is required")
    @Positive(message = "Customer ID must be positive")
    private Long customerId;

    @NotEmpty(message = "An order must contain at least one item")
    @Valid
    private List<OrderItemRequest> items;

    public Long getCustomerId() { return customerId; }
    public List<OrderItemRequest> getItems() { return items; }
    public void setCustomerId(Long customerId) { this.customerId = customerId; }
    public void setItems(List<OrderItemRequest> items) { this.items = items; }
}
