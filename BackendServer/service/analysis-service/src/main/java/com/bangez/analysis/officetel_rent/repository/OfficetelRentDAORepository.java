package com.bangez.analysis.officetel_rent.repository;

import reactor.core.publisher.Mono;

import java.util.Map;

public interface OfficetelRentDAORepository {

    Mono<Map<String, Long>> plotGraphAvgCostByDate();

    Mono<Map<String,Long>> plotGraphSalesCountByRegionForMonth(String date, String region);

}
