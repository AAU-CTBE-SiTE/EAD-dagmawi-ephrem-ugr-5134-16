package com.site.order_service.service;

import com.site.order_service.client.CustomerClient;
import com.site.order_service.client.OrderRequest;
import com.site.order_service.model.Order;
import com.site.order_service.repository.OrderRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final CustomerClient customerClient;

    public OrderService(OrderRepository orderRepository,
                        CustomerClient customerClient) {
        this.orderRepository = orderRepository;
        this.customerClient = customerClient;
    }

    public Order createOrder(OrderRequest request) {

        // Verify customer through Customer Service
        customerClient.getCustomerById(request.getCustomerId());

        Order order = new Order(
                request.getProductName(),
                request.getQuantity(),
                request.getTotalAmount(),
                request.getCustomerId()
        );

        return orderRepository.save(order);
    }

    public Order getOrderById(Long id) {

        return orderRepository.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Order not found: " + id
                        ));
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public List<Order> getOrdersByCustomerId(String customerId) {

        // Verify customer exists before retrieving orders
        customerClient.getCustomerById(customerId);

        return orderRepository.findByCustomerId(customerId);
    }
}