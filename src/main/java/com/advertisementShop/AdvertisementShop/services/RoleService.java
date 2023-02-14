package com.advertisementShop.AdvertisementShop.services;

import com.advertisementShop.AdvertisementShop.models.Roles;

import java.util.List;

public interface RoleService {
    List<Roles> getAllRoles();
    Roles addRole(Roles roles);
    Roles saveRole(Roles roles);
    Roles getRole(Long id);
    Roles getRoleByName(String name);
    void deleteRole(Roles role);
}
