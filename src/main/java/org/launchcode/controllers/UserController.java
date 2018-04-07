package org.launchcode.controllers;

import org.launchcode.models.User;
import org.launchcode.models.UserData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("user")
public class UserController {

    @RequestMapping(value = "")
    public String index(Model model) {
        model.addAttribute("title", "Users");
        model.addAttribute("users", UserData.getAll());
        return "user/index";
    }

    @RequestMapping(value = "signup", method = RequestMethod.GET)
    public String displaySignUpForm(Model model) {
        model.addAttribute("title", "User Sign Up");
        model.addAttribute(new User());
        return "user/signup";
    }

    @RequestMapping(value = "signup", method = RequestMethod.POST)
    public String processSignUpForm(@ModelAttribute @Valid User user,
                                    Errors errors, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "User Sign Up");
            return "user/signup";
        }
        UserData.add(user);

        return "redirect:";
    }
}
