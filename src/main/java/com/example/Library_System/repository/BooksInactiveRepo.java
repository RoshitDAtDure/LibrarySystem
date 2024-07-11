package com.example.Library_System.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Library_System.model.BooksInactive;

public interface BooksInactiveRepo extends JpaRepository<BooksInactive, Long> {

    List<BooksInactive> findByName(String name);

    List<BooksInactive> findByStatus(boolean status);

    List<BooksInactive> findByGenreId(Long genreId);

}
