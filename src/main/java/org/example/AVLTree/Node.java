package org.example.AVLTree;

public class Node {
    public Node parent; // if null, this is the root
    public Node left;
    public Node right;
    public Integer data;
    public Integer height = 0;

    private static Integer count = 0;

    public Node() {}

    public Node(Integer data) {
        this.data = data;
        count++;
    }

    public Node(Node parent, Integer data) {
        this.parent = parent;
        this.data = data;
    };

//    public String toString() {
//        return count.toString();
//    }
}
