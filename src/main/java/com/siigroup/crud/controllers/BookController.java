package com.siigroup.crud.controllers;

import com.siigroup.crud.dtos.ResponseDTO;
import com.siigroup.crud.entities.BookEntity;
import com.siigroup.crud.services.BookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * REST para gestionar los libros.
 */
@RestController
@RequestMapping("/book")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    /**
     * Busca todos los libros.
     *
     * @return lista de books.
     */
    @GetMapping("/find")
    public ResponseDTO<List<BookEntity>> getAllBooks(){
        try{
            List<BookEntity> books = bookService.findAll();
            if (!books.isEmpty()) {
                return new ResponseDTO<>("Libros encontrados",true,books);
            }else{
                return new ResponseDTO<>("No hay libros",false,null);
            }
        }catch(Exception e){
            return new ResponseDTO<>(e.getLocalizedMessage(),false,null);
        }
    }

    /**
     * Busca libro por Id.
     *
     * @param id del libro.
     * @return book encontrado.
     */

    @GetMapping("/find/{id}")
    public ResponseDTO<Optional<BookEntity>> findByIdOptional(@PathVariable Long id) {
        try{
            Optional<BookEntity> book = bookService.findById(id);
            if (book.isPresent()) {
                return new ResponseDTO<>("Libro encontrado",true,book);
            }else{
                return new ResponseDTO<>("Libro no encontrado",false,null);
            }
        }catch(Exception e){
            return new ResponseDTO<>(e.getLocalizedMessage(),false,null);
        }
    }


    /**
     * Crea un nuevo libro.
     *
     * @param book Libro a crear.
     * @return book creado.
     */
    @PostMapping("/createBook")
    public ResponseDTO<BookEntity> createBook(@RequestBody BookEntity book) {
        try {
            return new ResponseDTO<>("Libro se crea",true,bookService.saveBook(book));
        }catch (Exception e) {
            return new ResponseDTO<>("Libro no se crea",false,null);
        }
    }

    /**
     * Edita un libro.
     *
     * @param book Libro a editar.
     * @return book editado.
     */
    @PostMapping("/updateBook")
    public ResponseDTO<BookEntity> editBook(@RequestBody BookEntity book) {
        try {
            return new ResponseDTO<>("Libro se actualiza",true,bookService.updateBook(book));
        }catch (Exception e) {
            return new ResponseDTO<>("Libro no existe",false,null);
        }
    }

    /**
     * Elimina un libro.
     *
     * @param id de libro a eliminar.
     * @return boolean si se elimina o no.
     */
    @GetMapping("/deleteBook/{id}")
    public ResponseDTO<BookEntity> deleteBook(@PathVariable Long id) {
        try {
            bookService.deleteBook(id);
            return new ResponseDTO<>("Libro se eliminó",true,null);
        }catch (Exception e) {
            return new ResponseDTO<>("Libro no existe",false,null);
        }
    }
}
