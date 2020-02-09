package com.training.platform.controllers.admin;

import com.training.platform.entities.Pager;
import com.training.platform.entities.User;
import com.training.platform.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/admin/user")
public class UserController {

    private static final int BUTTONS_TO_SHOW = 5;
    private static final int INITIAL_PAGE = 0;
    private static final int INITIAL_PAGE_SIZE = 10;
    private static final int[] PAGE_SIZES = {5, 10, 20};
    @Autowired
    private UserService userService;

    @GetMapping(value = "")
    public String index(
            Model model,
            @RequestParam("pageSize") Optional<Integer> pageSize,
            @RequestParam("page") Optional<Integer> page) throws Exception {
        int evalPageSize = pageSize.orElse(INITIAL_PAGE_SIZE);
        int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;
        //Page<User> users = userService.findAll(PageRequest.of(evalPage, evalPageSize));
        //Page<User> users = userService.findAll(PageRequest.of(evalPage, evalPageSize, Sort.by(Sort.Direction.DESC, "id")));
        Page<User> users = userService.findAll(PageRequest.of(evalPage, evalPageSize));
        Pager pager = new Pager(users.getTotalPages(), users.getNumber(), BUTTONS_TO_SHOW);
        model.addAttribute("items", users);
        model.addAttribute("selectedPageSize", evalPageSize);
        model.addAttribute("pageSizes", PAGE_SIZES);
        model.addAttribute("pager", pager);
        return "admin/user/lists";

    }
    @GetMapping(value="/create")
    public String create(Model model, User user) {
        model.addAttribute("cities", userService.getCities());
        model.addAttribute("user", user);
        return "admin/user/create";
    }
    @PostMapping(value="")
    public String store(@Valid User user,
                        BindingResult bindingResult,
                        @RequestParam Map<String,String> inputs,
                        RedirectAttributes redirAttrs,
                        Model model) throws Exception {
        if (bindingResult.hasErrors()) {
            model.addAttribute("cities", userService.getCities());
            model.addAttribute("user", user);
            return "admin/user/create";
        }
//Saving data
        userService.save(inputs);
        redirAttrs.addFlashAttribute("success", "User [" +
                inputs.get("name") + " " +
                inputs.get("surname") + "] " +
                "created successfully.");
        return "redirect:/admin/user";
    }



}

