package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.SignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/signup")
public class SignUpController {

    @Autowired
    SignUpService signUpService;

    public SignUpController(SignUpService signUpService) {
        this.signUpService = signUpService;
    }

    @GetMapping
    public String signup() {
        System.out.println("Inside get mapping");
        return "signup";
    }

    @PostMapping
    public String signUpUser(@ModelAttribute User user, Model model) {
        String signuperror = null;

        if (!signUpService.isUserNameAvailable(user.getUsername())) {
            signuperror = "Username already exists";
            model.addAttribute("signupError", signuperror);

        }
        if (signuperror == null) {
            int rowsAdded = signUpService.createUser(user);
            if (rowsAdded < 0) {
                System.out.println("Inside signup user creation");
                signuperror = "There was an error signing you up. Please try again.";
            }

            model.addAttribute("signupsuccess", true);
        }


        return "signup";
    }

}
