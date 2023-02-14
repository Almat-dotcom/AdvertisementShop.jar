package com.advertisementShop.AdvertisementShop.repositories;

import com.advertisementShop.AdvertisementShop.models.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface RolesRepository extends JpaRepository<Roles,Long> {

    Roles findByName(String name);

}
