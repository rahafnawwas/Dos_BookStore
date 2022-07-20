package com.example.Order_Server.purchase;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class purchase_services {

	private final RestTemplate restTemplate = new RestTemplate();

	public Book purchase_book(String book_name) {
		Book book =restTemplate.getForObject("http://192.168.56.1:8080/BookItem/"+book_name, Book.class);
		if(book.getStock()>0)
		{
			book.setStock(book.getStock()-1);
			 restTemplate.put("http://192.168.56.1:8080/Book/"+book_name+"/stock/dec",Book.class);
			 return book;
		}
		else return null;
	}

}
