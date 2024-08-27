package com.bangez.analysis.acess_log.controller;

import com.bangez.analysis.acess_log.service.serviceImpl.AccessLogServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.Map;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/api/today")
public class AccessLogController {

    private final AccessLogServiceImpl service;

    @PostMapping("/access-record")
    public Mono<Void> recordPageVisit() {
        return service.recordPageVisit();
    }

    @GetMapping("/access-count")
    public Mono<Map<String, Long>> getTodayAccessCount() {
        return service.getTodayAccessCount();
    }}


