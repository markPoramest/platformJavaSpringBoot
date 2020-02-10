package com.training.platform.controllers.api;

import com.training.platform.entities.User;
import com.training.platform.entities.es.Company;
import com.training.platform.services.UserService;
import com.training.platform.services.es.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
@RestController("api-user")
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping(value = "")
    public List<User> index() throws Exception {
        return userService.findAll();
    }
}