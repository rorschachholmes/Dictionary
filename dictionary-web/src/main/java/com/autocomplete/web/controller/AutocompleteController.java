package com.autocomplete.web.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.autocomplete.web.model.SuggestionInfo;
import com.autocomplete.web.service.AutocompleteService;

/**
 * Main Controller class for handling auto complete requests
 * @author Shubham
 *
 */
@Controller
public class AutocompleteController {


	@Autowired
	@Qualifier("autocompleteService")
	private AutocompleteService autocompleteService;

	@RequestMapping(method=RequestMethod.GET, value="/")
	public String getHomePage(){

		return "searchWord";
	}

	public static final String ERROR_MESSAGE = "Oops, error occurred !";

	@RequestMapping(method=RequestMethod.GET, value="/{word}/suggestions", produces="application/json")
	public @ResponseBody SuggestionInfo getAutoCompleteSuggestions(@PathVariable("word") String word, HttpServletRequest request, HttpServletResponse response) throws IOException, Exception{

		SuggestionInfo suggestionInfo = new SuggestionInfo();

		try {

			List<String> suggestions = autocompleteService.getAutoCompleteSuggestions(word);
			suggestionInfo.setSearchedWord(word);
			suggestionInfo.setSuggestions(suggestions);
			
		}

		catch(IOException ioe){

			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			response.getWriter().write(ERROR_MESSAGE);
			response.flushBuffer();
			return null;

		}

		catch(Exception e){

			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			response.getWriter().write(ERROR_MESSAGE);
			response.flushBuffer();
			return null;
			

		}

		return suggestionInfo;

	}



}
