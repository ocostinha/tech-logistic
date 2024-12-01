package com.fiap.tech.tech_logistic.client.adapter;

import com.fiap.tech.tech_logistic.client.dto.ClientDTO;
import com.fiap.tech.tech_logistic.domain.delivery.Delivery;
import org.mapstruct.Mapper;

@Mapper
public interface ClientAdapter {

    ClientAdapter INSTANCE = org.mapstruct.factory.Mappers.getMapper(ClientAdapter.class);

    Delivery.Address toDomain(ClientDTO clientDTO);

}
