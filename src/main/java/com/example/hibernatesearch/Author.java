package com.example.hibernatesearch;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PostLoad;
import javax.persistence.Transient;

import org.apache.lucene.analysis.custom.CustomAnalyzer;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
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
	
	@Analyzer()
	@Field(store = Store.NO, analyze = Analyze.YES)
	@Column(name = "authorName" , length = 1000)
	private String authorName; 
	
	@Column(name = "dateofbirth")
	private Date dateofbirth;
	
	public Integer getAuthorId() {
		return authorId;
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


}
