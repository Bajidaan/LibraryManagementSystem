package com.bajidan.librarymanagementsystem.service;

import com.bajidan.librarymanagementsystem.execption.UserNotFoundException;
import com.bajidan.librarymanagementsystem.model.Users;
import com.bajidan.librarymanagementsystem.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @CacheEvict(cacheNames = {
            "allUser",
            "getById",
            "getByEmail",
            "getByFullName"
    }, allEntries = true)
    public ResponseEntity<String> addUser(Users user) {
        userRepository.save(user);
        return ResponseEntity.ok("Successfully registered...");
    }

    @Cacheable(cacheNames = "allUser")
    public ResponseEntity<List<Users>> getAllUsers() {
        return new ResponseEntity<>(userRepository.findAll(), HttpStatus.OK);
    }

    @Cacheable(cacheNames = "getById", key = "#id")
    public ResponseEntity<Users> findById(Long id) {
        return new ResponseEntity<>(userRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException("User not found")), HttpStatus.OK);
    }

    @Cacheable(cacheNames = "getByEmail", key = "#email")
    public ResponseEntity<Users> findByEmail(String email) {
        return new ResponseEntity<>(userRepository.findByEmail(email).orElseThrow(
                () -> new UserNotFoundException(email + " not found")), HttpStatus.OK);
    }

    @Cacheable(cacheNames = "getByFullName", key = "#name")
    public ResponseEntity<List<Users>> findByFullName(String name) {
        return new ResponseEntity<>(userRepository.findByFullName(name).orElseThrow(
                () -> new UserNotFoundException(name + " not found")), HttpStatus.CREATED);
    }

    @CacheEvict(cacheNames = {
            "allUser",
            "getById",
            "getByEmail",
            "getByFullName"
    }, allEntries = true)
    public String updateUser(Long id, Users user) {
        Users newUser = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found"));

        newUser.setFullName(user.getFullName());
        newUser.setEmail(user.getEmail());
        newUser.setAge(user.getAge());
        newUser.setAddress(user.getAddress());

        userRepository.save(newUser);
        return "Successfully updated";
    }

    @CacheEvict(cacheNames = {
            "allUser",
            "getById",
            "getByEmail",
            "getByFullName"
    }, allEntries = true)
    public String deleteUser(Long id) {
        Users user = userRepository.findById(id).orElseThrow();
        userRepository.deleteById(id);
        return user.getFullName() + " has been removed";
    }
}
