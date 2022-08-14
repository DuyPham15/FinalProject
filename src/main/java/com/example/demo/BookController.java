package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("books")
public class BookController {
	
	@RequestMapping(value ="/", method = RequestMethod.GET)
	public String getBookIndex() {
		return "books";
	}
}
