package com.example.Library_System.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data                // GETTER AND SETTER, CONSTRUCTORS, BUILDER
@AllArgsConstructor  // VERY IMPORTANT. HELPS WITH ALL CONSTRUCTORS WHICH CAN BE USED IN CONTROLLER.
@NoArgsConstructor
@Entity
public class BooksInactive {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Genre genre;
    //private Long genreId;
    private String name;
    private boolean status;
}

