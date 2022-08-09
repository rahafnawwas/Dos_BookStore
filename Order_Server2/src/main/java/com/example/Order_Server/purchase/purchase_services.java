package com.example.Order_Server.purchase;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.Order_Server.exception.ProductNotfoundException;

@Service
public class purchase_services {

	private final RestTemplate restTemplate = new RestTemplate();

	public Book purchase_book(int id) {
		try {
		Book book =restTemplate.getForObject("http://192.168.56.1:8081/BookItem/"+id, Book.class);
		if(book.getStock()>0)
		{
			book.setStock(book.getStock()-1);
			 restTemplate.put("http://192.168.56.1:8081/Book/"+id+"/stock/dec/0",Book.class);
			 return book;
		}
		else return null;
	}
		catch (Exception e) {
			throw new ProductNotfoundException();

		}
	}

}
