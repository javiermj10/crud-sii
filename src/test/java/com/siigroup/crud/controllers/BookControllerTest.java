package com.siigroup.crud.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.siigroup.crud.entities.BookEntity;
import com.siigroup.crud.services.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BookController.class)
class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        objectMapper = new ObjectMapper();
    }

    @Test
    void testGetAllBooks() throws Exception {
        // Arrange
        BookEntity book1 = new BookEntity(1L, "Book 1");
        BookEntity book2 = new BookEntity(2L, "Book 2");
        when(bookService.findAll()).thenReturn(Arrays.asList(book1, book2));

        // Act & Assert
        mockMvc.perform(get("/book/find"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Libros encontrados"))
                .andExpect(jsonPath("$.result").value(true))
                .andExpect(jsonPath("$.data[0].title").value("Book 1"))
                .andExpect(jsonPath("$.data[1].title").value("Book 2"));

        verify(bookService, times(1)).findAll();
    }

    @Test
    void testFindByIdOptional() throws Exception {
        // Arrange
        BookEntity book = new BookEntity(1L, "Test Book");
        when(bookService.findById(1L)).thenReturn(Optional.of(book));

        // Act & Assert
        mockMvc.perform(get("/book/find/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Libro encontrado"))
                .andExpect(jsonPath("$.result").value(true))
                .andExpect(jsonPath("$.data.title").value("Test Book"));

        verify(bookService, times(1)).findById(1L);
    }

    @Test
    void testCreateBook() throws Exception {
        // Arrange
        BookEntity book = new BookEntity(null, "New Book");
        BookEntity savedBook = new BookEntity(1L, "New Book");
        when(bookService.saveBook(any(BookEntity.class))).thenReturn(savedBook);

        // Act & Assert
        mockMvc.perform(post("/book/createBook")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(book)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Libro se actualiza"))
                .andExpect(jsonPath("$.result").value(true))
                .andExpect(jsonPath("$.data.title").value("New Book"));

        verify(bookService, times(1)).saveBook(any(BookEntity.class));
    }

    @Test
    void testUpdateBook() throws Exception {
        // Arrange
        BookEntity book = new BookEntity(1L, "Updated Book");
        when(bookService.updateBook(any(BookEntity.class))).thenReturn(book);

        // Act & Assert
        mockMvc.perform(post("/book/updateBook")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(book)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Libro se actualiza"))
                .andExpect(jsonPath("$.result").value(true))
                .andExpect(jsonPath("$.data.title").value("Updated Book"));

        verify(bookService, times(1)).updateBook(any(BookEntity.class));
    }

    @Test
    void testDeleteBook() throws Exception {
        // Arrange
        doNothing().when(bookService).deleteBook(1L);

        // Act & Assert
        mockMvc.perform(get("/book/deleteBook/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Libro se elimina"))
                .andExpect(jsonPath("$.result").value(true));

        verify(bookService, times(1)).deleteBook(1L);
    }
}
