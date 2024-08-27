package com.bangez.analysis.officetel_rent.controller;

import com.bangez.analysis.apt_trade.controller.AptTradeRouter;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.Map;

@Log4j2
//@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/off_rent")
public class OfficetelRentController {
    private final OfficetelRentRouter router;

    @GetMapping(path = "/statistics")
    public ResponseEntity<?> searchPlayer(
            @RequestParam(value = "select", required = true) String select,
            @RequestParam(value = "date", required = false) String date,
            @RequestParam(value = "region", required = false) String region
    ) {
        Mono<?> monoMap = router.execute(select, date, region);

        return ResponseEntity.ok(monoMap);
    }


}
