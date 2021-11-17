package com.example.hibernatesearch;

import javax.transaction.Transactional;

import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;

import javax.persistence.*;

@Transactional
@Repository
@Component
public class EntitySearch {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@SuppressWarnings("unchecked")
	public SearchResult<Author> searchAuthor(Pageable page){
		SearchResult<Author> searchResult = new SearchResult<>();
		FullTextEntityManager full = org.hibernate.search.jpa.Search.getFullTextEntityManager(entityManager);
		QueryBuilder queryBuilder = full.getSearchFactory().buildQueryBuilder().forEntity(Author.class).get();
		org.apache.lucene.search.Query query = queryBuilder.keyword().wildcard().onField("authorName").matching("*a*").createQuery();
		org.hibernate.search.jpa.FullTextQuery jpaQuery = full.createFullTextQuery(query, Author.class);
		
		List<Author> authors = jpaQuery.getResultList();
		searchResult.setResults(authors);
		return searchResult;
		
	}

}
 
