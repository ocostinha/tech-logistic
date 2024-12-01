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
import java.util.Optional;
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
    public Delivery updateDelivery(final Delivery delivery) {
        return deliveryRepository.update(delivery);
    }

    @Override
    public Delivery getDelivery(final Long deliveryId) {
        return deliveryRepository.findById(deliveryId)
                .orElseThrow(() -> new BusinessException("Entrega não encontrada"));
    }

    @Override
    public Optional<Delivery> getDeliveryByOrder(final Long orderId) {
        return deliveryRepository.findByOrderId(orderId);
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
        delivery.setCollectDate(LocalDateTime.now());

        updateDelivery(delivery);

        updateStatusOrderProducer.publish(delivery.getOrderId(), delivery.getStatus());
    }

    @Override
    public void cancelDelivery(final Long deliveryId) {
        Delivery delivery = getDelivery(deliveryId);

        if (delivery.getStatus().equals(Status.DELIVERED)) {
            throw new BusinessException("Entrega já foi finalizada");
        }

        if (delivery.getStatus().equals(Status.CANCELLED)) {
            throw new BusinessException("Entrega já foi cancelada");
        }

        delivery.setStatus(Status.CANCELLED);
        delivery.setCancelledDate(LocalDateTime.now());

        updateDelivery(delivery);

        updateStatusOrderProducer.publish(delivery.getOrderId(), delivery.getStatus());
    }

    @Override
    public void startDeliveryRoute(final Long deliveryId) {
        Delivery delivery = getDelivery(deliveryId);

        if (delivery.getDriverId() == null) {
            throw new BusinessException("Entrega não possui motorista");
        }

        delivery.setStatus(Status.IN_TRANSPORT);

        updateDelivery(delivery);

        updateStatusOrderProducer.publish(delivery.getOrderId(), delivery.getStatus());
    }

    @Override
    public void terminateDeliveryRoute(final Long deliveryId) {
        Delivery delivery = getDelivery(deliveryId);

        if (!delivery.getStatus().equals(Status.IN_TRANSPORT)) {
            throw new BusinessException("Entrega deve estar em andamento para ser finalizada");
        }

        delivery.setStatus(Status.DELIVERED);
        delivery.setDeliveredDate(LocalDateTime.now());

        updateDelivery(delivery);

        updateStatusOrderProducer.publish(delivery.getOrderId(), delivery.getStatus());
    }

}
