package com.bajidan.librarymanagementsystem.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class BorrowedBooks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String author;

    @OneToOne
    @JoinColumn(name = "user_id")
    private Users users;

    @OneToOne
    @JoinColumn(name = "book_id")
    private Books books;

    public BorrowedBooks(String title, String author, Users users, Books books) {
        this.title = title;
        this.author = author;
        this.users = users;
        this.books = books;
    }
}
