package org.example;

import org.example.AVLTree.AVLTree;
import org.example.AVLTree.Node;
import org.example.trie.AVLTreeNode;
import org.example.trie.TrieNode;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    public static void TrieSetupAndTest() throws Exception{
        // Open the file
        FileInputStream fstream = new FileInputStream("src/main/resources/words.txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

        String strLine;
        TrieNode root = new TrieNode();
        while ((strLine = br.readLine()) != null)   {
            root.insert(strLine);
        }

        //Close the input stream
        fstream.close();

        Scanner sc = new Scanner(System.in);
        System.out.println("Find words that start with :");
        String str = sc.nextLine();


        TrieNode node = root.findNode(str, 0); // Todo: should return optional
        if (node != null) {
            List<String> result = new ArrayList<>();
            result = node.getAllCombinations(result).stream().sorted(Comparator.comparingInt(String::length)).collect(Collectors.toList());
            System.out.println(result);
        } else {
            System.out.println("No result found");
        }
    }

    public static void AVLTreeSetupAndTest() {
//        AVLTree tree = new AVLTree();
//
//        tree.insert(10);
//        tree.insert(6);
//        tree.insert(12);
//        tree.insert(8);
//        tree.insert(14);
//        tree.insert(16);
//
//        //tree.delete(tree.root, 6);
//        System.out.println(tree.root);
        AVLDeleteTwoChildrenTest();
    }

    private static void AVLInsertTest() {
        AVLTree tree = new AVLTree();

        tree.insert(10);
        tree.insert(6);
        tree.insert(12);
        tree.insert(8);
        tree.insert(14);
        tree.insert(16);
    }

    private static void AVLDeleteNoChildrenTest() {

    }

    private static void AVLDeleteLeftChildTest() {

    }

    private static void AVLDeleteRightChildTest() {

    }

    private static void AVLDeleteTwoChildrenTest() {
        AVLTree tree = new AVLTree();

        tree.insert(10);
        tree.insert(20);
        tree.insert(30);
        tree.insert(26);
        tree.insert(24);

        tree.delete(tree.root, 30);

        assert(tree.root.right.right.data == 24);
        assert(tree.root.right.right.left.left == null);
    }
    public static void main(String[] args) throws Exception{
        AVLTreeSetupAndTest();
    }
}