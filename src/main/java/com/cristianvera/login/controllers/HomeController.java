package com.cristianvera.login.controllers;

import com.cristianvera.login.models.entity.SpringUser;
import com.cristianvera.login.models.entity.Usuario;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping(value = {"/", "/home"})
    public String home(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        SpringUser usuario = (SpringUser) authentication.getPrincipal();
        model.addAttribute("usuario", usuario);
        return "home";
    }
}
