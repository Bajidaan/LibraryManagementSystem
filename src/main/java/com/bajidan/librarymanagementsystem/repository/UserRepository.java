package com.bajidan.librarymanagementsystem.repository;

import com.bajidan.librarymanagementsystem.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<Users, Long> {
    Optional<Users> findByEmail(String email);

    Optional<List<Users>> findByFullName(String name);
}
