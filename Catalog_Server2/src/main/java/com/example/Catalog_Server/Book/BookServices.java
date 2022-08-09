package com.example.Catalog_Server.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.Catalog_Server.Book.exception.ProductNotfoundException;

@Service
public class BookServices {
	private final RestTemplate restTemplate = new RestTemplate();
	@Autowired
	Book_JPA book_Data;
	public List<Book> GetBookByTopic(String topic) {
		try {
		return book_Data.findByTopic(topic);
		}catch (Exception e) {
			throw new ProductNotfoundException();

		}
	}

	public Book GetBookByItem(int itemName) {
		
		try {
			return book_Data.findById(itemName).get();
		} catch (Exception e) {
			throw new ProductNotfoundException();

		}
	
	}

	public List<Book> GetBooks() {
		List<Book> books = new ArrayList<Book>();
		book_Data.findAll().forEach(books::add);
		return books;
	}

	public void addBooks(Book book,int calledFrom) {
		book_Data.save(book);
		if(calledFrom==0)
			restTemplate.postForObject("http://localhost:"+8080+"/addBook/1",book,Book.class);
		
	}
	//calledFrom if 0 means i have to send to replica but if 1 so im in replica
	public void updateCost(int id,int cost,int calledFrom)
	{
		try {
		book_Data.updateCost(cost, id);
		restTemplate.getForObject("http://192.168.56.104:8084/BookIDinvalid/"+id, Book.class);
		if(calledFrom==0)
	     restTemplate.put("http://192.168.56.1:"+8080+"/Book/"+id+"/cost/"+cost+"/1",Book.class);
		}catch (Exception e) {
			throw new ProductNotfoundException();

		}
	}
	
	
	public void updateStock(int id,String value, int calledFrom)
	{
		try {
		if(value.equalsIgnoreCase("inc"))
		{
			book_Data.updateStockInc(id);
			restTemplate.getForObject("http://192.168.56.104:8084/BookIDinvalid/"+id, Book.class);
			if(calledFrom==0)
			    restTemplate.put("http://192.168.56.1:"+8080+"/Book/"+id+"/stock/"+value+"/1",Book.class);

		}
		else if(value.equalsIgnoreCase("dec"))
		{
			int stock=book_Data.findByStock(id);
			if(stock!=0)
			{
				book_Data.updateStockDec(id);
				restTemplate.getForObject("http://192.168.56.104:8084/BookIDinvalid/"+id, Book.class);
				if(calledFrom==0)
				    restTemplate.put("http://192.168.56.1:"+8080+"/Book/"+id+"/stock/"+value+"/1",Book.class);

			}
		}
		}catch (Exception e) {
			throw new ProductNotfoundException();

		}
	}


}
