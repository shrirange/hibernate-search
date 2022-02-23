package com.example.hibernatesearch;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.apache.lucene.analysis.core.KeywordAnalyzer;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Store;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "address")
public class Address {

    public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    
	@Field(name = "city", store = Store.NO, analyze = Analyze.YES, analyzer = @Analyzer(impl = KeywordAnalyzer.class)) 
    @Column(name = "city")
    private String city;
    
	@Field(name = "address1", store = Store.NO, analyze = Analyze.YES, analyzer = @Analyzer(impl = KeywordAnalyzer.class)) 
    @Column(name = "address1")
    private String address1;
    
    @OneToOne(mappedBy = "address")
    @JoinColumn(name = "author_id")
    @JsonBackReference
    private Author author;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

}