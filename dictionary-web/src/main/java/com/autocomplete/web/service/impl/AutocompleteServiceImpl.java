package com.autocomplete.web.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.autocomplete.web.service.AutocompleteService;
import com.autocomplete.web.trie.TrieNode;
import com.autocomplete.web.trie.singleton.PreloadedDictionary;

/**
 * This class is responsible to find the matching suggestions for the input prefix
 * @author Shubham
 *
 */
public class AutocompleteServiceImpl implements AutocompleteService {

	private List<String> autoCompleteSuggestions;

	public List<String> getAutoCompleteSuggestions() {
		return this.autoCompleteSuggestions;
	}


	public void setAutoCompleteSuggestions(List<String> autoCompleteSuggestions) {
		this.autoCompleteSuggestions = autoCompleteSuggestions;
	}


	/**
	 * 
	 * Method to return all prefix-matching word suggestions
	 * 
	 * @param word
	 * @return {@link List} list of suggestions
	 * 
	 */
	@Override
	public List<String> getAutoCompleteSuggestions(String word)
			throws Exception {

		this.setAutoCompleteSuggestions(new ArrayList<String>());
		TrieNode<Character> currentNode = PreloadedDictionary.getPreloadedDictionary();
		String wordFormed = "";
		boolean isEmptyDictionary = false;
		char[] searchWordLetters = word.toCharArray();
		for(char letter : searchWordLetters) {

			if(currentNode.getSubNodeChildren(letter)==null){
				isEmptyDictionary=true;
				break;
			}

			else if(currentNode.getSubNodeChildren(letter).getContentVal()!=null &&
					currentNode.getSubNodeChildren(letter).getContentVal().equals(letter)) {
				currentNode = currentNode.getSubNodeChildren(letter);
				wordFormed+=String.valueOf(currentNode.getContentVal());
				if(wordFormed.equals(word)){
					break;
				}



			}

		}

		if(!isEmptyDictionary){

			if(currentNode.getChildren().size()==0){
				if(!autoCompleteSuggestions.contains(word)){
					autoCompleteSuggestions.add(wordFormed);
				}
			}
			else{
				for(TrieNode<Character> node : currentNode.getChildren()){
					findAllWordsWithPrefix(node,word);
				}
			}
		}

		Collections.shuffle(autoCompleteSuggestions);
		return getAutoCompleteSuggestions();
	}


	/**
	 * Recursive Helper method to find all matching words containing a common prefix
	 * and saving them
	 * 
	 * @param node
	 * @param word
	 * 
	 */
	private void findAllWordsWithPrefix(TrieNode<Character> node, String word){


		if(node.getChildren().size()>=0){
			word+=String.valueOf(node.getContentVal());
		}

		if(node.isLeaf() || node.getChildren().size()==0){
			if(!autoCompleteSuggestions.contains(word)){
				autoCompleteSuggestions.add(word);
			}
		}

		for(TrieNode<Character> trieNode : node.getChildren()){

			findAllWordsWithPrefix(trieNode,word);

		}


	}
}
