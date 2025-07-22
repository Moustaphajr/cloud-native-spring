package com.example.catalog_service;

import com.example.catalog_service.domain.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

    @JsonTest
   public class BookJsonTests {
        @Autowired
        private JacksonTester<Book> json;

        @Test
        void testSerialize() throws Exception {
            var book = new Book("1234567890", "Title", "Author", 9.90);
            var jsonContent = json.write(book);
            assertThat(jsonContent).extracting("@.isbn")
                    .isEqualTo(book.isbn());
            assertThat(jsonContent).extracting("@.title")
                    .isEqualTo(book.title());
            assertThat(jsonContent).extracting("@.author")
                    .isEqualTo(book.author());
            assertThat(jsonContent).extracting("@.price")
                    .isEqualTo(book.price());
        }

        @Test
        void testDeserialize() throws Exception {
            var content = """
                    {
                    "isbn": "1234567890",
                    "title": "Title",
                    "author": "Author",
                    "price": 9.90
                    }
                    """;
            assertThat(json.parse(content))
                    .usingRecursiveComparison()
                    .isEqualTo(new Book("1234567890", "Title", "Author", 9.90));
        }
    }


