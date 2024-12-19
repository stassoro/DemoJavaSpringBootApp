package org.mainBusinessApp;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mainBusinessApp.model.Book;
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
        String title = "testTitle";
        String author = "testAuthor";
        Book book = new Book(title,author);

        mockMvc.perform(post("/books")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(book)))
                .andExpect(status().isOk());

        List<Book> bookResp = booksRepository.findByAuthor(author);
        Assert.isTrue(!bookResp.isEmpty(), "book wasn't created");
        Assert.isTrue(bookResp.get(0).getTitle().equals(title),"resulted title isn't correct");
    }

    @Test
    void deleteBook() throws Exception {
        String title = "testTitleDelete";
        String author = "testAuthorDelete";
        Book book = new Book(title,author);

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
