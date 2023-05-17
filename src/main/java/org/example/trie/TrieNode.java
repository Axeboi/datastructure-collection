package org.example.trie;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TrieNode {
    List<TrieNode> children = new ArrayList<>();
    TrieNode parent;
    Character data;
    boolean terminal = false;

    public TrieNode() {}
    public TrieNode(TrieNode parent, char data) {
        this.parent = parent;
        this.data = data;
    }

    public void insert(String word) {
        insert(word, 0);
    }

    public void insert(String word, Integer wordIndex) {
        if (word.length() == wordIndex) return;

        // If char exist in children, continue recursion and return.
        for (var child: children) {
            if (child.data == word.charAt(wordIndex)) {
                child.insert(word, wordIndex + 1);
                return;
            }
        }

        // No node with the right char found in children so create new
        TrieNode node = new TrieNode(this, word.charAt(wordIndex));
        children.add(node);

        // if at the end of the string, mark the node as the end of a word, else continue recursion
        if (wordIndex == word.length() - 1) {
            node.terminal = true;
        } else {
            node.insert(word, wordIndex + 1);
        }
    }

    public TrieNode findNode(String word, Integer wordIndex) {
        if (word.length() == wordIndex) {
            return this;
        }

        for (var child: children) {
            if (child.data == word.charAt(wordIndex)) {
                return child.findNode(word, wordIndex + 1);
            }
        }

        return null;
    }

    public List<TrieNode> getParents(List<TrieNode> parents) {
        for (var child: children) {
            if (child.terminal) {
                parents.add(child);
                if (child.children.size() != 0) {
                    child.getParents(parents);
                }
            } else {
                child.getParents(parents);
            }
        }

        return parents;
    }

    public List<String> getAllCombinations(List<String> result) {
        List<TrieNode> parents = getParents(new ArrayList<>());

        for (var parent: parents) {
            String word = "";
            while (parent != null && parent.data != null) {
                word += parent.data;
                parent = parent.parent;
            }

            StringBuilder output = new StringBuilder(word).reverse();

            result.add(output.toString());
        }

        return result;
    }

    public String toString() {
        String ret = "";

        if (data != null) {
            ret = data.toString();
        }

        if (children.size() == 0) {
            ret = ret + " | ";
        }

        for (var child: children) {
            ret = ret + child.toString();
        }

        return ret;
    }
}
