package com.advertisementShop.AdvertisementShop.repositories;

import com.advertisementShop.AdvertisementShop.models.Advertisement;
import com.advertisementShop.AdvertisementShop.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface AdvertisementRepository extends JpaRepository<Advertisement,Long> {
    Advertisement findByName(String name);

    List<Advertisement> getAdvertisementsByStartedTrue();

    List<Advertisement> getAdvertisementsByCreatedByIsNotContaining(String email);
}
