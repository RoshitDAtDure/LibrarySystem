package com.example.Library_System.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Library_System.model.BooksActive;

public interface BooksActiveRepo extends JpaRepository<BooksActive, Long> {

    List<BooksActive> findByName(String name);

    List<BooksActive> findByStatus(boolean status);

    List<BooksActive> findByGenreId(Long genreId);

}

