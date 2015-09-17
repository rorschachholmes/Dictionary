package com.autocomplete.web.model;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This is the model class that is used as the data to the auto complete view
 * The model state is serialized to a JSON while being sent to the view
 * @author Shubham
 *
 */
@XmlRootElement(name="suggestionResults")
public class SuggestionInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4973216978986368730L;
	
	
	@XmlElement(name="searchedWord")
    private String searchedWord;
	
	@XmlElement(name="suggestions")
	private List<String> suggestions;

	public String getSearchedWord() {
		return searchedWord;
	}

	public void setSearchedWord(String searchedWord) {
		this.searchedWord = searchedWord;
	}

	public List<String> getSuggestions() {
		return suggestions;
	}

	public void setSuggestions(List<String> suggestions) {
		this.suggestions = suggestions;
	}
	
	
	

}
