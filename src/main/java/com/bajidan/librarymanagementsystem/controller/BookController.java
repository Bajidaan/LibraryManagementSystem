package com.bajidan.librarymanagementsystem.controller;

import com.bajidan.librarymanagementsystem.model.Books;
import com.bajidan.librarymanagementsystem.service.BookService;
import com.bajidan.librarymanagementsystem.wrapper.UserBookDetails;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/book")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @PostMapping("add")
    @ResponseStatus(HttpStatus.OK)
    public Map<String, String> addBook(@Valid @RequestBody Books book) {
        return bookService.addBook(book);
    }

    @GetMapping("getAll")
    public ResponseEntity<List<Books>> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("getById/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Books findBookById(@PathVariable Long id) {
        return bookService.findBookById(id);
    }

    @GetMapping("getByTitle/{title}")
    @ResponseStatus(HttpStatus.OK)
    public List<Books> findBookByTitle(@PathVariable String title) {
        return bookService.findBookByTitle(title);
    }

    @GetMapping("getByIsbn/{isbn}")
    @ResponseStatus(HttpStatus.OK)
    public Books findBookByIsbn(@PathVariable String isbn) {
        return bookService.findBookByIsbn(isbn);
    }
    @DeleteMapping("delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Map<String, String> deleteById(@PathVariable long id) {
        return bookService.deleteById(id);
    }

    @DeleteMapping("deleteByIsbn/{isbn}")
    @ResponseStatus(HttpStatus.OK)
    public Map<String, String> deleteByIsbn(@PathVariable String isbn) {
        return bookService.deleteByIsbn(isbn);
    }


    @PostMapping("borrowBook")
    @ResponseStatus(HttpStatus.OK)
    public Map<String, String> borrowBook(@RequestBody UserBookDetails details)  {
       return bookService.borrowBook(details);
    }








}
