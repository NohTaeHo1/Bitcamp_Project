package com.bangez.api.officetel.repository;

import com.bangez.api.officetel.model.OfficetelDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfficetelDAO {

    List<OfficetelDTO> getAllOfficetel(Pageable pageable);

    List<OfficetelDTO> getOfficetelDynamic(OfficetelDTO dto, List<String> oTvalue, List<String> pTvalue, List<String> cost);
}
