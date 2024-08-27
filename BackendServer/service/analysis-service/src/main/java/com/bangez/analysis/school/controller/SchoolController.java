package com.bangez.analysis.school.controller;

import com.bangez.analysis.school.model.SchoolDto;
import com.bangez.analysis.school.service.serviceImpl.SchoolServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

@Log4j2
//@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/school")
public class SchoolController {
    private final SchoolServiceImpl service;

    @GetMapping("/statistics")
    public Mono<List<SchoolDto>> findAll(){
        return service.findAll();
    }
}