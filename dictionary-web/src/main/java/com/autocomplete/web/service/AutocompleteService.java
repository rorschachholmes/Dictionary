package com.autocomplete.web.service;

import java.util.List;

/**
 * 
 * Abstraction in Service layer for Autocomplete Service
 * 
 * @author Shubham
 *
 */
public interface AutocompleteService {
	
	public List<String> getAutoCompleteSuggestions(String word) throws Exception;
	

}
