package com.siigroup.crud.repositories;

import com.siigroup.crud.entities.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio para realizar operaciones CRUD sobre la entidad {@link BookEntity}.
 */
@Repository
public interface BookRepository extends JpaRepository<BookEntity,Long>, CrudRepository<BookEntity,Long> {

}
