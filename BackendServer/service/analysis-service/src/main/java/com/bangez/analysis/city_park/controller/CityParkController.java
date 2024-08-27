package com.bangez.analysis.city_park.controller;

import com.bangez.analysis.city_park.model.CityParkDto;
import com.bangez.analysis.city_park.service.serviceImpl.CityParkServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Log4j2
//@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/city_park")
public class CityParkController {

    private final CityParkServiceImpl service;

    @GetMapping(path = "/statistics")
    public Mono<List<CityParkDto>> findall(){
        return service.findAll();
    }
}
