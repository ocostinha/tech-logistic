package com.fiap.tech.tech_logistic.delivery.queue;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fiap.tech.tech_logistic.core.business.DeliveryBusiness;
import com.fiap.tech.tech_logistic.core.gateway.ClientGateway;
import com.fiap.tech.tech_logistic.core.queue.UpdateStatusOrderProducer;
import com.fiap.tech.tech_logistic.delivery.adapter.DeliveryAdapter;
import com.fiap.tech.tech_logistic.delivery.dto.OrderDTO;
import com.fiap.tech.tech_logistic.domain.delivery.Delivery;
import io.awspring.cloud.sqs.annotation.SqsListener;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class LogisticConsumer {

    private final DeliveryBusiness deliveryBusiness;

    private final DeliveryAdapter deliveryAdapter = DeliveryAdapter.INSTANCE;

    private final UpdateStatusOrderProducer updateStatusOrderProducer;

    private final ClientGateway clientGateway;

    private final ObjectMapper objectMapper;

    @SqsListener("${queue.logistic.name}")
    public void createDelivery(final String orderString) {
        try {
            final OrderDTO order = objectMapper.readValue(orderString, OrderDTO.class);

            if (order.getCancelled()) {
                deliveryBusiness.cancelDelivery(
                        deliveryBusiness.getDeliveryByOrder(
                                order.getId()
                        ).get().getId()
                );

                return;
            }

            final Delivery delivery = deliveryAdapter.toDomain(order);
            delivery.setAddress(clientGateway.getClientAddress(order.getClientId()));

            final Optional<Delivery> deliveryExists = deliveryBusiness.getDeliveryByOrder(
                    order.getId());

            Delivery persistedDelivery;

            if (deliveryExists.isPresent()) {
                persistedDelivery = deliveryBusiness.updateDelivery(delivery);
            } else {
                persistedDelivery = deliveryBusiness.createDelivery(delivery);
            }

            updateStatusOrderProducer.publish(
                    persistedDelivery.getOrderId(), persistedDelivery.getStatus());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
