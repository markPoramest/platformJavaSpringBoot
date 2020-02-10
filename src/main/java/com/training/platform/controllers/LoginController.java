package com.training.platform.controllers;

import com.training.platform.entities.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class LoginController {
    @GetMapping("/login")
    public String index(User user) throws Exception {
        return "login/layout";
    }
    @GetMapping("/access-denied")
    public String accessDenied() throws Exception {
        return "login/access-denied";
    }
}

