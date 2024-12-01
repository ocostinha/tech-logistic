package com.fiap.tech.tech_logistic.domain.delivery;

import com.fiap.tech.tech_logistic.domain.delivery.status.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class Delivery {

    private Long id;

    private Long driverId;

    private Long orderId;

    private LocalDateTime deliveryDate;

    private LocalDateTime collectDate;

    private LocalDateTime deliveredDate;

    private LocalDateTime cancelledDate;

    private List<DeliveryItem> items;

    private Status status;

    private String note;

    private Address address;

    @Getter
    @Setter
    @AllArgsConstructor
    public static class DeliveryItem {

        private Long id;

        private Long productId;

        private Integer quantity;

    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class Address {

        private Long id;

        private String address;

        private String city;

        private String state;

        private String zipCode;

    }

}
