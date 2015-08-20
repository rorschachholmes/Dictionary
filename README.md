# Dictionary
A trie based solution for an autocomplete search in a dictionary of words

There are actually two versions that I am thinking of coming up :

# Standalone Approach

In this case, there is a simple main function where it takes an input word from the console
and searches all the words in the dictionary that has a input as the prefix.

There is a PreLoadedDictionary.txt file which is being pre-loaded into a 
trie data strucure or more specifically a prefix tree using the Singleton Design Pattern.

If you want to add more words, you can add them in the PreLoadedDictionary.txt and then test it.

# Web-App approach

In this case, I am thinking of a simple UI having a text box with an autocomplete feature

I would be looking to use any dictionary APIs online and preload the dictionary
just as in the Standalone approach and whenever user enters any word in the search box 
an ajax call will be fired to the backend service fetching up the autocomplete suggestions

There will be a provision to Search a Word in the Dictionary and if the word is not there

a user can add the word into the dictionary with a proper meaning/definition of the word

This is a TODO for now. In progress.
