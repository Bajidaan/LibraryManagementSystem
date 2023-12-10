package com.bajidan.librarymanagementsystem.repository;

import com.bajidan.librarymanagementsystem.model.Books;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Books, Long> {

    @Query("SELECT b FROM Books b WHERE LOWER(b.title) LIKE LOWER(CONCAT('%', :title, '%'))")
    Optional<List<Books>> findBooksByTitle(@Param("title") String title);
    Optional<Books> findByTitle(String title);
    Optional<Books> findByIsbn(String isbn);

    @Transactional
    void deleteByIsbn(String isbn);
}
