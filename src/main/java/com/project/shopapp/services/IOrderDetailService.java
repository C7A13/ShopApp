package com.project.shopapp.services;

import java.util.List;

import com.project.shopapp.dtos.OrderDetailDTO;
import com.project.shopapp.responses.OrderDetailResponse;

public interface IOrderDetailService {
    OrderDetailResponse createOrderDetail(OrderDetailDTO orderDetailDTO);

    OrderDetailResponse getOrderDetailById(Long id);

    OrderDetailResponse updateOrderDetailById(Long id, OrderDetailDTO orderDetailDTO);

    void deleteOrderDetailById(Long id);

    List<OrderDetailResponse> getOrderDetailByOrderId(Long orderId);
}
