

package com.autocomplete.standalone.trie.app;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import com.autocomplete.standalone.trie.TrieNode;
import com.autocomplete.standalone.trie.singleton.PreloadedDictionary;
import com.autocomplete.standalone.trie.utils.TrieDictionaryUtils;


/**
 * Main Interfacing class to test the autocomplete functionality
 * 
 * This can be done using a web-app but for simplicity
 * it is demonstrated using the main function 
 * 
 * @author Shubham
 * 
 */

public class DictionaryApp {

	public static void main(String[] args) {

		TrieDictionaryUtils trieDictionaryUtils = new TrieDictionaryUtils();
		ClassLoader classLoader = DictionaryApp.class.getClassLoader();
		File file = new File(classLoader.getResource("PreLoadedDictionary.txt").getFile());

		TrieNode<Character> dictionary = PreloadedDictionary.getPreloadedDictionary(file);
		String searchMoreWords = "Y";
		while(searchMoreWords.equalsIgnoreCase("Y")){
			
			try {
				trieDictionaryUtils.setCountOfMatchedWords(0);
				System.out.print("Search a word in the dictionary : ");
				BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				String prefix=br.readLine();
				System.out.println();
				trieDictionaryUtils.searchMatchedWordsInDictionary(dictionary,prefix);
				System.out.print("Search more words ? (Y/N) : ");
				br = new BufferedReader(new InputStreamReader(System.in));
				searchMoreWords = br.readLine();
			}

			catch(IOException ioe){
				System.out.println("Oops, error occurred !");
			}

		}
		
		System.out.println("You didn't say yes. Hope you found what you searched for.");

	}

}
