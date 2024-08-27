package com.bangez.analysis.officetel_trade.controller;

import com.bangez.analysis.officetel_rent.service.serviceImpl.OfficetelRentServiceImpl;
import com.bangez.analysis.officetel_trade.repository.repositoryImpl.OfficetelTradeDAORepositoryImpl;
import com.bangez.analysis.officetel_trade.service.serviceImpl.OfficetelTradeServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class OfficetelTradeRouter {
    private final OfficetelTradeDAORepositoryImpl repository;

    public Mono<?> execute(String select, String date, String region){

        return switch (select){
            case "1" -> repository.plotGraphAvgCostByDate();
            case "3" -> repository.plotGraphSalesCountByRegionForMonth(date, region);

            default -> null;
        };
    }
}
