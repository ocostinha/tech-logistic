package com.fiap.tech.tech_logistic.core.gateway;

import com.fiap.tech.tech_logistic.domain.delivery.Delivery;

public interface ClientGateway {

    Delivery.Address getClientAddress(Long clientId);

}
