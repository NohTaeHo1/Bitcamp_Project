package com.bangez.api.officetel.model;

import com.querydsl.core.annotations.QueryProjection;
import lombok.*;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Component
public class OfficetelDTO {

    private Long id;
    private Long ownerType; // 중개사 1 개인 2
    private String buildingName;
    private Long addressId;
    private Long propertyType; // 매매 1 월세 2 전세 3
    private Long price; // 월세는 보증금으로 적용
    private Long monthlyRent; // 월세만 존재
    private Long area;
    private String floor;
    private String direction;
    private String description;
    private String owner;
    private LocalDate listingDate;
    private String user;


    @QueryProjection
    public OfficetelDTO(String buildingName, Long id, Long ownerType, Long addressId, Long propertyType, Long price, Long monthlyRent, Long area, String floor, String direction, String description, String owner, LocalDate listingDate, String user
) {
        this.buildingName = buildingName;
        this.id = id;
        this.ownerType = ownerType;
        this.addressId = addressId;
        this.propertyType = propertyType;
        this.price = price;
        this.monthlyRent = monthlyRent;
        this.area = area;
        this.floor = floor;
        this.direction = direction;
        this.description = description;
        this.owner = owner;
        this.listingDate = listingDate;
        this.user = user;
    }
}

