package com.advertisementShop.AdvertisementShop.serviceImpl;

import com.advertisementShop.AdvertisementShop.models.Advertisement;
import com.advertisementShop.AdvertisementShop.models.Users;
import com.advertisementShop.AdvertisementShop.repositories.AdvertisementRepository;
import com.advertisementShop.AdvertisementShop.services.AdvertisementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdvertisementServiceImpl implements AdvertisementService {
    @Autowired
    private AdvertisementRepository advertisementRepository;

    @Override
    public List<Advertisement> getAllAdvertisements() {
        return advertisementRepository.findAll();
    }

    @Override
    public List<Advertisement> getAll(Users users) {
        return advertisementRepository.getAdvertisementsByCreatedByIsNotContaining(users.getEmail());
    }

    @Override
    public Advertisement addAdvertisement(Advertisement advertisement) {
        return advertisementRepository.save(advertisement);
    }

    @Override
    public Advertisement saveAdvertisement(Advertisement advertisement) {
        return advertisementRepository.save(advertisement);
    }

    @Override
    public Optional<Advertisement> getAdvertisement(Long id) {
        return advertisementRepository.findById(id);
    }

    @Override
    public Advertisement getAdvertisementByName(String name) {
        return advertisementRepository.findByName(name);
    }

    @Override
    public void deleteAdvertisement(Advertisement advertisement) {
        advertisementRepository.delete(advertisement);
    }
}
