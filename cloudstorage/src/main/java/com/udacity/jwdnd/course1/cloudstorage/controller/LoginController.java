package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.AuthenticationService;
import com.udacity.jwdnd.course1.cloudstorage.services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {



    @GetMapping
    public String login()
    {

      //  System.out.println("inside login controller");
        return "login";
    }

//    @PostMapping
//    public String loginPostCall(@ModelAttribute User user, Model model){
//        String username= user.getUsername();
//        String password= user.getPassword();
//        if(password.equals(LoginService.getPassword()))
//        {
//            return "home";
//        }
//        else
//        {
//            model.addAttribute("loginfailure",true);
//            return "login";}
//
//    }


}
