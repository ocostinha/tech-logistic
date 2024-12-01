package com.fiap.tech.tech_logistic.core.repository;

import com.fiap.tech.tech_logistic.domain.delivery.Delivery;

import java.util.Optional;
import java.util.Set;

public interface DeliveryRepository {

    Delivery save(Delivery order);

    Delivery update(Delivery order);

    Optional<Delivery> findById(Long id);

    Set<Delivery> findAll();

    Set<Delivery> findWaitingDeliveries();

    Optional<Delivery> findByOrderId(Long orderId);

}
