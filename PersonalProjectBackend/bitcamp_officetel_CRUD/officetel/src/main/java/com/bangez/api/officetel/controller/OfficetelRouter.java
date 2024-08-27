package com.bangez.api.officetel.controller;

import com.bangez.api.officetel.model.OfficetelDTO;
import com.bangez.api.officetel.repository.OfficetelDAOImpl;
import com.bangez.api.officetel.repository.OfficetelRepository;
import com.bangez.api.officetel.service.OfficetelService;
import com.bangez.api.officetel.service.OfficetelServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class OfficetelRouter {

    private final OfficetelDAOImpl service;


    public List<?> execute(String type, OfficetelDTO dto, List<String> oTvalue, List<String> pTvalue, List<String> cost, Pageable pageable, Long id, String user) {

        return switch (type){
            case "1" -> service.getAllOfficetel(pageable);
            case "2" -> service.getOfficetelDynamic(dto, oTvalue, pTvalue, cost);
            case "3" -> service.getAllOfficetelNoPage();
            case "4" -> service.getOfficetelById(id);
            case "5" -> service.getOfficetelByUser(user);
            default -> null;
        };
    }
}
