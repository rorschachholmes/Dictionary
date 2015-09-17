package com.autocomplete.web.service;

import java.util.List;

/**
 * 
 * @author Shubham
 *
 */
public interface AutocompleteService {
	
	public List<String> getAutoCompleteSuggestions(String word) throws Exception;
	

}
