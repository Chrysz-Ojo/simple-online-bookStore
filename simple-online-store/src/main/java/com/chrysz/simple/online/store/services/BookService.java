package com.chrysz.simple.online.store.services;

import com.chrysz.simple.online.store.entity.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {

    List<Book> getAllBooks();
    Optional<Book> getBookById(Long id);
    Book addBook(Book book);
    Book updateBook(Long id, Book bookDetails);
    void deleteBook(Long id);

    Book checkAvailability(Long id, boolean available);
}

