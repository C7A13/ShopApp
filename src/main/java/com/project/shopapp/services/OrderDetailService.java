package com.project.shopapp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.project.shopapp.dtos.OrderDetailDTO;
import com.project.shopapp.exceptions.DataNotFoundException;
import com.project.shopapp.mapper.OrderDetailMapper;
import com.project.shopapp.models.Order;
import com.project.shopapp.models.OrderDetail;
import com.project.shopapp.models.Product;
import com.project.shopapp.repositories.OrderDetailRepository;
import com.project.shopapp.repositories.OrderRepository;
import com.project.shopapp.repositories.ProductRepository;
import com.project.shopapp.responses.OrderDetailResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderDetailService implements IOrderDetailService {
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final OrderDetailMapper orderDetailMapper;
    private final OrderDetailRepository orderDetailRepository;

    @Override
    public OrderDetailResponse createOrderDetail(OrderDetailDTO orderDetailDTO) {
        Order existingOrder = orderRepository.findById(orderDetailDTO.getOrderId())
                .orElseThrow(() -> new DataNotFoundException("Can't find OrderId = " + orderDetailDTO.getOrderId()));
        Product existingProduct = productRepository.findById(orderDetailDTO.getProductId())
                .orElseThrow(
                        () -> new DataNotFoundException("Can't find ProductId = " + orderDetailDTO.getProductId()));
        OrderDetail newOrderDetail = orderDetailMapper.toEntity(orderDetailDTO);
        newOrderDetail.setProduct(existingProduct);
        newOrderDetail.setOrder(existingOrder);
        orderDetailRepository.save(newOrderDetail);
        OrderDetailResponse orderDetailResponse = orderDetailMapper.toResponse(newOrderDetail);
        return orderDetailResponse;
    }

    @Override
    public OrderDetailResponse getOrderDetailById(Long id) {
        OrderDetail orderDetail = orderDetailRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Can't find OrderDetailId = " + id));
        return orderDetailMapper.toResponse(orderDetail);

    }

    @Override
    public OrderDetailResponse updateOrderDetailById(Long id, OrderDetailDTO orderDetailDTO) {
        OrderDetail existingOrderDetail = orderDetailRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Can't find OrderDetailId = " + id));
        orderDetailMapper.updateEntity(existingOrderDetail, orderDetailDTO);
        orderDetailRepository.save(existingOrderDetail);
        return orderDetailMapper.toResponse(existingOrderDetail);
    }

    @Override
    public void deleteOrderDetailById(Long id) {
        Optional<OrderDetail> optionalOrderDetail = orderDetailRepository.findById(id);
        optionalOrderDetail.ifPresent(orderDetailRepository::delete);
    }

    @Override
    public List<OrderDetailResponse> getOrderDetailByOrderId(Long orderId) {
        List<OrderDetail> OrderDetails = orderDetailRepository.findByOrderId(orderId);
        return orderDetailMapper.toResponseList(OrderDetails);
    }

}
