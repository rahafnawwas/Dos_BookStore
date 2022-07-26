package com.example.Catalog_Server.Book;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Book")
public class Book {

	@Id
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE)
	private int id;
	@Column(name ="BookName")
	private String BookName;
	@Column(name ="price")
	private int price;
	@Column(name ="stock")
	private int stock;
	@Column(name ="topic")
	private String topic;
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
	
	public Book(String bookName, int price, int id) {
		super();
		BookName = bookName;
		this.price = price;
		this.id=id;
	
	}
	public String getBookName() {
		return BookName;
	}
	public void setBookName(String bookName) {
		BookName = bookName;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Override
	public String toString() {
		return "Book [BookName=" + BookName + ", price=" + price + ", stock=" + stock + ", topic=" + topic + "]";
	}
	public Book() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
