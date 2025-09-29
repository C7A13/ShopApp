package com.project.shopapp.services;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.project.shopapp.dtos.OrderDTO;
import com.project.shopapp.exceptions.DataNotFoundException;
import com.project.shopapp.mapper.OrderMapper;
import com.project.shopapp.models.Order;
import com.project.shopapp.models.OrderStatus;
import com.project.shopapp.models.User;
import com.project.shopapp.repositories.OrderRepository;
import com.project.shopapp.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService implements IOrderService {
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    @Override
    public Order createOrder(OrderDTO orderDTO) {
        User existingUser = userRepository.findById(orderDTO.getUserID())
                .orElseThrow(() -> new DataNotFoundException("Can't not find UserID = " + orderDTO.getUserID()));
        Order newOrder = orderMapper.toEntity(orderDTO);
        newOrder.setUser(existingUser);
        newOrder.setOrderDate(new Date());
        newOrder.setStatus(OrderStatus.PENDING);
        LocalDate shippingDate = orderDTO.getShippingDate() == null ? LocalDate.now() : orderDTO.getShippingDate();
        if (shippingDate.isBefore(LocalDate.now())) {
            throw new DataNotFoundException("Date must be least today !");
        }
        newOrder.setShippingDate(shippingDate);
        newOrder.setActive(true);
        orderRepository.save(newOrder);
        return newOrder;
    }

    @Override
    public Order getOrderById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Can't find OrderId " + id));
    }

    @Override
    public Order updateOrder(Long id, OrderDTO orderDTO) {
        Order existingOrder = orderRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Can't not find UserID = " + id));
        User existingUser = userRepository.findById(orderDTO.getUserID())
                .orElseThrow(() -> new DataNotFoundException("Can't not find UserID = " + orderDTO.getUserID()));
        orderMapper.updateEntity(existingOrder, orderDTO);
        orderRepository.save(existingOrder);
        return existingOrder;
    }

    @Override
    public void deleteOrder(Long id) {
        Order order = orderRepository.findById(id).orElse(null);
        if (order != null) {
            order.setActive(false);
            orderRepository.save(order);
        }
    }

    @Override
    public List<Order> findByUserId(Long userId) {
        return orderRepository.findByUserId(userId);
    }

}
