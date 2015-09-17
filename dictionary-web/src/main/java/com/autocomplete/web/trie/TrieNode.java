/*
 * 
 * Realizing a Trie Data Structure to represent and build the dictionary
 */

package com.autocomplete.web.trie;

import java.util.LinkedList;
import java.util.List;

/*
 * Forcing the TrieNode to have a private constructor and no scope of setting
 * values to defeat instantiation and modification of the dictionary.
 * This is to achieve the truly Singleton Nature of the PreloadedDictionary class
 * 
 */
public class TrieNode<T> {

	private T contentVal;

	private List<TrieNode<T>> children;

	private boolean isLeaf;

	public TrieNode (T contentVal) {

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

	public void setLeaf(boolean isLeaf) {
		this.isLeaf = isLeaf;
	}






}
