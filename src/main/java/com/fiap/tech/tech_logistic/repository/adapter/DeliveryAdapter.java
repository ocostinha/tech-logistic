package com.fiap.tech.tech_logistic.repository.adapter;

import com.fiap.tech.tech_logistic.domain.delivery.Delivery;
import com.fiap.tech.tech_logistic.repository.model.DeliveryEntity;
import org.mapstruct.AfterMapping;
import org.mapstruct.BeanMapping;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DeliveryAdapter {

    DeliveryAdapter INSTANCE = Mappers.getMapper(DeliveryAdapter.class);

    @BeanMapping(builder = @Builder(disableBuilder = true))
    DeliveryEntity toEntity(final Delivery domain);

    @BeanMapping(builder = @Builder(disableBuilder = true))
    Delivery fromEntity(final DeliveryEntity deliveryEntity);

    @AfterMapping
    default void afterMapping(@MappingTarget final DeliveryEntity deliveryEntity) {
        deliveryEntity.getItems().forEach(orderItem -> orderItem.setDelivery(deliveryEntity));
        deliveryEntity.getAddress().setDelivery(deliveryEntity);
    }

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    DeliveryEntity update(@MappingTarget DeliveryEntity persistedDelivery, Delivery delivery);

}
