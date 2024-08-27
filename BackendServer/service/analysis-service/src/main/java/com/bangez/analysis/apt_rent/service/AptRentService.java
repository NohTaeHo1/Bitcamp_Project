package com.bangez.analysis.apt_rent.service;

import com.bangez.analysis.apt_rent.model.AptRent;
import com.bangez.analysis.apt_rent.model.AptRentDto;
import reactor.core.publisher.Mono;

import java.util.Map;

public interface AptRentService {


    default Mono<AptRentDto> documentToDto(AptRent aptRent) {
        AptRentDto dto = AptRentDto.builder()
                .address(aptRent.getAddress())
                .aptName(aptRent.getAptName())
                .builtYear(aptRent.getBuiltYear())
                .contractDate(aptRent.getContractDate())
                .floor(aptRent.getFloor())
                .leaseTerm(aptRent.getLeaseTerm())
                .legalCode(aptRent.getLegalCode())
                .monthlyRent(aptRent.getMonthlyRent())
                .netLeasableArea(aptRent.getNetLeasableArea())
                .securityDeposit(aptRent.getSecurityDeposit())
                .ward(aptRent.getWard())
                .build();
        return Mono.just(dto);
    }

    default AptRent dtoToDocument(AptRentDto dto) {

        AptRent aptRent = AptRent.builder()
                .address(dto.getAddress())
                .aptName(dto.getAptName())
                .builtYear(dto.getBuiltYear())
                .contractDate(dto.getContractDate())
                .floor(dto.getFloor())
                .leaseTerm(dto.getLeaseTerm())
                .legalCode(dto.getLegalCode())
                .monthlyRent(dto.getMonthlyRent())
                .netLeasableArea(dto.getNetLeasableArea())
                .securityDeposit(dto.getSecurityDeposit())
                .ward(dto.getWard())
                .build();
        return aptRent;
    }
}
