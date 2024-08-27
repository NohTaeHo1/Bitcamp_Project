package com.bangez.analysis.officetel_trade.repository;

import com.bangez.analysis.officetel_trade.model.OfficetelTrade;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfficetelTradeRepository extends ReactiveMongoRepository<OfficetelTrade, String> {
}
