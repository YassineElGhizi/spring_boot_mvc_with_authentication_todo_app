package com.advanced_to_list.advanced_to_list.controllers;

import com.advanced_to_list.advanced_to_list.entities.User;
import com.advanced_to_list.advanced_to_list.FormValidation.Login;
import com.advanced_to_list.advanced_to_list.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class HomeController {
    private final UserRepository userRepository;

    @Autowired
    public HomeController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @RequestMapping(value = {"/"}, method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("loginForm", new Login());
        return "home";
    }

    @RequestMapping(value = {"/signin"}, method = RequestMethod.POST)
    public String signing(Model model, @ModelAttribute("loginForm") Login login, HttpServletRequest request) {
        User user = userRepository.findUserByEmail(login.getEmail());
        if (user == null) {
            model.addAttribute("error", "USER DOESNT EXISTS");
            return "home";
        }

        if (!user.checkPassword(login.getPassword())) {
            model.addAttribute("error", "Password Is Incorrect");
            return "home";
        }

        model.addAttribute("user_name", user.getName());
        model.addAttribute("user_id", user.getId());
        model.addAttribute("msg", null);
        model.addAttribute("todos", user.getTodos());

        request.getSession().setAttribute("id", user.getId());
        return "todo";
    }

    @RequestMapping(value = {"/signin"}, method = RequestMethod.GET)
    public String signing_get(Model model, HttpSession session) {
        @SuppressWarnings("unchecked") Long user_id = (Long) session.getAttribute("id");
        User user = userRepository.findUserById(user_id);

        model.addAttribute("user_name", user.getName());
        model.addAttribute("user_id", user.getId());
        model.addAttribute("msg", null);
        model.addAttribute("todos", user.getTodos());

        return "todo";
    }


    @RequestMapping(value = {"/register"}, method = RequestMethod.GET)
    public String register(Model model) {
        model.addAttribute("registerForm", new User());
        return "register";
    }

    @RequestMapping(value = {"/register"}, method = RequestMethod.POST)
    public String save_user_to_db(Model model, @ModelAttribute("user") User user) {
        if (userRepository.countByEmail(user.getEmail()) > 0) {
            model.addAttribute("error", "User Already Exists !");
            return "register";
        }
        userRepository.save(user);
        return "home";
    }
}
