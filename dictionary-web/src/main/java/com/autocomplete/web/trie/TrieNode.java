package com.autocomplete.web.trie;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 * Realizing a Trie Data Structure to represent and build the dictionary
 * This TrieNode class would represent the entire data in the dictionary
 * @author Shubham
 * 
 */
public class TrieNode<T> implements Cloneable,Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6621562336961788408L;

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
