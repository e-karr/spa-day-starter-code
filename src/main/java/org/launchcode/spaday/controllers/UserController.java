package org.launchcode.spaday.controllers;

import org.launchcode.spaday.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("user")
public class UserController {

    @GetMapping("/add")
    public String displayAddUserForm(Model model) {
        model.addAttribute(new User());
        return "user/add";
    }

    @PostMapping
    public String processAddUserForm(Model model, @ModelAttribute User user) {
        model.addAttribute("user", user);
        if (user.getPassword().equals(user.getVerify())) {
           return "user/index";
        }
        else {
            model.addAttribute("error", "Passwords do not match");
            return "user/add";
        }

    }


}
