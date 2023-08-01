package org.launchcode.spaday.controllers;

import org.launchcode.spaday.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("user")
public class UserController {

    @GetMapping("add")
    public String displayAddUserForm() {
        return "/user/add";
    }

    @PostMapping
    public String processAddUserForm(Model model, @ModelAttribute User user, @RequestParam String verifyPassword) {

        if (user.getPassword().equals(verifyPassword)) {
            model.addAttribute("user", user);
            return "/user/index";
        }

        String errorMessage = "Passwords do not match!";
        model.addAttribute("error", errorMessage);
        model.addAttribute("username", user.getUsername());
        model.addAttribute("email", user.getEmail());

        return "/user/add";
    }
}
