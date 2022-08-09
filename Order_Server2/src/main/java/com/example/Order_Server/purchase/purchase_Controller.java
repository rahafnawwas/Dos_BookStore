 package com.example.Order_Server.purchase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class purchase_Controller {

	@Autowired
	purchase_services ps;
	@RequestMapping(method = RequestMethod.GET,value = "/Book/purchase/{id}")
	public Book PurchaseItem(@PathVariable int id)
	{
		return ps.purchase_book(id);
	}
}
