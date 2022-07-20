package com.example.front_end.front;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;




@Service
public class front_end_service {
	private final RestTemplate restTemplate = new RestTemplate();
	public List<Book> Search(String topic) {
		ResponseEntity<Book[]> response =
				  restTemplate.getForEntity("http://192.168.56.1:8080/BookTopic/"+topic,Book[].class);
		Book[] books= response.getBody();
		return Arrays.stream(books).collect(Collectors.toList());
	}

	public Book info(String item) {
		return restTemplate.getForObject("http://192.168.56.1:8080/BookItem/"+item, Book.class);
	}

	public String purchase(String item) {
	    ///////added
		Book book=restTemplate.getForObject("http://192.168.56.1:8080/BookItem/"+item, Book.class);
		String status;
		if(book.getStock()==0)
		{
			return "Does not exist";
		}
		////////end
		restTemplate.getForObject("http://192.168.56.103:8080/Book/purchase/"+item,Book.class);
		return "Done and the number of books become"+book.getStock();
	}

}
