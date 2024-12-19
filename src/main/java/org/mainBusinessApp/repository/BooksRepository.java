package org.mainBusinessApp.repository;

import org.mainBusinessApp.model.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BooksRepository extends CrudRepository<Book, Long> {

    List<Book> findByAuthor(String author);

}
