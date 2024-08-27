package com.bangez.analysis.apt_trade.controller;

import com.bangez.analysis.apt_rent.service.serviceImpl.AptRentServiceImpl;
import com.bangez.analysis.apt_trade.repository.repositoryImpl.AptTradeDAORepositoryImpl;
import com.bangez.analysis.apt_trade.service.serviceImpl.AptTradeServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class AptTradeRouter {
    private final AptTradeDAORepositoryImpl repository;

    public Mono<?> execute(String select, String date, String region){

        return switch (select){
            case "1" -> repository.plotGraphAvgCostByDate();
            case "3" -> repository.plotGraphSalesCountByRegionForMonth(date, region);
            case "7" -> repository.tradeCountRaiseTop5ForMonth();

            default -> null;
        };
    }
}
