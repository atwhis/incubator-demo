package com.ymchen.incubatordemo.controller;

import com.ymchen.incubatordemo.common.model.UserProfile;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/")
public class OauthController {

    @RequestMapping("/v1/profile")
    public Object profile() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email = user.getUsername() + "@hotmail.com";
        UserProfile profile = new UserProfile();
        profile.setName(user.getUsername());
        profile.setEmail(email);
        return profile;
    }

    @RequestMapping("hello")
    public Object hello() {
        return "hello";
    }
}
