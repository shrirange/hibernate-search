package com.example.hibernatesearch;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.apache.lucene.analysis.core.WhitespaceAnalyzer;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Store;

@Indexed
@Entity
public class OfficeAddress implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "officeAddressId")
	private Integer officeAddressId;
	
	@Analyzer(impl = WhitespaceAnalyzer.class)
	@Field(store = Store.NO, analyze = Analyze.YES)
	@Column(name = "city", length = 1000)
	private String city;
	
	@OneToOne(mappedBy = "address")
	private Author author;

	public Integer getOfficeAddressId() {
		return officeAddressId;
	}

	public void setOfficeAddressId(Integer officeAddressId) {
		this.officeAddressId = officeAddressId;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}


}
