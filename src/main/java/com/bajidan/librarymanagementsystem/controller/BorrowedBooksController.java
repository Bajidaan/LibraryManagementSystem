package com.bajidan.librarymanagementsystem.controller;

import com.bajidan.librarymanagementsystem.model.BorrowedBooks;
import com.bajidan.librarymanagementsystem.service.BorrowedBooksService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/borrowedBook")
@RequiredArgsConstructor
public class BorrowedBooksController {
    private final BorrowedBooksService borrowedBooksService;

    @GetMapping("getAll")
    @ResponseStatus(HttpStatus.OK)
    public List<BorrowedBooks> getAll() {
        return borrowedBooksService.getAll();
    }
    @GetMapping("getById/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BorrowedBooks getById(@PathVariable long id) {
        return borrowedBooksService.getById(id);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Map<String, String> deleteById(@PathVariable long id) {
        return borrowedBooksService.deleteById(id);
    }

    @DeleteMapping("return/{email}")
    public Map<String, String> returnBorrowedBook(@PathVariable String email) {
        return borrowedBooksService.deleteByEmail(email);
    }


}
