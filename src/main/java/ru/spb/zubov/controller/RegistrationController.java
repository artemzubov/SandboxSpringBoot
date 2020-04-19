package ru.spb.zubov.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.spb.zubov.domain.User;
import ru.spb.zubov.service.UserService;

import javax.validation.Valid;
import java.util.Map;

import static ru.spb.zubov.controller.ControllerUtils.getErrors;

@Controller
public class RegistrationController {

    @Autowired
    private UserService userService;

    @GetMapping("/registration")
    public String registration(){
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(@RequestParam String password2,
                          @Valid User user,
                          BindingResult bindingResult,
                          Model model){


        boolean isPasswordError = user.getPassword() != null && !user.getPassword().equals(password2);
        if (isPasswordError) {
            model.addAttribute("passwordError", "Passwords are different");
        }
        boolean isPassword2Empty = StringUtils.isEmpty(password2);
        if (isPassword2Empty) {
            model.addAttribute("password2Error", "Password confirmation cannot be empty");
        }

        if (isPassword2Empty || isPasswordError || bindingResult.hasErrors()) {
            Map<String, String> errors = getErrors(bindingResult);
            model.mergeAttributes(errors);
            return "registration";
        }

        if (!userService.addUser(user)) {
            model.addAttribute("usernameError", "User exists!");
            return "registration";
        }

        return "redirect: /login";
    }

    @GetMapping("/activate/{code}")
    public String activate(Model model, @PathVariable String code) {

        if (userService.activateUser(code)) {
            model.addAttribute("messageType", "success");
            model.addAttribute("message", "User is activated");
        }
        else {
            model.addAttribute("messageType", "danger");
            model.addAttribute("message", "Activation code is not found");
        }

        return "login";
    }

}
