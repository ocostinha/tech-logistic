package com.fiap.tech.tech_logistic.delivery.adapter;

import com.fiap.tech.tech_logistic.delivery.dto.DeliveryDTO;
import com.fiap.tech.tech_logistic.delivery.dto.OrderDTO;
import com.fiap.tech.tech_logistic.domain.delivery.Delivery;
import com.fiap.tech.tech_logistic.domain.delivery.status.Status;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(imports = {Status.class})
public interface DeliveryAdapter {

    DeliveryAdapter INSTANCE = Mappers.getMapper(DeliveryAdapter.class);

    @Mapping(target = "orderId", source = "id")
    @Mapping(target = "status", expression = "java(Status.WAITING_DRIVER)")
    Delivery toDomain(OrderDTO dto);

    DeliveryDTO fromDomain(Delivery domain);

}
