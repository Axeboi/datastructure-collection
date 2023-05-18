package org.example.AVLTree;

public class AVLTree {
    public Node root;

    public Integer updateHeight(Node node) {
        if (node == null) return 0;
        node.height = Math.max(updateHeight(node.left), updateHeight(node.right)) + 1;
        return node.height;
    }

    public Integer height(Node node) {
        return node == null ? 0 : node.height;
    }

    public Integer getBalanceFactor(Node node) {
        if (node == null) return 0;
        System.out.println("BALANCE FACTOR :" + (height(node.right) - height(node.left)) + " " + node.height);
        return height(node.right) - height(node.left);
    }

    public void insert(Integer data) {
         root = insert(root, data);
    }

    public Node insert(Node node, Integer data) {
        if (node == null) {
            return new Node(data);
        }

        else if (data < node.data) {
            node.left = insert(node.left, data);
            node.left.parent = node;
        }
        else if (data > node.data) {
            node.right = insert(node.right, data);
            node.right.parent = node;
        }
        else {
            throw new RuntimeException("duplicate Key!");
        }

//        updateHeight(node);
//        var balance = getBalanceFactor(node);
//
//        if (node != null) {
//            if (balance < -1) {
//                if (height(node.left.right) > height(node.left.left)) {
//                    node.left = rotateLeft(node.left);
//                    return rotateRight(node);
//                } else {
//                    return rotateRight(node);
//                }
//                //node = rotateRight(node);
//            } else if (balance > 1){
//                if (height(node.right.left) > height(node.right.right)) {
//                    node.right = rotateRight(node.right);
//                    node = rotateLeft(node);
//                } else {
//                    node = rotateLeft(node);
//                }
//                //node = rotateLeft(node);
//            }
//        }

        if (node != null) {
            node = rebalance(node);
        }
        //System.out.println("Balance factor: " + balance + " " + node);
        return node;
    }

    private Node rebalance(Node node) {
        updateHeight(node);
        var balance = getBalanceFactor(node);

        if (balance < -1) {
            if (height(node.left.right) > height(node.left.left)) {
                node.left = rotateLeft(node.left);
                return rotateRight(node);
            } else {
                return rotateRight(node);
            }
            //node = rotateRight(node);
        } else if (balance > 1){
            if (height(node.right.left) > height(node.right.right)) {
                node.right = rotateRight(node.right);
                node = rotateLeft(node);
            } else {
                node = rotateLeft(node);
            }
            //node = rotateLeft(node);
        }

        return node;
    }

    private Node rotateRight(Node node) {
        System.out.println(" HERE LIES A ROTATE RIGHT");

        Node newParent = node.left;
        node.left = newParent.right;
        newParent.right = node;
        newParent.parent = node.parent;

        if (newParent.parent == null) {
            root = newParent;
        }
        // Set new height
        // updateHeight(node);
        updateHeight(newParent);
        return newParent;
    }

    private Node rotateLeft(Node node) {
        Node newParent = node.right;
        node.right = newParent.left;
        newParent.left = node;
        newParent.parent = node.parent;

        if (newParent.parent == null) {
            root = newParent;
        }

        updateHeight(newParent);
        return newParent;
    }

    private Node minimumSuccessorNode(Node node) {
        Node current = node;
        while (current.left != null) {
            current = current.left;
        }

        return current;
    }

    public Node delete(Node root, Integer data) {
        if (root == null) return root;

        if (data < root.data) {
            root.left = delete(root.left, data);
        } else if (data > root.data) {
            root.right = delete(root.right, data);
        } else {
            // Aktchual delete
            // If root only has one child, delete that child
            // If root has no child, set root to null
            if (root.left == null || root.right == null) {
                // Set root to the non-null child or set root to null.
                root = (root.left == null) ? root.right : root.left;

            } else {
                Node temp = minimumSuccessorNode(root);
                root.data = temp.data;
                // Delete the duplicate minimum successor node
                root = delete(root.right, temp.data);
            }
        }

        if (root == null) return root;

        // Step 2: update height of current node
        updateHeight(root);

        // Step 3: rebalance shit
        return rebalance(root);
    }
}
