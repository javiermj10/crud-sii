package com.siigroup.crud.services;

import com.siigroup.crud.entities.BookEntity;
import com.siigroup.crud.repositories.BookRepository;
import com.siigroup.crud.services.impl.BookServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookServiceImpl bookService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAll() {
        // Arrange
        BookEntity book1 = new BookEntity(1L, "Book 1");
        BookEntity book2 = new BookEntity(2L, "Book 2");
        when(bookRepository.findAll()).thenReturn(Arrays.asList(book1, book2));

        // Act
        List<BookEntity> books = bookService.findAll();

        // Assert
        assertNotNull(books);
        assertEquals(2, books.size());
        verify(bookRepository, times(1)).findAll();
    }

    @Test
    void testFindById() {
        // Arrange
        BookEntity book = new BookEntity(1L, "Test Book");
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));

        // Act
        Optional<BookEntity> foundBook = bookService.findById(1L);

        // Assert
        assertTrue(foundBook.isPresent());
        assertEquals("Test Book", foundBook.get().getTitle());
        verify(bookRepository, times(1)).findById(1L);
    }

    @Test
    void testSaveBook() {
        // Arrange
        BookEntity book = new BookEntity(null, "New Book");
        when(bookRepository.save(any(BookEntity.class))).thenReturn(new BookEntity(1L, "New Book"));

        // Act
        BookEntity savedBook = bookService.saveBook(book);

        // Assert
        assertNotNull(savedBook);
        assertEquals(1L, savedBook.getId());
        assertEquals("New Book", savedBook.getTitle());
        verify(bookRepository, times(1)).save(book);
    }

    @Test
    void testUpdateBook() {
        // Arrange
        BookEntity book = new BookEntity(1L, "Updated Book");
        when(bookRepository.save(any(BookEntity.class))).thenReturn(book);

        // Act
        BookEntity updatedBook = bookService.updateBook(book);

        // Assert
        assertNotNull(updatedBook);
        assertEquals("Updated Book", updatedBook.getTitle());
        verify(bookRepository, times(1)).save(book);
    }

    @Test
    void testDeleteBook() {
        // Arrange
        doNothing().when(bookRepository).deleteById(1L);

        // Act
        bookService.deleteBook(1L);

        // Assert
        verify(bookRepository, times(1)).deleteById(1L);
    }

}
