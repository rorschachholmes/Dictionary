

package com.autocomplete.standalone.trie;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * 
 * Realizing a Trie Data Structure to represent and build the dictionary
 * This TrieNode class would represent the entire data in the dictionary
 * 
 * Forcing the TrieNode to have a private constructor and no scope of setting
 * values to defeat instantiation and modification of the dictionary.
 * This is to achieve the truly Singleton Nature of the PreloadedDictionary class
 * 
 * 
 * @author Shubham
 */
public class TrieNode<T> implements Cloneable,Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7327552293861944013L;
	private static TrieNode<Character> root = new TrieNode<Character>('*');
	private T contentVal;

	private List<TrieNode<T>> children;

	private boolean isLeaf;

	private TrieNode (T contentVal) {

		this.contentVal = contentVal;
		this.children = new LinkedList<TrieNode<T>>();
		this.isLeaf = false;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException();
	}

	public TrieNode<T> getSubNodeChildren(T inputVal) {

		if(this.getChildren()!=null){

			for(TrieNode<T> childNode : children) {

				if(childNode.getContentVal().equals(inputVal)){
					return childNode;
				}

			}

		}

		return null;


	} 


	public T getContentVal() {
		return contentVal;
	}

	public List<TrieNode<T>> getChildren() {
		return children;
	}

	public boolean isLeaf() {
		return isLeaf;
	}

	private void setLeaf(boolean isLeaf) {
		this.isLeaf = isLeaf;
	}

	/**
	 * Final method to build the preloaded dictionary
	 * If there is a database, we can read each record and insert it into
	 * the Trie data structure to build the dictionary
	 * 
	 * @param dictionary
	 * @param file
	 * 
	 * 
	 */
	public static final TrieNode<Character> getPreloadedDictionary(TrieNode<Character> dictionary, File file){

		if(dictionary == null){
			try {
				Scanner scanner = new Scanner(file);
				while (scanner.hasNextLine()) {
					String dictionaryWord = scanner.nextLine();

					TrieNode<Character> currentNode = root;

					char [] dictionaryWordLetters = dictionaryWord.toCharArray();


					for(char letter : dictionaryWordLetters) {

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

		return root;
	}




}
