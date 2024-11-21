package com.siigroup.crud.services;

import com.siigroup.crud.entities.BookEntity;

import java.util.List;
import java.util.Optional;

/**
 * Servicio para manejar la lógica de negocio relacionada con los libros.
 */
public interface BookService{

    /**
     * Busca todos los libros.
     *
     * @return Lista todos los libros que existen.
     */
    List<BookEntity> findAll();

    /**
     * Busca un libro por su ID.
     *
     * @param id Identificador único del libro.
     * @return Un {@link Optional} que contiene el libro si existe.
     */
    Optional<BookEntity> findById(Long id);

    /**
     * Guarda un libro en el sitema.
     *
     * @param book Libro que se guardará.
     * @return Book Libro guardado.
     */
    BookEntity saveBook(BookEntity book);

    /**
     * Actualiza un libro en el sitema.
     *
     * @param book Libro que se guardará.
     * @return Book Libro actualizado.
     */
    BookEntity updateBook(BookEntity book);

    /**
     * elimina un libro en el sitema.
     *
     * @param id del libro que se eliminará.
     */
    void deleteBook(Long id);
}
