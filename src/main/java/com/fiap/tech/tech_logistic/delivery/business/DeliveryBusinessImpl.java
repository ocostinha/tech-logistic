package com.fiap.tech.tech_logistic.delivery.business;

import com.fiap.tech.tech_logistic.core.business.DeliveryBusiness;
import com.fiap.tech.tech_logistic.core.exception.BusinessException;
import com.fiap.tech.tech_logistic.core.queue.UpdateStatusOrderProducer;
import com.fiap.tech.tech_logistic.core.repository.DeliveryRepository;
import com.fiap.tech.tech_logistic.domain.delivery.Delivery;
import com.fiap.tech.tech_logistic.domain.delivery.status.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class DeliveryBusinessImpl implements DeliveryBusiness {

    private final DeliveryRepository deliveryRepository;

    private final UpdateStatusOrderProducer updateStatusOrderProducer;

    @Override
    public Delivery createDelivery(final Delivery delivery) {
        return deliveryRepository.save(delivery);
    }

    @Override
    public void updateDelivery(final Delivery delivery) {
        deliveryRepository.save(delivery);
    }

    @Override
    public Delivery getDelivery(final Long deliveryId) {
        return deliveryRepository.findById(deliveryId)
                .orElseThrow(() -> new BusinessException("Entrega não encontrada"));
    }

    @Override
    public Set<Delivery> getDeliveries() {
        return deliveryRepository.findAll();
    }

    @Override
    public Set<Delivery> getWaitingDeliveries() {
        return deliveryRepository.findWaitingDeliveries();
    }

    @Override
    public void addDriver(final Long deliveryId, final Long driverId) {
        Delivery delivery = getDelivery(deliveryId);

        delivery.setDriverId(driverId);
        delivery.setStatus(Status.COLLECTED);
        delivery.setCollectDateTime(LocalDateTime.now());

        updateDelivery(delivery);

        updateStatusOrderProducer.publish(delivery.getOrderId(), delivery.getStatus());
    }

    @Override
    public void cancelDelivery(final Long deliveryId) {
        Delivery delivery = getDelivery(deliveryId);

        delivery.setStatus(Status.CANCELLED);
        delivery.setCancelledDateTime(LocalDateTime.now());

        updateDelivery(delivery);

        updateStatusOrderProducer.publish(delivery.getOrderId(), delivery.getStatus());
    }

    @Override
    public void startDeliveryRoute(final Long deliveryId) {
        Delivery delivery = getDelivery(deliveryId);

        delivery.setStatus(Status.IN_TRANSPORT);

        updateDelivery(delivery);

        updateStatusOrderProducer.publish(delivery.getOrderId(), delivery.getStatus());
    }

    @Override
    public void terminateDeliveryRoute(final Long deliveryId) {
        Delivery delivery = getDelivery(deliveryId);

        delivery.setStatus(Status.DELIVERED);
        delivery.setDeliveredDateTime(LocalDateTime.now());

        updateDelivery(delivery);

        updateStatusOrderProducer.publish(delivery.getOrderId(), delivery.getStatus());
    }

}
