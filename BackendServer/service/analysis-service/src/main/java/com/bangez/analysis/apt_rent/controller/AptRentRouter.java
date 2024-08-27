package com.bangez.analysis.apt_rent.controller;

import com.bangez.analysis.apt_rent.model.AptRentDto;
import com.bangez.analysis.apt_rent.repository.repositoryImpl.AptRentDAORepositoryImpl;
import com.bangez.analysis.apt_rent.service.serviceImpl.AptRentServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class AptRentRouter {

    private final AptRentDAORepositoryImpl repository;

    public Mono<?> execute(String select, String date, String region){

        return switch (select){
            case "1" -> repository.plotGraphAvgCostByDate();
            case "3" -> repository.plotGraphSalesCountByRegionForMonth(date, region);

            default -> null;
        };
    }
}
