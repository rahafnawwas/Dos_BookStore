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
	
	@RequestMapping("/BookItem/{id}")
	public Book GetByItem(@PathVariable int id)
	{
		return (Book) bookService.GetBookByItem(id);
		
	}
	@RequestMapping("/Book")
	public List<Book> GetAllBooks()
	{
		return bookService.GetBooks();
	}
	
	@RequestMapping(method = RequestMethod.POST,value = ("/addBook/{calledFrom}"))
	public void addBook(@RequestBody Book book,@PathVariable int calledFrom)
	{
		bookService.addBooks(book,calledFrom);
	}
	@RequestMapping(method = RequestMethod.PUT,value="/Book/{id}/{infoToBeUpdate}/{value}/{calledFrom}")
	public void updateBookInfo(@PathVariable int id,@PathVariable String infoToBeUpdate,@PathVariable String value,@PathVariable int calledFrom)
	{
		if(infoToBeUpdate.equalsIgnoreCase("cost"))
		{
		bookService.updateCost(id,Integer.parseInt(value),calledFrom);
		}
		else if(infoToBeUpdate.equalsIgnoreCase("stock"))
		{
			bookService.updateStock(id,value,calledFrom);
		}
	}

	
}
