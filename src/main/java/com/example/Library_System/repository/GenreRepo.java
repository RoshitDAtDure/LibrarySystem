package com.example.Library_System.repository;

import org.springframework.data.jpa.repository.JpaRepository; // Changed import to JpaRepository

import com.example.Library_System.model.Genre;

public interface GenreRepo extends JpaRepository<Genre, Long> { // Changed MongoRepository to JpaRepository and the ID type to Long

}
