package com.bangez.analysis.apt_rent.service.serviceImpl;

import com.bangez.analysis.apt_rent.model.AptRent;
import com.bangez.analysis.apt_rent.model.AptRentDto;
import com.bangez.analysis.apt_rent.repository.AptRentRepository;
import com.bangez.analysis.apt_rent.service.AptRentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class AptRentServiceImpl implements AptRentService {

    private final AptRentRepository repository;

}
