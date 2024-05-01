package com.henryschein.orderservicetest;

import static org.junit.jupiter.api.Assertions.*;


import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.henryschein.orderservice.controller.OrderController;
import com.henryschein.orderservice.model.Order;
import com.henryschein.orderservice.service.OrderService;
import com.henryschein.orderservice.repository.OrderRepository;

@RunWith(MockitoJUnitRunner.class)
public class OrderControllerTest {

    @Mock
    private OrderService orderService;

    @Mock
    private OrderRepository orderRepository; // Mock repository

    @InjectMocks
    private OrderController orderController;

    private Order sampleOrder;

    @Before
    public void setUp() {
        // Sample order for testing
        sampleOrder = new Order();
        sampleOrder.setOrderId(1L);
        sampleOrder.setProducts("Product1:10,Product2:20");
        sampleOrder.setCustomerName("Test Customer");
        sampleOrder.setEmailId("test@example.com");
    }

    @Test
    public void testCreateOrder() {
        // Setup behavior of orderService mock
        when(orderService.createOrder(any(Order.class))).thenReturn(sampleOrder);

        // Call the method being tested
        Order createdOrder = orderController.createOrder(sampleOrder);

        // Assertions
        assertEquals(sampleOrder, createdOrder);
    }

    @Test
    public void testGetOrderById() {
        // Setup behavior of orderService mock
        when(orderService.getOrderById(1L)).thenReturn(Optional.of(sampleOrder));

        // Call the method being tested
        Optional<Order> retrievedOrder = orderController.getOrderById(1L);

        // Assertions
        assertEquals(sampleOrder, retrievedOrder.orElse(null));
    }

    @Test
    public void testUpdateOrder() {
        // Setup behavior of orderService mock
        when(orderService.updateOrder(any(Order.class))).thenReturn(sampleOrder);

        // Call the method being tested
        Order updatedOrder = orderController.updateOrder(sampleOrder);

        // Assertions
        assertEquals(sampleOrder, updatedOrder);
    }

    @Test
    public void testDeleteOrder() {
        // Setup behavior of orderService mock
        when(orderService.deleteOrder(1L)).thenReturn("Order removed successfully!");

        // Call the method being tested
        String result = orderController.deleteOrder(1L);

        // Assertions
        assertEquals("Order removed successfully!", result);
    }

    // Add similar test methods for other controller methods like createOrders, getOrders, updateOrders, etc.
}
