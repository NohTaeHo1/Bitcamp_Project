package com.bangez.analysis.officetel_rent.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.stereotype.Component;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OfficetelRentDto {

    private String officetelRentId ;
    private String address;
    private String builtYear;
    private String contractDate;
    private String floor;
    private String leaseTerm;
    private String legalCode;
    private Long monthlyRent;
    private Float netLeasableArea;
    private String officetelName;
    private Long securityDeposit;
    private String ward;
}
