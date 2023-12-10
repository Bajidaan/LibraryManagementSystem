package com.bajidan.librarymanagementsystem.service;

import com.bajidan.librarymanagementsystem.execption.UserNotFoundException;
import com.bajidan.librarymanagementsystem.model.BorrowedBooks;
import com.bajidan.librarymanagementsystem.model.Users;
import com.bajidan.librarymanagementsystem.repository.BorrowedBooksRepository;
import com.bajidan.librarymanagementsystem.wrapper.BorrowedBookId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BorrowedBooksService {
    private final BorrowedBooksRepository borrowedBooksRepository;
    public void save(BorrowedBooks borrowedBook) {
        borrowedBooksRepository.save(borrowedBook);
    }

    public List<BorrowedBooks> getAll() {
        return borrowedBooksRepository.findAll();
    }

    public BorrowedBooks getById(long id) {
        return borrowedBooksRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("id:" + id + " does not exist")
        );
    }

    public Map<String, String> deleteById(long id) {
        borrowedBooksRepository.deleteById(id);
        return Map.of("message", "id:" + " is deleted");
    }

    public Map<String, String> deleteByEmail(String email) {
         BorrowedBookId idEmailPairs = borrowedBooksRepository.findAll()
                .stream()
                .map(y -> new BorrowedBookId(y.getId(), y.getUsers().getEmail()))
                .filter(x -> x.email().equalsIgnoreCase(email))
                 .findFirst()
                 .orElseThrow(
                         () -> new UserNotFoundException(email + " is not on borrowed record")
                 );

         deleteById(idEmailPairs.id());

        return Map.of("message", email + " is deleted");
    }


}
