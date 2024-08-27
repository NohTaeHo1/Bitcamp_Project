package com.bangez.analysis.apt_trade.service.serviceImpl;

import com.bangez.analysis.apt_trade.model.AptTrade;
import com.bangez.analysis.apt_trade.model.AptTradeBoxPlot;
import com.bangez.analysis.apt_trade.repository.AptTradeRepository;
import com.bangez.analysis.apt_trade.service.AptTradeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class AptTradeServiceImpl implements AptTradeService {

    private final AptTradeRepository repository;

    public Mono<Map<String, AptTradeBoxPlot>> getPriceStatistics() {
        return repository.findAll()
                .collectList()
                .flatMap(properties -> {
                    Map<String, List<Float>> pricesByRegion = properties.stream()
                            .collect(Collectors.groupingBy(
                                    i -> i.getWard(),
                                    Collectors.mapping(i -> i.getPricePerArea(), Collectors.toList())
                            ));

                    Map<String, AptTradeBoxPlot> statsByRegion = pricesByRegion.entrySet().stream()
                            .collect(Collectors.toMap(
                                    Map.Entry::getKey,
                                    entry -> calculateStatistics(entry.getValue().stream()
                                            .map(Float::doubleValue)
                                            .collect(Collectors.toList()))
                            ));

                    return Mono.just(statsByRegion);
                });
    }

    private AptTradeBoxPlot calculateStatistics(List<Double> prices) {
        // 통계 계산 로직 구현
        AptTradeBoxPlot stats = new AptTradeBoxPlot();
        List<Double> sortedPrices = prices.stream().sorted().collect(Collectors.toList());

        stats.setMin(sortedPrices.get(0));
        stats.setMax(sortedPrices.get(sortedPrices.size() - 1));
        stats.setMedian(calculateMedian(sortedPrices));
        stats.setQ1(calculateQuartile(sortedPrices, 1));
        stats.setQ3(calculateQuartile(sortedPrices, 3));

        return stats;
    }

    private double calculateMedian(List<Double> prices) {
        int size = prices.size();
        if (size % 2 == 0) {
            return (prices.get(size / 2 - 1) + prices.get(size / 2)) / 2.0;
        } else {
            return prices.get(size / 2);
        }
    }

    private double calculateQuartile(List<Double> prices, int quartile) {
        int size = prices.size();
        int index = (quartile * (size + 1)) / 4 - 1;
        return prices.get(index);
    }

}
