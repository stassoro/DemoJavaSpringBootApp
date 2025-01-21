package org.mainBusinessApp.controller;

import lombok.extern.slf4j.Slf4j;
import org.mainBusinessApp.model.Customer;
import org.mainBusinessApp.model.Book;
import org.mainBusinessApp.service.BookService;
import org.mainBusinessApp.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
public class ApiController {

    @Autowired private BookService bookService;
    @Autowired private CustomerService customerService;

    @PostMapping("/books")
    public Book createBook(@RequestBody Book book) {
        log.info("created new book");
        return bookService.saveBook(book);
    }
    @GetMapping("/books")
    public List<Book> getBooks() {
        return bookService.fetchBookList();
    }
    @GetMapping("/books/{id}")
    public Book getBookById(@PathVariable("id") Long id) {
        List<Book> foundBooks = bookService.fetchBookList().stream().filter(it -> it.getId().equals(id)).toList();
        if (foundBooks.size() != 1)
            return null;
        else
            return foundBooks.get(0);
    }
    @DeleteMapping("/books/{id}")
    public void deleteBookById(@PathVariable("id") Long id) {
        bookService.deleteBookById(id);
    }

    @PostMapping("/customers")
    public Customer createCustomer(@RequestBody Customer customer) {
        log.info("creating new customer");
        return customerService.saveCustomer(customer);
    }
    @GetMapping("/customers")
    public List<Customer> getCustomers() {
        return customerService.fetchCustomerList();
    }
    @GetMapping("/customers/{id}")
    public Customer getCustomerById(@PathVariable("id") Long id) {
        List<Customer> foundCustomers = customerService.fetchCustomerList().stream().filter(it -> it.getId().equals(id)).toList();
        if (foundCustomers.size() != 1)
            return null;
        else
            return foundCustomers.get(0);
    }

}
