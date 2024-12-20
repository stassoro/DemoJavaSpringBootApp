package org.mainBusinessApp.service;

import org.mainBusinessApp.model.Book;
import org.mainBusinessApp.repository.BooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BooksRepository booksRepository;

    @Override
    public Book saveBook(Book book) {
        return booksRepository.save(book);
    }

    @Override
    public List<Book> fetchBookList() {
        return (List<Book>) booksRepository.findAll();
    }

    @Override
    public Book updateBook(Long bookId, Book book) {
        Book bookDB;
        if (booksRepository.findById(bookId).isPresent()) {
            bookDB = booksRepository.findById(bookId).get();
        }
        else {
            return null;
//            throw new NoSuchElementException("bookId "+bookId+" was not found");
        }

        if (Objects.nonNull(book.getTitle()) && !"".equalsIgnoreCase(book.getTitle())) {
            bookDB.setTitle(book.getTitle());
        }

        if (Objects.nonNull(book.getAuthor()) && !"".equalsIgnoreCase(book.getAuthor())) {
            bookDB.setAuthor(book.getAuthor());
        }

        return booksRepository.save(bookDB);
    }

    @Override
    public void deleteBookById(Long bookId) {
        booksRepository.deleteById(bookId);
    }
}
