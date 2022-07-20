package com.example.Catalog_Server.Book;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.Catalog_Server.Book.*;

public interface Book_JPA extends CrudRepository<Book, String> {

	@Query(value = "select * from book where topic = ?", nativeQuery = true)
	public List<Book> findByTopic(String topic);
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(value = "update book set price= :price where book_name= :book_name", nativeQuery = true)
	public void updateCost(@Param("price") int cost,@Param("book_name") String item_name);
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(value = "update book set stock=stock+1 where book_name= :book_name", nativeQuery = true)
	public void updateStockInc(@Param("book_name") String item_name);
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(value = "update book set stock=stock-1 where book_name= :book_name", nativeQuery = true)
	public void updateStockDec(@Param("book_name") String item_name);
	
	
	@Query(value = "select stock from book where book_name = ?", nativeQuery = true)
	public int findByStock(String book_name);
}
