package com.bangez.analysis.school.service.serviceImpl;

import com.bangez.analysis.apt_rent.repository.AptRentRepository;
import com.bangez.analysis.apt_trade.repository.AptTradeRepository;
import com.bangez.analysis.city_park.repository.CityParkRepository;
import com.bangez.analysis.officetel_rent.repository.OfficetelRentRepository;
import com.bangez.analysis.officetel_trade.repository.OfficetelTradeRepository;
import com.bangez.analysis.school.model.School;
import com.bangez.analysis.school.model.SchoolDto;
import com.bangez.analysis.school.repository.SchoolRepository;
import com.bangez.analysis.school.service.SchoolService;
import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBeanBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.FileReader;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class SchoolServiceImpl implements SchoolService {

    private final SchoolRepository repository;

    public Mono<List<SchoolDto>> findAll(){
        return repository.findAll().flatMap(i-> documentToDto(i)).collectList();
    }

}
