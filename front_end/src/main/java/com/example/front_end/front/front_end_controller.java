package com.example.front_end.front;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class front_end_controller {
    
	
	
	@Autowired
	front_end_service frontService;
	
	
	@RequestMapping(value = "/search/{topic}")
	public List<Book> search(@PathVariable String topic)
	{
		return frontService.Search(topic);
	}
	
	@RequestMapping(value = "/info/{item}")
	public Book info(@PathVariable int item)
	{
		return frontService.info(item);
	}
	
	@RequestMapping(value = "/BookIDinvalid/{id}")
	public void invalidData(@PathVariable int id)
	{
		frontService.invalidBook(id);
	}
	
	@RequestMapping(method =RequestMethod.PUT ,value = "/purchase/{item}")
	public String purchase(@PathVariable int item)
	{
		return frontService.purchase(item);
	}
}
