# Dictionary
A trie (prefix-tree) based solution for an autocomplete search in a dictionary of words

There are actually two versions that I am thinking of coming up :

# Standalone Approach

In this case, there is a simple main function where it takes an input word from the console
and searches all the words in the dictionary that has a input as the prefix.

There is a PreLoadedDictionary.txt file which is being pre-loaded into a 
Trie data strucure or more specifically a prefix tree using the Singleton Design Pattern.

If you want to add more words, you can add them in the PreLoadedDictionary.txt and then test it.

# Web-App approach

The Web Version of the Dictionary Auto Complete feature contains only a search text box with an autocomplete feature
associated with it.

The search text box takes any random English word and fires an ajax request to fetch up the matching suggestions based
on the common prefix as denoted by the typed characters.

The application is tested against a Preloaded Dictionary of 150000 words of varied lengths.
