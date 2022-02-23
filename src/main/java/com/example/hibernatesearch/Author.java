package com.example.hibernatesearch;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PostLoad;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Transient;

import org.apache.lucene.analysis.core.KeywordAnalyzer;
import org.apache.lucene.analysis.core.SimpleAnalyzer;
import org.apache.lucene.analysis.core.WhitespaceAnalyzer;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Fields;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Store;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Indexed
@Entity
public class Author implements Serializable {

	private static final long serialVersionUID = 1L;

	@Override
	public String toString() {
		return "Author [authorId=" + authorId + ", authorName=" + authorName + ", dateofbirth=" + dateofbirth + "]";
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "authorId")
	private Integer authorId;

	/*
	 * @Field(name = "authorNameCaseSensitive", store = Store.NO, analyze =
	 * Analyze.YES, analyzer = @Analyzer(impl = WhitespaceAnalyzer.class))
	 */
	@Fields({
			@Field(name = "authorNameCaseSensitive", store = Store.NO, analyze = Analyze.YES, analyzer = @Analyzer(impl = WhitespaceAnalyzer.class)),
			@Field(name = "authorName", store = Store.NO, analyze = Analyze.YES, analyzer = @Analyzer(impl = SimpleAnalyzer.class)) })
	@Column(name = "authorName", length = 1000)
	private String authorName;

	@Column(name = "dateofbirth")
	private Date dateofbirth;
	
	
	@Field(name = "test", store = Store.NO, analyze = Analyze.YES, analyzer = @Analyzer(impl = SimpleAnalyzer.class))
	@Column(name = "test", length = 1000)
	private String test;

	public String getTest() {
		return test;
	}

	public void setTest(String test) {
		this.test = test;
	}

	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "address_id", referencedColumnName = "id")
	@JsonManagedReference
	private Address address;

	public Integer getAuthorId() {
		return authorId;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public void setAuthorId(Integer authorId) {
		this.authorId = authorId;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public Date getDateofbirth() {
		return dateofbirth;
	}

	public void setDateofbirth(Date dateofbirth) {
		this.dateofbirth = dateofbirth;
	}
	
	@PrePersist
	@PreUpdate
    private void saveState(){
		if(address == null) {
			this.test = "PuneKothrud"; 
		}else
       this.test = address.getCity() + address.getAddress1(); 
    }


}
