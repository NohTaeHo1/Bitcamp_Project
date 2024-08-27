package com.bangez.analysis.officetel_rent.controller;

import com.bangez.analysis.officetel_rent.repository.repositoryImpl.OfficetelRentDAORepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class OfficetelRentRouter {
    private final OfficetelRentDAORepositoryImpl repository;

    public Mono<?> execute(String select, String date, String region){

        return switch (select){
            case "1" -> repository.plotGraphAvgCostByDate();
            case "3" -> repository.plotGraphSalesCountByRegionForMonth(date, region);
            default -> null;
        };
    }
}
