package org.mainBusinessApp;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mainBusinessApp.model.Book;
import org.mainBusinessApp.model.Customer;
import org.mainBusinessApp.repository.BooksRepository;
import org.mainBusinessApp.repository.CustomersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.Assert;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
public class SpringBootRestApiApplicationTests {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private BooksRepository booksRepository;
    @Autowired
    private CustomersRepository customersRepository;

    @Test
    void createNewBook() throws Exception {
        String author = "testAuthor";
        String title = "testTitle";
        Book book = new Book();
        book.setAuthor(author);
        book.setTitle(title);
        mockMvc.perform(post("/books")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(book)))
                .andExpect(status().isOk());

        List<Book> bookResp = booksRepository.findByAuthor(author);
        Assert.isTrue(!bookResp.isEmpty(), "book wasn't created");
        Assert.isTrue(bookResp.get(0).getTitle().equals(title),"resulted title isn't correct");
    }
    @Test
    void createNewCustomer() throws Exception {
        String name = "Tester Test";
        String email = "tester.test@tests.com";
        Customer customer = new Customer();
        customer.setName(name);
        customer.setEmail(email);
        mockMvc.perform(post("/customers")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(customer)))
                .andExpect(status().isOk());

        List<Customer> customerResp = customersRepository.findByName(name);
        Assert.isTrue(!customerResp.isEmpty(), "customer wasn't created");
        Assert.isTrue(customerResp.get(0).getName().equals(name),"resulted email isn't correct");
    }

    @Test
    void deleteBook() throws Exception {
        String title = "testTitleDelete";
        String author = "testAuthorDelete";
        Book book = new Book();
        book.setAuthor(author);
        book.setTitle(title);

        mockMvc.perform(post("/books")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(book)))
                .andExpect(status().isOk());

        List<Book> bookCreateResp = booksRepository.findByAuthor(author);
        Assert.isTrue(!bookCreateResp.isEmpty(), "book wasn't created");

        long bookId = bookCreateResp.get(0).getId();
        mockMvc.perform(delete("/books/{id}",bookId)
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(book)))
                .andExpect(status().isOk());

        List<Book> bookDeleteResp = booksRepository.findByAuthor(author);
        Assert.isTrue(bookDeleteResp.isEmpty(), "book wasn't deleted");
    }
}
