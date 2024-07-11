package com.example.Library_System.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Library_System.BookUpdateRequest;
import com.example.Library_System.model.BooksActive;
import com.example.Library_System.model.BooksInactive;
import com.example.Library_System.model.Genre;
import com.example.Library_System.repository.BooksActiveRepo;
import com.example.Library_System.repository.BooksInactiveRepo;
import com.example.Library_System.repository.GenreRepo;



@CrossOrigin(origins = "http://localhost:8080")
@RestController
 //to identify different controllers created. In this case only one is created.   
@RequestMapping("/api")
public class LibraryController {
	
	@Autowired
	BooksActiveRepo booksActiveRepo;
	

	@Autowired
	BooksInactiveRepo booksInactiveRepo;
	
	@Autowired
	GenreRepo genreRepo;
	
	
	
	/*
	 1) Create : Genre, Books
	 2) Retrieve : All genres, books belonging to a genre, active books, inactive books
	 3) Update : Update a book (name,genreId), change status of book (and change collection) 
	 4) Delete : Delete a genre or delete a book by title,id or status
	 */
	
	
	
	//::::::::::::::::::::::::::::::::::::::::::::::::CREATE:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
		@PostMapping("/genre")
			public ResponseEntity<Genre> createGenre(@RequestBody Genre genre) {
				try {
					Genre savedGenre = genreRepo.save(genre);
					return new ResponseEntity<>(savedGenre, HttpStatus.CREATED);
				} 
				catch (Exception e) {
					return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
				}
		}
		
		
        @PostMapping("/books/active")
        public ResponseEntity<BooksActive> addActiveBook(@RequestBody BooksActive book) {
            try {
            	
            	 if (!genreRepo.existsById(book.getGenre().getId())) {
                     return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                 }
                BooksActive savedBook = booksActiveRepo.save(book);
                return new ResponseEntity<>(savedBook, HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        @PostMapping("/books/inactive")
        public ResponseEntity<BooksInactive> addInactiveBook(@RequestBody BooksInactive book) {
            try {
            	
            	if (!genreRepo.existsById(book.getGenre().getId())) {
                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                }

                BooksInactive savedBook = booksInactiveRepo.save(book);
                return new ResponseEntity<>(savedBook, HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            }}
        
        
       
        //:::::::::::::::::::::::::::::::::::::RETRIEVE:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
        @GetMapping("/genre")
        public ResponseEntity<List<Genre>> getAllGenres() {
            try {
                List<Genre> genres = genreRepo.findAll();

                if (genres.isEmpty()) {
                    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                }

                return new ResponseEntity<>(genres, HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        
        @GetMapping("/genre/{id}/books")
    	public ResponseEntity<List<Object>> getAllBooks(@PathVariable("id") Long genreId) {
            try {
                List<Object> books = new ArrayList<>();

                // Retrieve all active books for the given genre ID
                List<BooksActive> activeBooks = booksActiveRepo.findByGenreId(genreId);
                if (activeBooks != null) {
                    books.addAll(activeBooks);
                }

                // Retrieve all inactive books for the given genre ID

                List<BooksInactive> inactiveBooks = booksInactiveRepo.findByGenreId(genreId);
                if (inactiveBooks != null) {
                    books.addAll(inactiveBooks);
                }

                if (books.isEmpty()) {
                    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                }

                return new ResponseEntity<>(books, HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            }
    	}
        
        @GetMapping("/books/active")
        public ResponseEntity<List<BooksActive>> getAllActiveBooks() {
            try {
                List<BooksActive> activeBooks = booksActiveRepo.findAll();
                if (activeBooks.isEmpty()) {
                    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                }
                return new ResponseEntity<>(activeBooks, HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        
        
        
        
        @GetMapping("/books/inactive")
        public ResponseEntity<List<BooksInactive>> getAllInactiveBooks() {
            try {
                List<BooksInactive> inactiveBooks = booksInactiveRepo.findAll();
                if (inactiveBooks.isEmpty()) {
                    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                }
                return new ResponseEntity<>(inactiveBooks, HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        
        @GetMapping("/books")
        public ResponseEntity<List<Object>> getAllBooks() {
            try {
                List<Object> allBooks = new ArrayList<>();

                List<BooksActive> activeBooks = booksActiveRepo.findAll();
                if (activeBooks != null) {
                    allBooks.addAll(activeBooks);
                }

                List<BooksInactive> inactiveBooks = booksInactiveRepo.findAll();
                if (inactiveBooks != null) {
                    allBooks.addAll(inactiveBooks);
                }

                if (allBooks.isEmpty()) {
                    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                }

                return new ResponseEntity<>(allBooks, HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
            
// ::::::::::::::::::::::::::::::::::::::::::::::::::::::::UPDATE:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
        
        @PutMapping("/genre/{id}")
        public ResponseEntity<Genre> updateGenre(@PathVariable("id") Long id, @RequestBody Genre genreDetails) {
            try {
                // Fetch the existing genre by ID
                Optional<Genre> genreData = genreRepo.findById(id);
                
                if (genreData.isPresent()) {
                    // Get the existing genre object
                    Genre existingGenre = genreData.get();
                    
                    // Update the name and type fields
                    existingGenre.setType(genreDetails.getType());
                    existingGenre.setPublished(genreDetails.isPublished());
                    
                    // Save the updated genre back to the repository
                    Genre updatedGenre = genreRepo.save(existingGenre);
                    return new ResponseEntity<>(updatedGenre, HttpStatus.OK);
                } else {
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                }
            } catch (Exception e) {
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        
        @PutMapping("/books/{id}")
        public ResponseEntity<Object> updateBook(@PathVariable("id") Long id, @RequestBody BookUpdateRequest bookDetails) {
            try {
                // Check if the book exists in active collection
                Optional<BooksActive> activeBookData = booksActiveRepo.findById(id);

                if (activeBookData.isPresent()) {
                    BooksActive existingBook = activeBookData.get();

                    // Check if the status has changed
                    if (existingBook.isStatus() != bookDetails.isStatus()) {
                        // Transfer book to the inactive collection
                        
                        BooksInactive newInactiveBook = new BooksInactive(existingBook.getId(), existingBook.getGenre(), bookDetails.getName(), bookDetails.isStatus());
                        booksInactiveRepo.save(newInactiveBook);
                        booksActiveRepo.deleteById(id);
                        return new ResponseEntity<>(newInactiveBook, HttpStatus.OK);
                    } else {
                        // Update the book details and save to the same collection
                        existingBook.setName(bookDetails.getName());
                        existingBook.setStatus(bookDetails.isStatus());
                        booksActiveRepo.save(existingBook);
                        return new ResponseEntity<>(existingBook, HttpStatus.OK);
                    }
                } else {
                    // Check if the book exists in inactive collection
                    Optional<BooksInactive> inactiveBookData = booksInactiveRepo.findById(id);

                    if (inactiveBookData.isPresent()) {
                        BooksInactive existingBook = inactiveBookData.get();

                        // Check if the status has changed
                        if (existingBook.isStatus() != bookDetails.isStatus()) {
                            // Transfer book to the active collection
                           
                            BooksActive newActiveBook = new BooksActive(existingBook.getId(), existingBook.getGenre(), bookDetails.getName(), bookDetails.isStatus());
                            booksActiveRepo.save(newActiveBook);
                            booksInactiveRepo.deleteById(id);
                            return new ResponseEntity<>(newActiveBook, HttpStatus.OK);
                        } else {
                            // Update the book details and save to the same collection
                            existingBook.setName(bookDetails.getName());
                            existingBook.setStatus(bookDetails.isStatus());
                            booksInactiveRepo.save(existingBook);
                            return new ResponseEntity<>(existingBook, HttpStatus.OK);
                        }
                    } else {
                        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                    }
                }
            } catch (Exception e) {
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        
        //::::::::::::::::::::::::::::::::::::::DELETE:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
        
        
}
