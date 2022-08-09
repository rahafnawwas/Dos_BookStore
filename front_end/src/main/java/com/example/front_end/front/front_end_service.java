package com.example.front_end.front;

import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.front_end.Book.exception.copy.ProductNotfoundException;





@Service
public class front_end_service {
	Hashtable<Integer,Book> hm=new Hashtable<Integer,Book>();  
	private final RestTemplate restTemplate = new RestTemplate();
	static int flag=0;  // test for which replica to send 
	static int flag1=0;
	String server="localhost";
	static int port;
	void chooseReplicaCatalog()
	{
		if(flag==0)
		{
			port=8080;
			flag=1;
		}
		else {
			port=8081;
			flag=0;
		}
	}
	void chooseReplicaOrder()
	{
		if(flag1==0)
		{
			port=8082;
			flag1=1;
		}
		else {
			port=8083;
			flag1=0;
		}
	}
	public List<Book> Search(String topic) {
		chooseReplicaCatalog();
		try {
			ResponseEntity<Book[]> response =
					  restTemplate.getForEntity("http://192.168.56.1:"+port+"/BookTopic/"+topic,Book[].class);
			Book[] books= response.getBody();
			
			return Arrays.stream(books).collect(Collectors.toList());
		} catch (Exception e) {
			throw new ProductNotfoundException();

		}
		
	}

	public Book info(int item) {
		chooseReplicaCatalog();
		if(hm.containsKey(item))
		{
			return hm.get(item);
		}
		try {
			Book book=restTemplate.getForObject("http://192.168.56.1:"+port+"/BookItem/"+item, Book.class);
			hm.put(book.getId(), book);
			return book;
		} catch (Exception e) {
			throw new ProductNotfoundException();
		}
		
		}

	public String purchase(int item) {
	    ///////added
		Book book;
		try {
			if(hm.containsKey(item))
			{
				 book=hm.get(item);
			}
			else {
			chooseReplicaCatalog();
			 book=restTemplate.getForObject("http://192.168.56.1:"+port+"/BookItem/"+item, Book.class);
			}
			
			if(book.getStock()==0)
			{
				return "Does not exist";
			}
			////////end
			chooseReplicaOrder();
			restTemplate.getForObject("http://192.168.56.103:"+port+"/Book/purchase/"+item,Book.class);
			return "Done and the number of books become  "+(book.getStock()-1);
		} catch (Exception e) {
			throw new ProductNotfoundException();
		}
		
	}
	public void invalidBook(int id) {
		hm.remove(id);
		
	}

}
