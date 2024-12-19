package org.mainBusinessApp.service;

import org.mainBusinessApp.model.Book;

import java.util.List;

public interface BookService {
    // Save operation
    Book saveBook(Book book);

    // Read operation
    List<Book> fetchBookList();

    // Update operation
    Book updateBook(Long bookId, Book book);

    // Delete operation
    void deleteBookById(Long bookId);

}
