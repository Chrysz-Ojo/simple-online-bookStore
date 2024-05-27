package com.chrysz.simple.online.store.controller;

import com.chrysz.simple.online.store.entity.Book;
import com.chrysz.simple.online.store.exception.ResourceNotFoundException;
import com.chrysz.simple.online.store.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> books = bookService.getAllBooks();
        return ResponseEntity.ok(books);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) throws ResourceNotFoundException {
        Book book = bookService.getBookById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book with id " + id + " not found", HttpStatus.NOT_FOUND));
        return ResponseEntity.ok(book);
    }



    @PostMapping
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        Book savedBook = bookService.addBook(book);
        return ResponseEntity.status(201).body(savedBook);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book bookDetails) {
        Book updatedBook = bookService.updateBook(id, bookDetails);
        return ResponseEntity.ok(updatedBook);
    }

    @GetMapping("/{id}/availability")
    public ResponseEntity<Map<String, Object>> checkAvailability(@PathVariable Long id) throws ResourceNotFoundException {
        Book book = bookService.getBookById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book with id " + id + " not found", HttpStatus.NOT_FOUND));

        Map<String, Object> response = new HashMap<>();
        response.put("id", book.getId());
        response.put("title", book.getTitle());
        response.put("available", book.isAvailable());

        return ResponseEntity.ok(response);
    }



//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
//        bookService.deleteBook(id);
//        return ResponseEntity.noContent().build();
//    }



    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteBook(@PathVariable Long id) throws ResourceNotFoundException{
        bookService.deleteBook(id);
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Book deleted successfully");
        response.put("bookId", id);
        return ResponseEntity.ok(response);
    }
}