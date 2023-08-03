package org.launchcode.spaday.controllers;

import org.launchcode.spaday.data.UserData;
import org.launchcode.spaday.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController {

    @GetMapping("add")
    public String displayAddUserForm() {
        return "/user/add";
    }

    @GetMapping("detail/{userId}")
    public String displayUserDetails(Model model, @PathVariable int userId) {
        User selectedUser = UserData.getById(userId);
        model.addAttribute("user", selectedUser);
        return "/user/detail";
    }

    @PostMapping
    public String processAddUserForm(Model model, @ModelAttribute User user, @RequestParam String verifyPassword) {

        if (user.getPassword().equals(verifyPassword)) {
            model.addAttribute("user", user);
            UserData.add(user);
            model.addAttribute("users", UserData.getAll());
            return "/user/index";
        }

        String errorMessage = "Passwords do not match!";
        model.addAttribute("error", errorMessage);
        model.addAttribute("username", user.getUsername());
        model.addAttribute("email", user.getEmail());

        return "/user/add";
    }
}
