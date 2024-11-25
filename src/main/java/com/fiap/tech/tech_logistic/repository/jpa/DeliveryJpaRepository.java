package com.fiap.tech.tech_logistic.repository.jpa;

import com.fiap.tech.tech_logistic.repository.model.DeliveryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeliveryJpaRepository extends JpaRepository<DeliveryEntity, Long> {

    List<DeliveryEntity> findByDriverIdIsNull();

}
