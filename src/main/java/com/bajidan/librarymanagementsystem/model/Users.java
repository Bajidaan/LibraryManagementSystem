package com.bajidan.librarymanagementsystem.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "users", uniqueConstraints = {@UniqueConstraint(columnNames = "email")})
@Data
@NoArgsConstructor
public class Users {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Length(min = 6, max = 15)
    private String fullName;

    @Email
    private String email;

    @Min(value = 18, message = "You must be 18 years and above")
    @Max(value = 70, message = "You are too old")
    private int age;

    @NotBlank
    private String address;

    public Users(String fullName, String email, int age, String address) {
        this.fullName = fullName;
        this.email = email;
        this.age = age;
        this.address = address;
    }
}
