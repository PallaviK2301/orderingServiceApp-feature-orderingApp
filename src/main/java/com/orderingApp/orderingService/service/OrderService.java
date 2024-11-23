package com.orderingApp.orderingService.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import com.orderingApp.orderingService.model.Product;


@Service
public class OrderService {

    private final RestTemplate restTemplate;

    @Value("${order.service.url}") // URL of Order Service
    private String orderServiceUrl;

    public OrderService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    // Place an order - Require JWT token to authenticate
    public String placeOrder(String jwtToken, Product order) {
        String url = orderServiceUrl ; //+ "/api/orders";  // API to place an order

        // Create headers and add JWT token in Authorization header
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + jwtToken); // Add JWT token to header
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Create HTTP entity with headers and body (Order details)
        HttpEntity<Product> entity = new HttpEntity<>(order, headers);

        // Send POST request to Order Service to place an order
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
        return response.getBody(); // Response (order confirmation or details)
    }
}

