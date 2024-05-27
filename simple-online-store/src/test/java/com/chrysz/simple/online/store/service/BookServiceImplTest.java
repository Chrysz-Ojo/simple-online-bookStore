package com.chrysz.simple.online.store.service;

import com.chrysz.simple.online.store.entity.Book;
import com.chrysz.simple.online.store.exception.ResourceNotFoundException;
import com.chrysz.simple.online.store.repository.BookRepository;
import com.chrysz.simple.online.store.services.impl.BookServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class BookServiceImplTest {

    @InjectMocks
    private BookServiceImpl bookService;

    @Mock
    private BookRepository bookRepository;

    private Book book;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        book = new Book();
        book.setId(1L);
        book.setTitle("Effective Java");
        book.setAuthor("Joshua Bloch");
        book.setIsbn("978-0134685991");
        book.setPublicationYear(2017);
        book.setPrice(45.00);
        book.setAvailable(true);
    }

    @Test
    void testAddBook() {
        when(bookRepository.save(book)).thenReturn(book);
        Book savedBook = bookService.addBook(book);
        assertTrue(savedBook.isAvailable());
        verify(bookRepository, times(1)).save(book);
    }

    @Test
    void testGetBookById() {
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
        Optional<Book> foundBook = bookService.getBookById(1L);
        assertTrue(foundBook.isPresent());
        verify(bookRepository, times(1)).findById(1L);
    }

    @Test
    void testDeleteBook() {
        when(bookRepository.existsById(1L)).thenReturn(true);
        doNothing().when(bookRepository).deleteById(1L);
        bookService.deleteBook(1L);
        verify(bookRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDeleteBookNotFound() {
        when(bookRepository.existsById(1L)).thenReturn(false);
        assertThrows(ResourceNotFoundException.class, () -> bookService.deleteBook(1L));
        verify(bookRepository, never()).deleteById(1L);
    }

    @Test
    void testCheckBookAvailability() {
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
        when(bookRepository.save(book)).thenReturn(book);
        Book updatedBook = bookService.checkAvailability(1L, false);
        assertTrue(!updatedBook.isAvailable());
        verify(bookRepository, times(1)).findById(1L);
        verify(bookRepository, times(1)).save(book);
    }
}
