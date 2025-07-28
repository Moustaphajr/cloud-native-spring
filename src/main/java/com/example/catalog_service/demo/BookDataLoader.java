package com.example.catalog_service.demo;


import com.example.catalog_service.Interfaces.BookRepository;
import com.example.catalog_service.domain.Book;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Profile("testData")
public class BookDataLoader {

    private final BookRepository bookRepository;

    public BookDataLoader(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    @EventListener(ApplicationReadyEvent.class)
    public void loadBookTestData(){
        var book=Book.of(
                "978-3-16-148410-0",
                "Effective Java",
                "Joshua Bloch",
                45.00,
                "Me"
        );
        var book2=Book.of(
                "978-0-13-468609-7",
                "Clean Code",
                "Robert C. Martin",
                40.00,
                "Me"
        );
        bookRepository.saveAll(List.of(book, book2));

    }
}
