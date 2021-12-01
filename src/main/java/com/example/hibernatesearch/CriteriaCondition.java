package com.example.hibernatesearch;

import java.util.ArrayList;
import java.util.List;

public class CriteriaCondition {
	
	private BooleanCondition c1; 
	
	private List<Criteria> listofCriteria = new ArrayList<Criteria>();

	public BooleanCondition getC1() {
		return c1;
	}

	public void setC1(BooleanCondition c1) {
		this.c1 = c1;
	}

	public List<Criteria> getListofCriteria() {
		return listofCriteria;
	}

	public void setListofCriteria(List<Criteria> listofCriteria) {
		this.listofCriteria = listofCriteria;
	}


}
