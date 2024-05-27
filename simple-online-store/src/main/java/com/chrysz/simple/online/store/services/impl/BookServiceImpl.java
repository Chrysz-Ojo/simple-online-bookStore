package com.chrysz.simple.online.store.services.impl;

import com.chrysz.simple.online.store.entity.Book;
import com.chrysz.simple.online.store.exception.ResourceNotFoundException;
import com.chrysz.simple.online.store.repository.BookRepository;
import com.chrysz.simple.online.store.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;
    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Optional<Book> getBookById(Long id) {
        return bookRepository.findById(id);
    }

    @Override
    public Book addBook(Book book) {
        book.setAvailable(true);
        return bookRepository.save(book);
    }

    @Override
    public Book updateBook(Long id, Book bookDetails) {
        Book book = bookRepository.findById(id).orElseThrow(()-> new RuntimeException("Book with" + id + "not found"));
        book.setTitle(bookDetails.getTitle());
        book.setAuthor(bookDetails.getAuthor());
        book.setIsbn(bookDetails.getIsbn());
        book.setPublicationYear(book.getPublicationYear());
        book.setPrice(bookDetails.getPrice());
        book.setAvailable(bookDetails.isAvailable());
        return bookRepository.save(book);
    }

    @Override
    public void deleteBook(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new ResourceNotFoundException("Book with " + id + " not found", HttpStatus.NOT_FOUND);
        }
        bookRepository.deleteById(id);

    }

    @Override
    public Book checkAvailability(Long id, boolean available) {
        Book book = bookRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Book with id " + id + " not found", HttpStatus.NOT_FOUND));
         book.setAvailable(available);
         return bookRepository.save(book);

    }
}
