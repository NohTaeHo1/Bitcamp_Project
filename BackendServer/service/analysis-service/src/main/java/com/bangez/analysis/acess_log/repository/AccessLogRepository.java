package com.bangez.analysis.acess_log.repository;

import com.bangez.analysis.acess_log.model.AccessLog;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

import java.time.LocalDateTime;

public interface AccessLogRepository extends ReactiveMongoRepository<AccessLog, String> {
    Flux<AccessLog> findByAccessTimeAfter(LocalDateTime dateTime);
}
