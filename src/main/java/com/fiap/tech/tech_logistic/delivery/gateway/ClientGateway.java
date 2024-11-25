package com.fiap.tech.tech_logistic.delivery.gateway;

import com.fiap.tech.tech_logistic.domain.delivery.Delivery;
import org.springframework.stereotype.Component;

@Component
public class ClientGateway {

    public Delivery.Address getClientAddress(Long clientId) {
        return null;
    }

}
