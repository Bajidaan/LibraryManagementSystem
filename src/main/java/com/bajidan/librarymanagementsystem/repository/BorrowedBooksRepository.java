package com.bajidan.librarymanagementsystem.repository;

import com.bajidan.librarymanagementsystem.model.BorrowedBooks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BorrowedBooksRepository extends JpaRepository<BorrowedBooks, Long> { }
