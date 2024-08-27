package com.bangez.analysis.city_park.service.serviceImpl;

import com.bangez.analysis.city_park.model.CityParkDto;
import com.bangez.analysis.city_park.repository.CityParkRepository;
import com.bangez.analysis.city_park.service.CityParkService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CityParkServiceImpl implements CityParkService {

    private final CityParkRepository repository;

    public Mono<List<CityParkDto>> findAll(){
        return repository.findAll().flatMap(this::documentToDto).collectList();
    }
}
