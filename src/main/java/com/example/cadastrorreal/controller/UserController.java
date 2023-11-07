package com.example.cadastrorreal.controller;

import com.example.cadastrorreal.model.Evento;
import com.example.cadastrorreal.model.User;
import com.example.cadastrorreal.repository.EventRepository;
import com.example.cadastrorreal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/userAction")
    public String userAction(@RequestParam("username") String username,
                             @RequestParam("email") String email,
                             @RequestParam ("password") String password,
                             @RequestParam("action") String action, Model model) {
        User newUser = new User();

        if ("register".equals(action)) {
            if (userRepository.findByUsernameAndPassword(username, password) == null) {
                newUser.setUsername(username);
                newUser.setEmail(email);
                newUser.setPassword(password);
                userRepository.save(newUser);
                model.addAttribute("message", "Novo usário registrado!");
            }else {
                model.addAttribute("message", "Este usuario ja existe.., 👀");
            }
        }else if ("login".equals(action)){
            User user = userRepository.findByUsername(username);
            if (user != null && user.getPassword().equals(password)){
                model.addAttribute("username", username);
                model.addAttribute("mensagem", "é voce meeesmo");
            }else {
                model.addAttribute("mensagem", "Senha ou usuário incorretos");
            }
        }else {
            model.addAttribute("mensagem", "Ação inválida");
        }
        return "index";

    }
    @GetMapping("/home")
    public String home(Model model, @RequestParam(value = "username", required = false) String username) {
        if (username != null) {
            model.addAttribute("username", username);
        }
        return "index.html";
    }

}

