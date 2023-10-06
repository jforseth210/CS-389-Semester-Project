package edu.carroll.bankapp.web.controller;

import edu.carroll.bankapp.jpa.model.SiteUser;
import edu.carroll.bankapp.service.UserService;
import edu.carroll.bankapp.web.form.LoginForm;
import edu.carroll.bankapp.web.form.NewLoginForm;
import jakarta.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * This controller is responsible for all authentication routes. Logging in/out,
 * signing up, etc.
 */
@Controller
public class LoginController {

    private static final Logger log = LoggerFactory.getLogger(LoginController.class);
    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    /**
     * The login page
     */
    @GetMapping("/login")
    public String loginGet(Model model) {
        model.addAttribute("loginForm", new LoginForm());
        return "loginExisting";
    }

    /**
     * The sign up pages
     */
    @GetMapping("/loginNew")
    public String loginNewGet(Model model) {
        model.addAttribute("newLoginForm", new NewLoginForm());
        return "loginNew";
    }

    /**
     * This page accepts form submissions for (user) account creation
     *
     * @param newLoginForm The data collected from the form
     * @param result       Form errors (if any)
     * @return
     */
    @PostMapping("/loginNew")
    public String loginNewPost(@Valid @ModelAttribute NewLoginForm newLoginForm, BindingResult result) {
        SiteUser createdUser = userService.createUser(newLoginForm);
        if (createdUser != null || result.hasErrors()) {
            return "loginNew";
        }
        log.info("Redirecting to \"/\"");
        return "redirect:/";
    }
}
