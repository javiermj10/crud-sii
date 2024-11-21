package com.siigroup.crud.services.impl;

import com.siigroup.crud.entities.BookEntity;
import com.siigroup.crud.repositories.BookRepository;
import com.siigroup.crud.services.BookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Implementación del servicio {@link BookService}.
 */
@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    /**
     * Constructor del servicio.
     *
     * @param bookRepository El repositorio de libros.
     */
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    public List<BookEntity> findAll() {
        return bookRepository.findAll();
    }

    public Optional<BookEntity> findById(Long id) {
        return bookRepository.findById(id);
    }

    public BookEntity saveBook(BookEntity book) {
        return bookRepository.save(book);
    }

    @Override
    public BookEntity updateBook(BookEntity book) {
        bookRepository.findById(book.getId()).orElseThrow(() ->
                new IllegalArgumentException("El libro no existe.")
        );
        return bookRepository.save(book);
    }

    public void deleteBook(Long id) {
        bookRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("El libro no existe.")
        );
        bookRepository.deleteById(id);
    }
}
