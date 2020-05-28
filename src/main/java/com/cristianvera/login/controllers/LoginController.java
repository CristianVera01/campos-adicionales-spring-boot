package com.cristianvera.login.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login(@RequestParam(value = "", required = false) String error, Model model, Principal principal, RedirectAttributes flash) {
        if(principal != null) {
            flash.addFlashAttribute("info", "Ya haz iniciado sesión anteriormente.");
            return "redirect:/login";
        }

        if(error != null) {
            model.addAttribute("error", "El usuario y/o contraseña son incorrectos.");
        }

        model.addAttribute("titulo", "Inicio de sesión");
        return "login";
    }
}
