package com.example.hibernatesearch;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.apache.lucene.analysis.core.WhitespaceAnalyzer;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;
import org.hibernate.search.annotations.Store;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Indexed
@Entity
public class Author implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "authorId")
	private Integer authorId;

	@Analyzer(impl = WhitespaceAnalyzer.class)
	@Field(store = Store.NO, analyze = Analyze.YES)
	@Column(name = "authorName", length = 1000)
	private String authorName;
	
	@Column(name = "dateofbirth")
	private Date dateofbirth;
	
	@OneToOne(cascade = CascadeType.ALL)
	@IndexedEmbedded
	@JoinColumn(name = "officeAddressId", referencedColumnName = "officeAddressId")
	private OfficeAddress address;

	public OfficeAddress getAddress() {
		return address;
	}

	public void setAddress(OfficeAddress address) {
		this.address = address;
	}

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
