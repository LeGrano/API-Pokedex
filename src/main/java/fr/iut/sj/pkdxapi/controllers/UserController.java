package fr.iut.sj.pkdxapi.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.iut.sj.pkdxapi.models.UserDTO;
import fr.iut.sj.pkdxapi.models.UserData;
import fr.iut.sj.pkdxapi.services.CustomUserDetailsService;
import fr.iut.sj.pkdxapi.services.UserDataService;

@RestController
public class UserController {

    private UserDataService userDataService;

    public UserController(UserDataService userDataService) {
        this.userDataService = userDataService;
    }
    
    @GetMapping("/register")
    public String registerForm() {
        // Afficher le formulaire d'inscription
        return "register";
    }
    
    @PostMapping("/users/register")
    public ResponseEntity<String> registerUser(@RequestBody UserDTO userDTO) {
        userDataService.createUser(userDTO);
        return ResponseEntity.ok("User created");
    }

    @GetMapping("users/login")
    public ResponseEntity<String> login() {
        return ResponseEntity.ok("User logged in");
    }
}
