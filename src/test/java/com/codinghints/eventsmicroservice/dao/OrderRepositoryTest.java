package com.codinghints.eventsmicroservice.dao;

import com.codinghints.eventsmicroservice.model.Event;
import com.codinghints.eventsmicroservice.model.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OrderRepositoryTest {

    private final OrderRepository orderRepository;
    private final EventRepository eventRepository;

    @Autowired
    public OrderRepositoryTest(OrderRepository orderRepository, EventRepository eventRepository){
        this.orderRepository = orderRepository;
        this.eventRepository = eventRepository;
    }

    @Test
    public void printOrders(){
        List<Order> orders = orderRepository.findAll();
        System.out.println("orders = " + orders);
    }

    @Test
    public void saveOrder(){
        Event event = Event.builder()
                .description("Order")
                .price(BigDecimal.TEN)
                .url("http://")
                .build();

        Event savedEvent = eventRepository.save(event);

        Order order = Order.builder()
                .stripeId("123456789")
                .totalAmount(BigDecimal.valueOf(1000))
                .event(savedEvent)
                .build();
        Order savedOrder = orderRepository.save(order);
        System.out.println("savedOrder = " + savedOrder);
    }
}