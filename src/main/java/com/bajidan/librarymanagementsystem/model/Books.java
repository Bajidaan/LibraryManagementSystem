package com.bajidan.librarymanagementsystem.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Entity
@Data
@NoArgsConstructor
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = "isbn")})
public class Books {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Length(min = 3, message = "length must not be than 3")
    private String title;

    @Length(min = 3, max = 15)
    private String author;

    @Pattern(regexp = "[A-Z]{3}-[0-9]{3}")
    private String isbn;

    @Min(value = 1000)
    @Max(value = 2023)
    private int year;

    public Books(String title, String author, String isbn, int year) {
        this.title = title.toLowerCase();
        this.author = author.toLowerCase();
        this.isbn = isbn;
        this.year = year;
    }
}
