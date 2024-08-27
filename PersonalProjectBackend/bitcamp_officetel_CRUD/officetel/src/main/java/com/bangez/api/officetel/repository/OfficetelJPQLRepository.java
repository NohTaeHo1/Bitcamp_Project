package com.bangez.api.officetel.repository;

import com.bangez.api.officetel.model.OfficetelModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface OfficetelJPQLRepository extends JpaRepository<OfficetelModel, Long> {

    @Transactional
    @Modifying
    @Query(value="insert into officetels (owner_type, building_name, address_id, property_type, price, area, floor, direction, description, owner, listing_date, user) values " +
            "(:#{#entity.ownerType}, :#{#entity.buildingName}, :#{#entity.addressId}, :#{#entity.propertyType}, :#{#entity.price}, :#{#entity.area}, :#{#entity.floor}, :#{#entity.direction}, :#{#entity.description}, :#{#entity.owner}, :#{#entity.listingDate}, :#{#entity.user})",
            nativeQuery = true)
    void insert(@Param("entity") OfficetelModel entity);

    @Transactional
    @Modifying
    @Query("update officetels o set o.ownerType = :#{#entity.ownerType}, o.buildingName = :#{#entity.buildingName}, o.addressId = :#{#entity.addressId}, o.propertyType = :#{#entity.propertyType}, " +
            "o.price = :#{#entity.price}, o.area = :#{#entity.area}, o.floor = :#{#entity.floor}, o.direction = :#{#entity.direction}, o.description = :#{#entity.description}, " +
            "o.owner = :#{#entity.owner}, o.listingDate = :#{#entity.listingDate} where o.id = :#{#entity.id}")
    void modify(@Param("entity") OfficetelModel entity);

    @Transactional
    @Modifying
    @Query("delete from officetels o where o.id = :id")
    void removebyId(@Param("id") Long id);

}
