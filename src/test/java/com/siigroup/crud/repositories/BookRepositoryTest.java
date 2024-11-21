package com.siigroup.crud.repositories;

import com.siigroup.crud.entities.BookEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Test
    void testSaveBook() {
        // Arrange
        BookEntity book = new BookEntity();
        book.setTitle("Book");

        // Act
        BookEntity savedBook = bookRepository.save(book);

        // Assert
        assertNotNull(savedBook.getId());
        assertEquals("Book", savedBook.getTitle());
    }

    @Test
    void testFindById() {
        // Arrange
        BookEntity book = new BookEntity();
        book.setTitle("Book");
        BookEntity savedBook = bookRepository.save(book);

        // Act
        Optional<BookEntity> testBook = bookRepository.findById(savedBook.getId());

        // Assert
        assertTrue(testBook.isPresent());
        assertEquals("Book", testBook.get().getTitle());
    }
}
