package com.fiap.tech.tech_logistic.delivery.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class OrderDTO {

    private Long id;

    private Long clientId;

    private List<OrderItemDTO> items;

    private LocalDateTime orderDate;

    private LocalDateTime deliveryDate;

    private String note;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private String status;

    private Boolean cancelled;

    @Getter
    @Setter
    @AllArgsConstructor
    @Builder
    public static class OrderItemDTO {

        private Long id;

        private Long productId;

        private Integer quantity;

        private Double unitPrice;

    }

}

