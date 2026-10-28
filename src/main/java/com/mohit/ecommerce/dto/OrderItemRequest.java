package com.mohit.ecommerce.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class OrderItemRequest {
    @NotNull(message = "Product ID is required")
    private Long productId;

    @Positive(message = "Quantity must be greater than zero")
    private int quantity;

    public Long getProductId() { return productId; }
    public int getQuantity() { return quantity; }
    public void setProductId(Long productId) { this.productId = productId; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
}
