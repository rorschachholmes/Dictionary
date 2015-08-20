/*
 * 
 * Dictionary Utilities class for building and 
 * searching common prefix words in the dictionary
 */

package com.autocomplete.standalone.trie.utils;

import com.autocomplete.standalone.trie.TrieNode;

public class TrieDictionaryUtils {

	private int countOfMatchedWords = 0;

	/*
	 * Method that prints all the matching words in the dictionary
	 * as per the input prefix
	 * 
	 */

	public void searchMatchedWordsInDictionary(TrieNode<Character> root, String word) {
		
		if(root==null){
			
			System.out.println("Can't search for '"+word+"' since you deleted the dictionary !");
			System.exit(0);
			
		}

		TrieNode<Character> currentNode = root;
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

		System.out.print("\n\n**********************************\n\n");
		if(!isEmptyDictionary){

			if(currentNode.getChildren().size()==0){
				System.out.println(wordFormed);
				countOfMatchedWords++;
			}
			else{
				for(TrieNode<Character> node : currentNode.getChildren()){
					findAllWordsWithPrefix(node,word);
				}
			}
		}
		if(countOfMatchedWords==0){
			System.out.print("No matching records found");


		}
		else {
			System.out.print(countOfMatchedWords+" matching word/s found");
			System.out.println();
		}
		System.out.print("\n\n**********************************\n\n");



	}

	/*
	 * Recursive Helper method to find all matching words containing a common prefix
	 * and printing them
	 */
	private void findAllWordsWithPrefix(TrieNode<Character> node, String word){


		if(node.getChildren().size()>=0){
			word+=String.valueOf(node.getContentVal());
		}

		if(node.isLeaf() || node.getChildren().size()==0){
			System.out.println(word);
			countOfMatchedWords++;
		}

		for(TrieNode<Character> trieNode : node.getChildren()){

			findAllWordsWithPrefix(trieNode,word);

		}


	}


	public int getCountOfMatchedWords() {
		return countOfMatchedWords;
	}


	public void setCountOfMatchedWords(int countOfMatchedWords) {
		this.countOfMatchedWords = countOfMatchedWords;
	}





}





