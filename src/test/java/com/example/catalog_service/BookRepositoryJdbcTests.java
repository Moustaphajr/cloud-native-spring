package com.example.catalog_service;


import com.example.catalog_service.Config.DataConfig;
import com.example.catalog_service.Interfaces.BookRepository;
import com.example.catalog_service.domain.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.context.annotation.Import;
import org.springframework.data.jdbc.core.JdbcAggregateTemplate;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJdbcTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(DataConfig.class)
@ActiveProfiles("integration")
class BookRepositoryJdbcTests{

    @Autowired
    private BookRepository bookRepository;


    @Autowired
    private JdbcAggregateTemplate jdbcAggregateTemplate;



    @Test
   void  testFindBysIbn(){
        String bookIsbn = "1231231232";

        Book book = Book.of(bookIsbn, "Title", "Author", 9.90,"Me");

        jdbcAggregateTemplate.insert(book);

        Optional<Book> foundBook = bookRepository.findByIsbn(bookIsbn);

        assertThat(foundBook).isNotNull();
        assertThat(foundBook.get().isbn()).isEqualTo(bookIsbn);

    }





}