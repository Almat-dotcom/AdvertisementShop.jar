package com.advertisementShop.AdvertisementShop.serviceImpl;

import com.advertisementShop.AdvertisementShop.models.Roles;
import com.advertisementShop.AdvertisementShop.repositories.RolesRepository;
import com.advertisementShop.AdvertisementShop.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RolesRepository rolesRepository;

    @Override
    public List<Roles> getAllRoles() {
        return rolesRepository.findAll();
    }

    @Override
    public Roles addRole(Roles roles) {
        return rolesRepository.save(roles);
    }

    @Override
    public Roles saveRole(Roles roles) {
        return rolesRepository.save(roles);
    }

    @Override
    public Roles getRole(Long id) {
        return rolesRepository.getOne(id);
    }

    @Override
    public Roles getRoleByName(String name) {
        return rolesRepository.findByName(name);
    }

    @Override
    public void deleteRole(Roles role) {
        rolesRepository.delete(role);
    }
}
