/*
 * 
 * Singleton Pattern to preload the dictionary for caching
 * 
 */

package com.autocomplete.standalone.trie.singleton;
import java.io.File;

import com.autocomplete.standalone.trie.TrieNode;

public class PreloadedDictionary implements Cloneable{
	
	private static TrieNode<Character> dictionary = null;
	
	private PreloadedDictionary() {}
	
	@Override
	protected Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException();
	}
	
	public static TrieNode<Character> getPreloadedDictionary(File file){
		
		if(dictionary==null){
			
			dictionary = TrieNode.getPreloadedDictionary(dictionary,file);
		}
		
		return dictionary;
		
	}

}
