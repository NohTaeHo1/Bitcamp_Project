package com.bangez.analysis.apt_trade.repository;

import reactor.core.publisher.Mono;

import java.util.Map;

public interface AptTradeDAORepositoty {

    Mono<Map<String, Long>> plotGraphAvgCostByDate();


    Mono<Map<String,Long>> plotGraphSalesCountByRegionForMonth(String date, String region);


    Mono<Map<String,Long>> tradeCountRaiseTop5ForMonth();

}
