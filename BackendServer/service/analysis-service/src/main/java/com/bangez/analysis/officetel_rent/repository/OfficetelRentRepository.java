package com.bangez.analysis.officetel_rent.repository;

import com.bangez.analysis.officetel_rent.model.OfficetelRent;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfficetelRentRepository extends ReactiveMongoRepository<OfficetelRent, String> {
}
