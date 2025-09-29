package com.project.shopapp.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailDTO {
    @NotNull(message = "OrderID is required")
    @JsonProperty("order_id")
    private Long orderId;

    @NotNull(message = "ProductID is required")
    @JsonProperty("product_id")
    private Long productId;

    @Min(value = 0, message = "Total must be than >= 0")
    private Float price;

    @Min(value = 1, message = "Total must be than >= 1")
    @JsonProperty("number_of_products")
    private int numberOfProducts;

    @JsonProperty("total_money")
    @Min(value = 0, message = "Total must be than >= 0")
    private Float totalMoney;

    private String color;
}
