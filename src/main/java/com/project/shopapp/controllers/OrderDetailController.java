package com.project.shopapp.controllers;

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

import jakarta.validation.Valid;

@RestController
@RequestMapping("${api.prefix}/order_details")
public class OrderDetailController {

    @PostMapping("")
    private ResponseEntity<?> createOrderDetail(@Valid @RequestBody OrderDetailDTO orderDetailDTO) {
        return ResponseEntity.ok("This is createOrderDetail");
    }

    @GetMapping("/{id}")
    private ResponseEntity<?> getOrderDetailById(@Valid @PathVariable("id") Long id) {
        return ResponseEntity.ok("get OrderDetail with Id = " + id);
    }

    // Lấy ra list orderDetail thông qua orderid
    @GetMapping("/order/{order_id}")
    private ResponseEntity<?> getListOrderDetailByOrderId(@Valid @PathVariable("order_id") Long orderId) {
        return ResponseEntity.ok("get List OrderDetail with Id = " + orderId);
    }

    @PutMapping("/{id}")
    private ResponseEntity<?> updateOrderDetail(@Valid @PathVariable("id") Long id,
            @RequestBody OrderDetailDTO orderDetailDTO) {
        return ResponseEntity.ok("update OrderDetail with Id = " + id +
                "new OrderDetail " + orderDetailDTO);
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<?> deleteOrderDetail(@Valid @PathVariable("id") Long id) {
        return ResponseEntity.ok("delete OrderDetail with Id = " + id);
    }
}
