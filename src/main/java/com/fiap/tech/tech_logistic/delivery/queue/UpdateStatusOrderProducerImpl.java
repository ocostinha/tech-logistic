package com.fiap.tech.tech_logistic.delivery.queue;

import com.fiap.tech.tech_logistic.core.queue.UpdateStatusOrderProducer;
import com.fiap.tech.tech_logistic.delivery.dto.OrderStatusUpdateDTO;
import com.fiap.tech.tech_logistic.domain.delivery.status.Status;
import io.awspring.cloud.sqs.operations.SqsTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UpdateStatusOrderProducerImpl implements UpdateStatusOrderProducer {

    private final SqsTemplate sqsTemplate;

    @Value("${queue.pedido.update.status.name}")
    private String queueUrl;

    @Override
    public void publish(Long orderId, Status status) {
        sqsTemplate.send(
                queueUrl, new OrderStatusUpdateDTO(
                        orderId, status.name()
                )
        );
    }

}
