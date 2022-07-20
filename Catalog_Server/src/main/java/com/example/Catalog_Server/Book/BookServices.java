package com.example.Catalog_Server.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BookServices {

	@Autowired
	Book_JPA book_Data;
	public List<Book> GetBookByTopic(String topic) {
		return book_Data.findByTopic(topic);
	}

	public Book GetBookByItem(String itemName) {
		
		return book_Data.findById(itemName).get();
	
	}

	public List<Book> GetBooks() {
		List<Book> books = new ArrayList<Book>();
		book_Data.findAll().forEach(books::add);
		return books;
	}

	public void addBooks(Book book) {
		book_Data.save(book);
		
	}
	
	public void updateCost(String item_name,int cost)
	{
		book_Data.updateCost(cost, item_name);
		
	}
	
	public void updateStock(String item_name,String value)
	{
		if(value.equalsIgnoreCase("inc"))
		{
			book_Data.updateStockInc(item_name);
		}
		else if(value.equalsIgnoreCase("dec"))
		{
			int stock=book_Data.findByStock(item_name);
			if(stock!=0)
			{
				book_Data.updateStockDec(item_name);
			}
		}
	}


}
