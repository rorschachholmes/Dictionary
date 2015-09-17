/*
 * 
 * Singleton Pattern to preload the dictionary for caching
 * 
 */

package com.autocomplete.web.trie.singleton;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import com.autocomplete.web.trie.TrieNode;

public class PreloadedDictionary implements Cloneable{
	
	private static TrieNode<Character> dictionary = null;
	
	private PreloadedDictionary() {}
	
	@Override
	protected Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException();
	}
	
	public static TrieNode<Character> getPreloadedDictionary(){
		
		if(dictionary==null){
			
			dictionary = buildPreloadedDictionary(dictionary);
		}
		
		return dictionary;
		
	}

	
	/*
	 * Method to build the preloaded dictionary
	 * If there is a database, we can read each record and insert it into
	 * the Trie data structure to build the dictionary
	 */
	private static TrieNode<Character> buildPreloadedDictionary(TrieNode<Character> dictionary){

		if(dictionary == null){
			try {
				
				File file = new File(PreloadedDictionary.class.getClassLoader().getResource("PreLoadedDictionary.txt").getFile());
				Scanner scanner = new Scanner(file);
				dictionary = new TrieNode<Character>('*');
				while (scanner.hasNextLine()) {
					String dictionaryWord = scanner.nextLine();

					TrieNode<Character> currentNode = dictionary;

					char [] dictionaryWordLetters = dictionaryWord.toCharArray();


					for(Character letter : dictionaryWordLetters) {

						TrieNode<Character> childNode = currentNode.getSubNodeChildren(letter);
						if(childNode!=null){

							currentNode = childNode;

						}

						else {

							List<TrieNode<Character>> children = currentNode.getChildren();
							children.add(new TrieNode<Character>(letter));

							currentNode = currentNode.getSubNodeChildren(letter);

						}

					}

					currentNode.setLeaf(true);
				}

				scanner.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return dictionary;
	}
}
