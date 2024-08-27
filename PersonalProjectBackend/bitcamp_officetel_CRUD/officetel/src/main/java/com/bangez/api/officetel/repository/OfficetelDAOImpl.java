package com.bangez.api.officetel.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.bangez.api.officetel.model.OfficetelModel;
import com.bangez.api.officetel.model.OfficetelDTO;
import com.bangez.api.officetel.model.QOfficetelModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@Service
@RequiredArgsConstructor
public class OfficetelDAOImpl implements OfficetelDAO {

    private final JPAQueryFactory factory;
    private final QOfficetelModel officetel = QOfficetelModel.officetelModel;

    public List<OfficetelDTO> getOfficetelByUser(String user){
        log.info("입력받은 정보 : {}", user );

        log.info("리턴값 확인 {} ", factory.selectFrom(officetel)
                .orderBy(officetel.id.desc())
                .where(officetel.user.eq(user))
                .fetch()
                .stream()
                .map(OfficetelDAOImpl::entityToDto)
                .toList());

        return factory.selectFrom(officetel)
                .orderBy(officetel.id.desc())
                .where(officetel.user.eq(user))
                .fetch()
                .stream()
                .map(OfficetelDAOImpl::entityToDto)
                .toList();
    }
    public List<OfficetelDTO> getAllOfficetel(Pageable pageable) {
        return factory.selectFrom(officetel)
                .orderBy(officetel.id.desc())
                .offset((pageable.getPageNumber() - 1) + pageable.getPageSize())
                .limit(pageable.getPageSize())
                .fetch()
                .stream()
                .map(OfficetelDAOImpl::entityToDto)
                .toList();
    }


    public List<OfficetelDTO> getAllOfficetelNoPage() {
        return factory.selectFrom(officetel)
                .orderBy(officetel.id.desc())
                .fetch()
                .stream()
                .map(OfficetelDAOImpl::entityToDto)
                .toList();
    }

    public List<OfficetelDTO> getOfficetelById(Long id) {
        log.info("입력받은 정보 : {}", id );
        return factory.selectFrom(officetel)
                .where(officetel.id.eq(id))
                .fetch()
                .stream()
                .map(i -> entityToDto(i))
                .toList();
    }

    public List<OfficetelDTO> getOfficetelDynamic(OfficetelDTO dto, List<String> oTvalue, List<String> pTvalue, List<String> cost) {
        BooleanBuilder oTvalueBuilder = new BooleanBuilder();
        BooleanBuilder pTvalueBuilder = new BooleanBuilder();
        BooleanBuilder costBuilder = new BooleanBuilder();

        if (!oTvalue.isEmpty()) {
            for (String i : oTvalue) {
                oTvalueBuilder.or(officetel.ownerType.eq(Long.valueOf(i)));
            }
        }

        if (!pTvalue.isEmpty()) {
            for (String i : pTvalue) {
                pTvalueBuilder.or(officetel.propertyType.eq(Long.valueOf(i)));
            }
        }

        if (!cost.isEmpty()) {
            Long lowCost = Long.parseLong(cost.get(0))*10000;
            Long maxCost = Long.parseLong(cost.get(1))*10000;
            costBuilder.and(officetel.price.gt(lowCost));
            costBuilder.and(officetel.price.lt(maxCost));
        }

        BooleanBuilder finalBuilder = oTvalueBuilder.and(pTvalueBuilder).and(costBuilder);

        return factory.selectFrom(officetel)
                .where(finalBuilder)
                .orderBy(officetel.id.desc())
                .fetch()
                .stream()
                .map(OfficetelDAOImpl::entityToDto)
                .toList();
    }


    static OfficetelDTO entityToDto(OfficetelModel officetelModel) {
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
}
