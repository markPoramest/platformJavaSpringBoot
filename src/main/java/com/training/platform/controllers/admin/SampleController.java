package com.training.platform.controllers.admin;

import com.training.platform.entities.User;
import com.training.platform.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequestMapping("/admin/sample")
public class SampleController {
    @Autowired
    UserService userService;
    @GetMapping(value = "")
    public String index(Model model) {
        Page<User> users = userService.findAllByLimit(0, 8, "city");
        model.addAttribute("items", users);
        return "sample/firstlayout";
    }
    @GetMapping(value = "/mytheme")
    public String mytheme(Model model) {
        Page<User> users = userService.findAllByLimit(0, 8, "city");
        model.addAttribute("items", users);
        return "sample/lists";
    }
}
