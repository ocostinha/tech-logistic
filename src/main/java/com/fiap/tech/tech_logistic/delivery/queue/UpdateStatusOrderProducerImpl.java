package com.fiap.tech.tech_logistic.delivery.queue;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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

    private final ObjectMapper objectMapper;

    @Value("${queue.pedido.update.status.name}")
    private String queueUrl;

    @Override
    public void publish(Long orderId, Status status) {
        try {
            sqsTemplate.send(
                    queueUrl, objectMapper.writeValueAsString(
                            new OrderStatusUpdateDTO(
                                    orderId, status.name()
                            )
                    )
            );
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
