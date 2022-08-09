package com.example.Catalog_Server.Book;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.Catalog_Server.Book.*;

public interface Book_JPA extends CrudRepository<Book, Integer> {

	@Query(value = "select * from book where topic = ?", nativeQuery = true)
	public List<Book> findByTopic(String topic);
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(value = "update book set price= :price where id= :id", nativeQuery = true)
	public void updateCost(@Param("price") int cost,@Param("id") int id);
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(value = "update book set stock=stock+1 where id= :id", nativeQuery = true)
	public void updateStockInc(@Param("id") int id);
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(value = "update book set stock=stock-1 where id= :id", nativeQuery = true)
	public void updateStockDec(@Param("id") int id);
	
	
	@Query(value = "select stock from book where id = ?", nativeQuery = true)
	public int findByStock(int id);
}
