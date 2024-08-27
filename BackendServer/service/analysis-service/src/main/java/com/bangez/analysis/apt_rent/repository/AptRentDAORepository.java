package com.bangez.analysis.apt_rent.repository;

import reactor.core.publisher.Mono;

import java.util.Map;

public interface AptRentDAORepository {

    Mono<Map<String, Long>> plotGraphAvgCostByDate();

    Mono<Map<String,Long>> plotGraphSalesCountByRegionForMonth(String date, String region);

}
