package com.advertisementShop.AdvertisementShop.serviceImpl;

import com.advertisementShop.AdvertisementShop.models.Roles;
import com.advertisementShop.AdvertisementShop.models.Users;
import com.advertisementShop.AdvertisementShop.repositories.RolesRepository;
import com.advertisementShop.AdvertisementShop.repositories.UserRepository;
import com.advertisementShop.AdvertisementShop.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@EnableWebSecurity
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RolesRepository rolesRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        Users myUser = userRepository.findByEmail(s);

        if (myUser != null) {
            return new User(myUser.getEmail(), myUser.getPassword(), myUser.getRoles());
        } else {
            throw new UsernameNotFoundException("User Name Not Found");
        }
    }

    @Override
    public Users registerUser(Users user) {
        Users checkUser = userRepository.findByEmail(user.getEmail());
        if (checkUser == null) {
            Roles role = rolesRepository.findByName("ROLE_USER");
            ArrayList<Roles> roles = new ArrayList<>();
            roles.add(role);
            user.setRoles(roles);
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            return userRepository.save(user);
        }
        return null;
    }

    @Override
    public Users saveUser(Users user) {
        return userRepository.save(user);
    }

    @Override
    public Users updatePassword(String oldPassword, String newPassword, Users user) {
        if (passwordEncoder.matches(oldPassword, user.getPassword())) {
            user.setPassword(passwordEncoder.encode(newPassword));
            return userRepository.save(user);

        }
        return null;
    }

    @Override
    public Users getUserByEmail(String email) {
        return userRepository.findByEmail(email);


    }

    @Override
    public List<Users> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void deleteUser(Users user) {
        userRepository.delete(user);
    }
}
