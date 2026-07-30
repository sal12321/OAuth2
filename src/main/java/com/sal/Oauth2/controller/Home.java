package com.sal.Oauth2.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;




@Controller
@RequestMapping("/")
public class Home {

    @GetMapping("/")
    public String goHome(@AuthenticationPrincipal OAuth2User user, Model model, Authentication authentication){





        user.getAttributes().forEach((k,v) ->
                System.out.println(k + " : " + v));


        OAuth2AuthenticationToken token = (OAuth2AuthenticationToken) authentication;

        String provider = token.getAuthorizedClientRegistrationId().toUpperCase();
        String email = user.getAttribute("email");
        if(email == null){
            email = "Not Provided !";
        }

        model.addAttribute("provider", provider);
        model.addAttribute("name", user.getAttribute("name"));
            model.addAttribute("email", email);
        model.addAttribute("picture", user.getAttribute("picture"));


        return "Home" ;
    }

}
