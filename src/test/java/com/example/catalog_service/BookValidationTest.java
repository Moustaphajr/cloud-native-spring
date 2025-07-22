package com.example.catalog_service;

import com.example.catalog_service.domain.Book;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class BookValidationTest {

    private static Validator validator;

    @BeforeAll
    static void setup(){
         ValidatorFactory factory =Validation.buildDefaultValidatorFactory();
         validator=factory.getValidator();
    }


    @Test
    void shouldValidateBookWithValidData() {
      Book book=new Book(
              "1234567890",
              "Effective Java",
              "Joshua Bloch",
              45.99
      );
      Set<ConstraintViolation<Book>> violations = validator.validate(book);
      assertThat(violations).isEmpty();
    }

    @Test
    void shouldNotValidateBookWithInvalidIsbn() {
        Book book = new Book(
                "12345", // Invalid ISBN
                "Effective Java",
                "Joshua Bloch",
                45.99
        );
        Set<ConstraintViolation<Book>> violations = validator.validate(book);
        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getMessage())
                .isEqualTo("The ISBN format must be valid.");
    }


}

