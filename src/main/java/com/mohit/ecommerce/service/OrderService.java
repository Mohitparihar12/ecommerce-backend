package com.mohit.ecommerce.service;

import com.mohit.ecommerce.dto.OrderItemRequest;
import com.mohit.ecommerce.dto.PlaceOrderRequest;
import com.mohit.ecommerce.entity.CustomerOrder;
import com.mohit.ecommerce.entity.OrderItem;
import com.mohit.ecommerce.entity.Product;
import com.mohit.ecommerce.exception.ResourceNotFoundException;
import com.mohit.ecommerce.repository.CustomerOrderRepository;
import com.mohit.ecommerce.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class OrderService {
    private final CustomerOrderRepository orders;
    private final ProductRepository products;

    public OrderService(CustomerOrderRepository orders, ProductRepository products) {
        this.orders = orders;
        this.products = products;
    }

    @Transactional
    public CustomerOrder placeOrder(PlaceOrderRequest request) {
        CustomerOrder order = new CustomerOrder();
        order.setCustomerId(request.getCustomerId());
        double total = 0;

        for (OrderItemRequest requestedItem : request.getItems()) {
            Product product = products.findById(requestedItem.getProductId())
                .orElseThrow(() -> new ResourceNotFoundException(
                    "Product not found: " + requestedItem.getProductId()));

            int quantity = requestedItem.getQuantity();
            if (quantity <= 0) {
                throw new IllegalArgumentException("Quantity must be greater than zero");
            }
            if (product.getStock() < quantity) {
                throw new IllegalArgumentException("Insufficient stock for product: " + product.getName());
            }

            product.setStock(product.getStock() - quantity);
            products.save(product);

            OrderItem item = new OrderItem();
            item.setProductId(product.getId());
            item.setProductName(product.getName());
            item.setUnitPrice(product.getPrice());
            item.setQuantity(quantity);
            order.addItem(item);
            total += product.getPrice() * quantity;
        }

        order.setTotalAmount(Math.round(total * 100.0) / 100.0);
        return orders.save(order);
    }

    public CustomerOrder getById(Long id) {
        return orders.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Order not found: " + id));
    }

    public List<CustomerOrder> byCustomer(Long customerId) {
        return orders.findByCustomerIdOrderByCreatedAtDesc(customerId);
    }
}
