package com.bajidan.librarymanagementsystem.service;

import com.bajidan.librarymanagementsystem.execption.UserNotFoundException;
import com.bajidan.librarymanagementsystem.model.Books;
import com.bajidan.librarymanagementsystem.model.BorrowedBooks;
import com.bajidan.librarymanagementsystem.model.Users;
import com.bajidan.librarymanagementsystem.repository.BookRepository;
import com.bajidan.librarymanagementsystem.wrapper.UserBookDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final UserService userService;
    private final BorrowedBooksService borrowedBooksService;

    @CacheEvict(cacheNames = {
            "allBooks",
            "getById",
            "getByTitle",
            "getByIsbn",
            "getByFullTitle"
    }, allEntries = true)
    public Map<String, String> addBook(Books book) {
        bookRepository.save(book);
        return Map.of("message", book.getTitle()  + " has been successfully added");
    }

    @Cacheable(cacheNames = "allBooks")
    public ResponseEntity<List<Books>> getAllBooks() {
        return ResponseEntity.ok(bookRepository.findAll());
    }

    @Cacheable(cacheNames = "getById")
    public Books findBookById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(
                        () -> new UserNotFoundException(String.format("Book with id %d does not exist", id)));
    }


    @Cacheable(cacheNames = "getByTitle")
    public List<Books> findBookByTitle(String title) {
        return bookRepository.findBooksByTitle(title).orElseThrow(
                () -> new UserNotFoundException(String.format("Book with title: \"%s\" does not exist", title)));
    }
    @Cacheable(cacheNames = "getByFullTitle")
    public Books findSingleBookByTitle(String title) {
        return bookRepository.findByTitle(title).orElseThrow(
                () -> new UserNotFoundException(String.format("Book with title: \"%s\" does not exist", title)));
    }

    @Cacheable(cacheNames = "getByIsbn")
    public Books findBookByIsbn(String isbn) {
        return bookRepository.findByIsbn(isbn).orElseThrow(
                () -> new UserNotFoundException(String.format("Book with isbn: \"%s\" does not exist", isbn)));
    }

    @CacheEvict(cacheNames = {
            "allBooks",
            "getById",
            "getByTitle",
            "getByIsbn",
            "getByFullTitle"
    }, allEntries = true)
    public Map<String, String> deleteById(long id) {
        bookRepository.deleteById(id);
        return Map.of("message", "Book deleted");
    }


    public Map<String, String> borrowBook(UserBookDetails details)  {
        Users borrowedUser = userService.findByEmail(details.userEmail()).getBody();
        Books bookToBeBorrowed = findSingleBookByTitle(details.title());

        BorrowedBooks borrowedBook = new BorrowedBooks(
                bookToBeBorrowed.getTitle(),
                bookToBeBorrowed.getAuthor(),
                borrowedUser, bookToBeBorrowed
        );

        borrowedBooksService.save(borrowedBook);
        assert borrowedUser != null;
        return Map.of("usersFullName", borrowedUser.getFullName(),
                "bookBorrowed", borrowedBook.getTitle());

    }
}
