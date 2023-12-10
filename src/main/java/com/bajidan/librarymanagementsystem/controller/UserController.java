package com.bajidan.librarymanagementsystem.controller;

import com.bajidan.librarymanagementsystem.model.Users;
import com.bajidan.librarymanagementsystem.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    @GetMapping("allUser")
    public ResponseEntity<List<Users>> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Users> findUserById(@PathVariable Long id) {
        return userService.findById(id);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<Users> findUserByEmail(@PathVariable String email) {
        return userService.findByEmail(email);
    }


    @GetMapping("/name/{name}")
    public ResponseEntity<List<Users>> findUserByFullName(@PathVariable String name) {
        return userService.findByFullName(name);
    }

    @PostMapping("signUp")
    public ResponseEntity<String> addUser(@Valid @RequestBody Users user) {
        return userService.addUser(user);
    }

    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String updateUser(@PathVariable Long id, @Valid @RequestBody  Users user) {
        return userService.updateUser(id, user);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String deleteUser(@PathVariable Long id) {
        return userService.deleteUser(id);
    }






}
