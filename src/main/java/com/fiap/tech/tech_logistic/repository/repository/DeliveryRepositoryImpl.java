package com.fiap.tech.tech_logistic.repository.repository;

import com.fiap.tech.tech_logistic.core.repository.DeliveryRepository;
import com.fiap.tech.tech_logistic.domain.delivery.Delivery;
import com.fiap.tech.tech_logistic.repository.adapter.DeliveryAdapter;
import com.fiap.tech.tech_logistic.repository.jpa.DeliveryJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class DeliveryRepositoryImpl implements DeliveryRepository {

    private final DeliveryJpaRepository deliveryJpaRepository;

    private final DeliveryAdapter deliveryAdapter = DeliveryAdapter.INSTANCE;

    @Override
    public Delivery save(final Delivery order) {
        return deliveryAdapter.fromEntity(
                deliveryJpaRepository.save(
                        deliveryAdapter.toEntity(
                                order
                        )
                )
        );
    }

    @Override
    public Optional<Delivery> findById(final Long id) {
        return Optional.ofNullable(
                deliveryAdapter.fromEntity(
                        deliveryJpaRepository.findById(id).orElse(null)
                )
        );
    }

    @Override
    public Set<Delivery> findAll() {
        return deliveryJpaRepository.findAll().stream()
                .map(deliveryAdapter::fromEntity)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Delivery> findWaitingDeliveries() {
        return deliveryJpaRepository.findByDriverIdIsNull().stream()
                .map(deliveryAdapter::fromEntity)
                .collect(Collectors.toSet());
    }

}
