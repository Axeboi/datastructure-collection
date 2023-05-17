package org.example.trie;

public class AVLTreeNode<T>{
    AVLTreeNode parent = null;
    AVLTreeNode left = null;
    AVLTreeNode right = null;
    public Integer data;
    public Integer height = 1;

    AVLTreeNode() {}
    AVLTreeNode(AVLTreeNode parent, Integer data, Integer height) {
        this.parent = parent;
        this.data = data;
        this.height = height;
    }

    public Integer getNodeHeight(AVLTreeNode node) {
        return (node == null) ? -1 : node.height;
    }

    public void updateHeight(AVLTreeNode node) {

        node.height = 1 + Math.max(getNodeHeight(node.left), getNodeHeight(node.right));
    }

    public Integer getBalance(AVLTreeNode node) {
        return (node == null) ? 0 : getNodeHeight(node.left) - getNodeHeight(node.right);
    }


    public void insert(Integer data) {
        if (this.data > data) {
            if (left != null) {
                left.insert(data);
            } else {
                AVLTreeNode node = new AVLTreeNode(this, data, this.height + 1);
                this.left = node;
            }
        }

        if (this.data < data) {
            if (right != null) {
                right.insert(data);
            } else {
                AVLTreeNode node = new AVLTreeNode();
                node.parent = this;
                node.data = data;
                this.right = node;
            }
        }

        // Node already exist so there is no need to insert
        return;
    }

    public Integer find(Integer compare) {
        return data;
    }

    public void delete(T compare) {

    }
}
