package com.example.front_end.front;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Book")
public class Book {

	@Id
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

	
	@Override
	public String toString() {
		return "Book [BookName=" + BookName + ", price=" + price + ", stock=" + stock + ", topic=" + topic + "]";
	}
	public Book() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
