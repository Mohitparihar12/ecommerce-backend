package com.mohit.ecommerce.controller;

import com.mohit.ecommerce.dto.PlaceOrderRequest;
import com.mohit.ecommerce.entity.CustomerOrder;
import com.mohit.ecommerce.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderService service;

    public OrderController(OrderService service) { this.service = service; }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerOrder placeOrder(@Valid @RequestBody PlaceOrderRequest request) {
        return service.placeOrder(request);
    }

    @GetMapping("/{id}")
    public CustomerOrder getById(@PathVariable Long id) { return service.getById(id); }

    @GetMapping("/customer/{customerId}")
    public List<CustomerOrder> byCustomer(@PathVariable Long customerId) {
        return service.byCustomer(customerId);
    }
}
