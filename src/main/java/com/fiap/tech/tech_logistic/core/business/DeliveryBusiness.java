package com.fiap.tech.tech_logistic.core.business;

import com.fiap.tech.tech_logistic.domain.delivery.Delivery;

import java.util.Optional;
import java.util.Set;

public interface DeliveryBusiness {

    Delivery createDelivery(Delivery delivery);

    Delivery updateDelivery(Delivery delivery);

    Delivery getDelivery(Long deliveryId);

    Optional<Delivery> getDeliveryByOrder(Long orderId);

    Set<Delivery> getDeliveries();

    Set<Delivery> getWaitingDeliveries();

    void addDriver(Long deliveryId, Long driverId);

    void cancelDelivery(Long deliveryId);

    void startDeliveryRoute(Long deliveryId);

    void terminateDeliveryRoute(Long deliveryId);

}
