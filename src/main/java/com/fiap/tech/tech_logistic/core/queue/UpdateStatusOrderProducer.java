package com.fiap.tech.tech_logistic.core.queue;

import com.fiap.tech.tech_logistic.domain.delivery.status.Status;

public interface UpdateStatusOrderProducer {

    void publish(Long orderId, Status status);

}
