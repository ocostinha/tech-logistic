package com.fiap.tech.tech_logistic.delivery.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fiap.tech.tech_logistic.domain.delivery.status.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class DeliveryDTO {

    private Long id;

    private Long driverId;

    private Long orderId;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private LocalDateTime deliveryDateTime;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private LocalDateTime collectDateTime;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private LocalDateTime deliveredDateTime;

    private List<DeliveryItemDTO> items;

    private Status status;

    @Getter
    @Setter
    @AllArgsConstructor
    public static class DeliveryItemDTO {

        private Long id;

        private Long productId;

        private Integer quantity;

    }

}
