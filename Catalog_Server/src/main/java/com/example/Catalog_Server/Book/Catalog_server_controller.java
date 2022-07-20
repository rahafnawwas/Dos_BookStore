package com.example.Catalog_Server.Book;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Catalog_server_controller {

	@Autowired
	private BookServices bookService;
	
	@RequestMapping("/BookTopic/{topic}")
	public List<Book> GetByTopic(@PathVariable String topic)
	{
		return bookService.GetBookByTopic(topic);
		
	}
	
	@RequestMapping("/BookItem/{ItemName}")
	public Book GetByItem(@PathVariable String ItemName)
	{
		return (Book) bookService.GetBookByItem(ItemName);
		
	}
	@RequestMapping("/Book")
	public List<Book> GetAllBooks()
	{
		return bookService.GetBooks();
	}
	
	@RequestMapping(method = RequestMethod.POST,value = ("/addBook"))
	public void addBook(@RequestBody Book book)
	{
		bookService.addBooks(book);
	}
	@RequestMapping(method = RequestMethod.PUT,value="/Book/{itemName}/{infoToBeUpdate}/{value}")
	public void updateBookInfo(@PathVariable String itemName,@PathVariable String infoToBeUpdate,@PathVariable String value)
	{
		if(infoToBeUpdate.equalsIgnoreCase("cost"))
		{
		bookService.updateCost(itemName,Integer.parseInt(value));
		}
		else if(infoToBeUpdate.equalsIgnoreCase("stock"))
		{
			bookService.updateStock(itemName,value);
		}
	}

	
}
