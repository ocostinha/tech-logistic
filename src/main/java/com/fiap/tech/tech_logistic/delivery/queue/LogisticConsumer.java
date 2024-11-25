package com.fiap.tech.tech_logistic.delivery.queue;

import com.fiap.tech.tech_logistic.core.business.DeliveryBusiness;
import com.fiap.tech.tech_logistic.core.queue.UpdateStatusOrderProducer;
import com.fiap.tech.tech_logistic.delivery.adapter.DeliveryAdapter;
import com.fiap.tech.tech_logistic.delivery.dto.OrderDTO;
import com.fiap.tech.tech_logistic.delivery.gateway.ClientGateway;
import com.fiap.tech.tech_logistic.domain.delivery.Delivery;
import io.awspring.cloud.sqs.annotation.SqsListener;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LogisticConsumer {

    private final DeliveryBusiness deliveryBusiness;

    private final DeliveryAdapter deliveryAdapter = DeliveryAdapter.INSTANCE;

    private final UpdateStatusOrderProducer updateStatusOrderProducer;

    private final ClientGateway clientGateway;

    @SqsListener("${queue.logistic.name}")
    public void createDelivery(final OrderDTO order) {
        Delivery delivery = deliveryBusiness.createDelivery(
                deliveryAdapter.toDomain(order)
        );

        delivery.setAddress(clientGateway.getClientAddress(order.getClientId()));

        updateStatusOrderProducer.publish(delivery.getOrderId(), delivery.getStatus());
    }

}
