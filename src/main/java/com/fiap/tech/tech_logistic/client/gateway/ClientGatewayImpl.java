package com.fiap.tech.tech_logistic.client.gateway;

import com.fiap.tech.tech_logistic.client.adapter.ClientAdapter;
import com.fiap.tech.tech_logistic.client.dto.ClientDTO;
import com.fiap.tech.tech_logistic.core.gateway.ClientGateway;
import com.fiap.tech.tech_logistic.domain.delivery.Delivery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
@RequiredArgsConstructor
public class ClientGatewayImpl implements ClientGateway {

    private final RestClient restClient;

    private final ClientAdapter clientAdapter = ClientAdapter.INSTANCE;

    @Override
    public Delivery.Address getClientAddress(Long clientId) {
        return clientAdapter.toDomain(
                restClient
                        .get()
                        .uri("/" + clientId)
                        .retrieve()
                        .toEntity(ClientDTO.class)
                        .getBody()
        );
    }

}
