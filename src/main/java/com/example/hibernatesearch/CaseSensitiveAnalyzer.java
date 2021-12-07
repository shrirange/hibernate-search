package com.example.hibernatesearch;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.core.LetterTokenizer;

public class CaseSensitiveAnalyzer extends Analyzer {

	 @Override
	  protected TokenStreamComponents createComponents(final String fieldName) {
	    return new TokenStreamComponents(new LetterTokenizer());
	  }

}
