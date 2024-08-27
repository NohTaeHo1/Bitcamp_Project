package com.bangez.api.officetel.service;

import com.bangez.api.common.model.MessengerVo;
import com.bangez.api.officetel.model.OfficetelModel;
import com.bangez.api.officetel.model.OfficetelDTO;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface OfficetelService {

    Long countOfficetel();
    List<OfficetelDTO> findAll();
    Optional<OfficetelDTO> findById(Long id);
    MessengerVo insert(OfficetelDTO dto);
    MessengerVo modify(OfficetelDTO dto);
    MessengerVo removebyId(Long id);

    default OfficetelDTO entityToDto(OfficetelModel officetelModel){
        return OfficetelDTO.builder()
                .id(officetelModel.getId())
                .ownerType(officetelModel.getOwnerType())
                .buildingName(officetelModel.getBuildingName())
                .addressId(officetelModel.getAddressId())
                .propertyType(officetelModel.getPropertyType())
                .price(officetelModel.getPrice())
                .area(officetelModel.getArea())
                .floor(officetelModel.getFloor())
                .direction(officetelModel.getDirection())
                .description(officetelModel.getDescription())
                .owner(officetelModel.getOwner())
                .listingDate(officetelModel.getListingDate())
                .monthlyRent(officetelModel.getMonthlyRent())
                .user(officetelModel.getUser())
                .build();
    }

    default OfficetelModel dtoToEntity(OfficetelDTO dto){
        LocalDate localdate = LocalDate.now();
        return OfficetelModel.builder()
                .id(dto.getId())
                .ownerType(dto.getOwnerType())
                .buildingName(dto.getBuildingName())
                .addressId(dto.getAddressId())
                .propertyType(dto.getPropertyType())
                .price(dto.getPrice())
                .area(dto.getArea())
                .floor(dto.getFloor())
                .direction(dto.getDirection())
                .description(dto.getDescription())
                .owner(dto.getOwner())
                .listingDate(localdate)
                .user(dto.getUser())
                .build();
    }



}
