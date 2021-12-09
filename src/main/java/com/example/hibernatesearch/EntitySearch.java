package com.example.hibernatesearch;

import javax.transaction.Transactional;

import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.query.dsl.BooleanJunction;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.Iterator;
import java.util.List;

import javax.persistence.*;

@Transactional
@Repository
@Component
public class EntitySearch {

	@PersistenceContext
	private EntityManager entityManager;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public SearchResult<Author> searchAuthor(Pageable page, String searchString, Boolean caseSensitive) {

		String[] tokenized = null;

		SearchResult<Author> searchResult = new SearchResult<>();
		FullTextEntityManager full = org.hibernate.search.jpa.Search.getFullTextEntityManager(entityManager);
		QueryBuilder queryBuilder = full.getSearchFactory().buildQueryBuilder().forEntity(Author.class).get();
		// String cleanedText = query.toString();
		tokenized = searchString.split("\\s");

		// org.apache.lucene.search.Query query =

		BooleanJunction<BooleanJunction> booleanJunction = queryBuilder.bool();

		if (tokenized.length > 1) {

			for (int i = 1; i < tokenized.length - 2; i++) {
				booleanJunction.must(queryBuilder.keyword().wildcard().onField("authorName")
						.matching(tokenized[tokenized.length - 1] + "*").createQuery());
			}

			booleanJunction.must(
					queryBuilder.keyword().wildcard().onField("authorName").matching("*" + tokenized[0]).createQuery());

			booleanJunction.must(queryBuilder.keyword().wildcard().onField("authorName")
					.matching(tokenized[tokenized.length - 1] + "*").createQuery());

		} else {
			System.out.println("Case sensitive " + caseSensitive);
			if (caseSensitive) {
				booleanJunction.must(queryBuilder.keyword().wildcard().onField("authorNameCaseSensitive")
						.matching("*" + searchString + "*").createQuery());
			} else {
				booleanJunction.must(queryBuilder.keyword().wildcard().onField("authorName")
						.matching("*" + searchString.toLowerCase() + "*").createQuery());
			}

		}
		org.apache.lucene.search.Query query = booleanJunction.createQuery();

		// .keyword().wildcard().onFields(new String[]{"authorName"}).matching("*" +
		// searchString + "*").createQuery();

		org.hibernate.search.jpa.FullTextQuery jpaQuery = full.createFullTextQuery(query, Author.class);

		List<Author> authors = jpaQuery.getResultList();
		searchResult.setResults(authors);
		return searchResult;

	}

}
