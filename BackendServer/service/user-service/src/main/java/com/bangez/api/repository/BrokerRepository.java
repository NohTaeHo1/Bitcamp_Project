package com.bangez.api.repository;

import com.bangez.api.domain.model.Broker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BrokerRepository extends JpaRepository<Broker, Long> {
}
