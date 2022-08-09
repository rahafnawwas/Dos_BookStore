package com.example.front_end.Book.exception.copy;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.front_end.front.Book;


@ControllerAdvice
public class ProductExceptionController {
   @ExceptionHandler(value = ProductNotfoundException.class)
   public ResponseEntity<Book> exception(ProductNotfoundException exception) {
      return new ResponseEntity<Book>(HttpStatus.NOT_FOUND);
   }
}
