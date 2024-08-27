package com.bangez.analysis.apt_trade.controller;

import com.bangez.analysis.apt_rent.controller.AptRentRouter;
import com.bangez.analysis.apt_trade.model.AptTradeBoxPlot;
import com.bangez.analysis.apt_trade.service.AptTradeService;
import com.bangez.analysis.apt_trade.service.serviceImpl.AptTradeServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

@Log4j2
//@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/apt_trade")
public class AptTradeController {

    private final AptTradeRouter router;
    private final AptTradeServiceImpl service;

    @GetMapping(path = "/statistics")
    public ResponseEntity<?> searchPlayer(
            @RequestParam(value = "select", required = true) String select,
            @RequestParam(value = "date", required = false) String date,
            @RequestParam(value = "region", required = false) String region
    ) {
        Mono<?> monoMap = router.execute(select, date, region);

        return ResponseEntity.ok(monoMap);
    }

    @GetMapping("/boxplot")
    public Mono<Map<String, AptTradeBoxPlot>> getPriceStatistics() {
        return service.getPriceStatistics();
    }

}
