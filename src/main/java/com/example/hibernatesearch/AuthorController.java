package com.example.hibernatesearch;

import java.util.List;

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
	public SearchResult<Author> getAuthorSearchResults(@PathVariable("searchString") String searchString){
		System.out.println("searchString =" + searchString);
		Criteria criteria = new Criteria();
		criteria.setNot(false);
		criteria.setSearchString(searchString);
		criteria.setOperator("contains");
		return entitySearch.searchAuthor(null, searchString, criteria);
	}
	
	@PostMapping("searchWithConditions")
	public SearchResult<Author> getAuthorSearchResultsWithConditions(@RequestBody Criteria criteria){
		return entitySearch.searchAuthor(null, criteria.getSearchString(), criteria);
	}
	
	@GetMapping("test")
	public CriteriaCondition getCriteriaCondition() {
		Criteria criteria = new Criteria();
		criteria.setNot(false);
		criteria.setSearchString("searchString");
		criteria.setOperator("contains");
		CriteriaCondition cc = new CriteriaCondition();
		cc.setC1(BooleanCondition.OR);
		cc.getListofCriteria().add(criteria);
		return cc;
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