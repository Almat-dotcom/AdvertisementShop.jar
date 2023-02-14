package com.advertisementShop.AdvertisementShop.services;

import com.advertisementShop.AdvertisementShop.models.Advertisement;
import com.advertisementShop.AdvertisementShop.models.Users;

import java.util.List;
import java.util.Optional;

public interface AdvertisementService {
    List<Advertisement> getAllAdvertisements();

    List<Advertisement> getAll(Users users);

    Advertisement addAdvertisement(Advertisement advertisement);

    Advertisement saveAdvertisement(Advertisement advertisement);

    Optional<Advertisement> getAdvertisement(Long id);

    Advertisement getAdvertisementByName(String name);

    void deleteAdvertisement(Advertisement advertisement);


}
