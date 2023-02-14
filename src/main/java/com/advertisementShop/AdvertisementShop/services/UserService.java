package com.advertisementShop.AdvertisementShop.services;

import com.advertisementShop.AdvertisementShop.models.Users;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    Users getUserByEmail(String email);
    Users registerUser(Users user);
    Users saveUser(Users user);
    Users updatePassword(String oldPassword, String newPassword, Users user);
    List<Users> getAllUsers();
    void deleteUser(Users user);
}
