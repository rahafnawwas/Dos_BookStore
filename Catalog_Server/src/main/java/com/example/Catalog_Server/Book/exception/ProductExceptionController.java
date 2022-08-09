package com.example.Catalog_Server.Book.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.Catalog_Server.Book.Book;

@ControllerAdvice
public class ProductExceptionController {
   @ExceptionHandler(value = ProductNotfoundException.class)
   public ResponseEntity<Book> exception(ProductNotfoundException exception) {
      return new ResponseEntity<Book>(HttpStatus.NOT_FOUND);
   }
}
