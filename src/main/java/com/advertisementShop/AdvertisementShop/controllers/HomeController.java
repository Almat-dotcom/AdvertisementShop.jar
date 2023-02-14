package com.advertisementShop.AdvertisementShop.controllers;

import com.advertisementShop.AdvertisementShop.models.Advertisement;
import com.advertisementShop.AdvertisementShop.models.Users;
import com.advertisementShop.AdvertisementShop.services.AdvertisementService;
import com.advertisementShop.AdvertisementShop.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
public class HomeController {

    @Autowired
    private UserService userService;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @Autowired
    AdvertisementService advertisementService;

    @GetMapping(value = "/ala")
    @PreAuthorize("isAuthenticated()")
    public String index(){
        return "Hello MC";
    }

    @GetMapping(value = "/")
    @PreAuthorize("isAuthenticated()")
    public String index(Model model, HttpServletRequest request) {
        List<Advertisement> ads=advertisementService.getAll(getUserData());
        model.addAttribute("advertisements",ads);
        model.addAttribute("currentUser", getUserData());
        return "welcome";
//        model.addAttribute("currentUser",getUserData());
//        return "login";
    }

    @GetMapping(value = "/register")
    public String register(Model model){
        model.addAttribute("currentUser",getUserData());
        return "register";
    }

    @GetMapping(value = "/login")
    public String login(Model model) {
        model.addAttribute("currentUser", getUserData());
        return "login";
    }

    @PostMapping(value = "/register")
    public String toRegister(@RequestParam(name = "user_email") String email,
                             @RequestParam(name = "user_password") String password,
                             @RequestParam(name = "re_user_password") String re_password,
                             @RequestParam(name = "full_name") String fullName) {
        if (password.equals(re_password)) {
            Users newUser = userService.registerUser(new Users(null,email, password, fullName, null, null));
            if (newUser != null) {
                return "redirect:/register?success";
            }
        }
        return "redirect:/register?error";
    }

    @PostMapping(value = "/addAdvertisement")
    public String addAdvertisement(@RequestParam(name = "post_name") String name,
                                   @RequestParam(name="post_description" ) String description,
                                   @RequestParam(name="post_picture",defaultValue = "https://storage.yandexcloud.net/mobiltelefon/february21/05/nothing_naznachila_anons_na_sleduuschuu_nedelu_i_otozvala_ego_picture2_0.jpg") String postPicture,
                                   @RequestParam(name="post_price") Long postPrice){
        Advertisement ad=new Advertisement();
        ad.setName(name);
        ad.setPrice(postPrice);
        ad.setDescription(description);
        ad.setPicUrl(postPicture);
        ad.setIsActive(true);
        advertisementService.saveAdvertisement(ad);

        return "redirect:/";
    }

    @GetMapping(value="/buyAd/{id}")
    public String buyAd(Model model,
                        @PathVariable(name = "id") Long id){
        Optional<Advertisement> ads=advertisementService.getAdvertisement(id);
        ads.get().setStarted(true);
        if(ads.get().getTempPrice()==null)
            ads.get().setTempPrice(ads.get().getPrice());
        ads.get().setTempPrice(ads.get().getTempPrice()+500);
        ads.get().setTimeStarted(new Date());
        ads.get().setFutureOwner(getUserData());
        advertisementService.saveAdvertisement(ads.get());
        return "redirect:/";
    }

    private Users getUserData() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            User secUser = (User) authentication.getPrincipal();
            Users myUser = userService.getUserByEmail(secUser.getUsername());
            return myUser;
        }
        return null;
    }
}
