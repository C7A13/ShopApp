package com.project.shopapp.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.shopapp.dtos.OrderDetailDTO;
import com.project.shopapp.models.OrderDetail;
import com.project.shopapp.responses.OrderDetailResponse;
import com.project.shopapp.services.OrderDetailService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("${api.prefix}/order_details")
@RequiredArgsConstructor
public class OrderDetailController {

    private final OrderDetailService orderDetailService;

    @PostMapping("")
    private ResponseEntity<?> createOrderDetail(@Valid @RequestBody OrderDetailDTO orderDetailDTO) {
        OrderDetailResponse orderDetailResponse = orderDetailService.createOrderDetail(orderDetailDTO);
        return ResponseEntity.ok(orderDetailResponse);

    }

    @GetMapping("/{id}")
    private ResponseEntity<?> getOrderDetailById(@Valid @PathVariable("id") Long id) {
        OrderDetailResponse orderDetail = orderDetailService.getOrderDetailById(id);
        return ResponseEntity.ok(orderDetail);
    }

    // Lấy ra list orderDetail thông qua orderid
    @GetMapping("/order/{order_id}")
    private ResponseEntity<?> getListOrderDetailByOrderId(@Valid @PathVariable("order_id") Long orderId) {
        List<OrderDetailResponse> orderDetails = orderDetailService.getOrderDetailByOrderId(orderId);
        return ResponseEntity.ok(orderDetails);
    }

    @PutMapping("/{id}")
    private ResponseEntity<?> updateOrderDetail(@Valid @PathVariable("id") Long id,
            @RequestBody OrderDetailDTO orderDetailDTO) {
        OrderDetailResponse orderDetailResponse = orderDetailService.updateOrderDetailById(id, orderDetailDTO);

        return ResponseEntity.ok(orderDetailResponse);
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<?> deleteOrderDetail(@Valid @PathVariable("id") Long id) {
        orderDetailService.deleteOrderDetailById(id);
        return ResponseEntity.ok("delete OrderDetail Successfully !!");
    }
}
