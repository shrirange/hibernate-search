package com.example.hibernatesearch;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/author")
public class AuthorController {
	
	@Autowired
	private AuthorRepository authorRepository; 
	
	@Autowired
	private EntitySearch entitySearch;
	
	@GetMapping("list")
	public List<Author> getAuthor(){
		return authorRepository.findAll();
	}
	
	@GetMapping("search/{searchString}")
	public SearchResult<Author> getAuthorSearchResults(@PathVariable("searchString") String searchString,
			@PathParam("caseSensitive") Boolean caseSensitive){
		System.out.println("searchString =" + searchString);
		return entitySearch.searchAuthor(null, searchString, caseSensitive);
	}
	
	@PostMapping("create")
	public Author createAuthor(@RequestBody Author author){
		return authorRepository.save(author);
	}
	
	@PostMapping("update")
	public Author updateAuthor(@RequestBody Author author){
		return authorRepository.save(author);
	}
	
	

}