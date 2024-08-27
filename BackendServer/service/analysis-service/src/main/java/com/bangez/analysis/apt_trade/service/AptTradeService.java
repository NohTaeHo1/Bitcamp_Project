package com.bangez.analysis.apt_trade.service;

import com.bangez.analysis.apt_trade.model.AptTrade;
import com.bangez.analysis.apt_trade.model.AptTradeBoxPlot;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

public interface AptTradeService{

    Mono<Map<String, AptTradeBoxPlot>> getPriceStatistics();

}
