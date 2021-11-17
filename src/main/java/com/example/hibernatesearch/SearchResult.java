package com.example.hibernatesearch;

import java.util.ArrayList;
import java.util.List;

public class SearchResult<T> {

	int totalRecords;
	List<T> results = new ArrayList<T>();

	public int getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(int totalRecords) {
		this.totalRecords = totalRecords;
	}

	public List<T> getResults() {
		return results;
	}

	public void setResults(List<T> results) {
		this.results = results;
	}

	SearchResult() {

	}

}
