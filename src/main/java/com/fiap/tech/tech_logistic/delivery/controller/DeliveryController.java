package com.fiap.tech.tech_logistic.delivery.controller;

import com.fiap.tech.tech_logistic.core.business.DeliveryBusiness;
import com.fiap.tech.tech_logistic.delivery.adapter.DeliveryAdapter;
import com.fiap.tech.tech_logistic.delivery.dto.DeliveryDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class DeliveryController {

    private final DeliveryBusiness deliveryBusiness;

    private final DeliveryAdapter deliveryAdapter = DeliveryAdapter.INSTANCE;

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PostMapping("/api/delivery/{id}/driver/{driverId}")
    public void addDriver(@PathVariable("id") Long deliveryId,
                          @PathVariable("driverId") Long driverId) {
        deliveryBusiness.addDriver(deliveryId, driverId);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PostMapping("/api/delivery/{id}/start_route")
    public void startRoute(@PathVariable("id") Long deliveryId) {
        deliveryBusiness.startDeliveryRoute(deliveryId);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PostMapping("/api/delivery/{id}/cancel")
    public void cancel(@PathVariable("id") Long deliveryId) {
        deliveryBusiness.cancelDelivery(deliveryId);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PostMapping("/api/delivery/{id}/terminate")
    public void terminate(@PathVariable("id") Long deliveryId) {
        deliveryBusiness.terminateDeliveryRoute(deliveryId);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/api/delivery/{id}")
    public DeliveryDTO getDelivery(@PathVariable Long id) {
        return deliveryAdapter.fromDomain(
                deliveryBusiness.getDelivery(id)
        );
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/api/delivery")
    public Set<DeliveryDTO> getDeliveries() {
        return deliveryBusiness.getDeliveries().stream()
                .map(deliveryAdapter::fromDomain)
                .collect(Collectors.toSet());
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/api/delivery/waiting")
    public Set<DeliveryDTO> getDeliveriesWaiting() {
        return deliveryBusiness.getWaitingDeliveries().stream()
                .map(deliveryAdapter::fromDomain)
                .collect(Collectors.toSet());
    }

}