package ru.Labs.core.structures.tree;

public class BinarySearchTree {
    Node root;

    public BinarySearchTree() {
        root = null;
    }

    public void insert(int value) {
        root = insertRec(root, value);
    }

    private Node insertRec(Node node, int value) {
        if (node == null) {
            return new Node(value);
        }

        if (value < node.value) {
            node.left = insertRec(node.left, value);
        } else if (value > node.value) {
            node.right = insertRec(node.right, value);
        }

        return node;
    }

    public void delete(int value) {
        root = deleteRec(root, value);
    }
    
    private Node deleteRec(Node node, int value) {
        if (node == null) {
            return null;
        }

        if (value < node.value) {
            node.left = deleteRec(node.left, value);
        } else if (value > node.value) {
            node.right = deleteRec(node.right, value);
        } else {
            if (node.left == null) { // Нет детей или один ребенок
                return node.right;
            }
            if (node.right == null) {
                return node.left;
            }

            node.value = minValue(node.right); // Два ребенка
            node.right = deleteRec(node.right, node.value);
        }

        return node;
    }

    private int minValue(Node node) {
        int minValue = node.value;

        while (node.left != null) {
            minValue = node.left.value;
            node = node.left;
        }

        return minValue;
    }

    public boolean hasValue(int value) {
        Node currentNode = root;

        while (currentNode != null) {
            if (value == currentNode.value) {
                return true;
            }

            if (value < currentNode.value) {
                currentNode = currentNode.left;
            } else {
                currentNode = currentNode.right;
            }
        }

        return false;
    }

    public Integer next(int value) { // minTreeElement > value
        Integer possAnswer = null;
        Node currentNode = root;

        while (currentNode != null) {
            if (value < currentNode.value) {
                possAnswer = currentNode.value;
                currentNode = currentNode.left;
            } else {
                currentNode = currentNode.right;
            }
        }

        return possAnswer;
    }

    public Integer prev(int value) { // maxTreeElement < value
        Integer possAnswer = null;
        Node currentNode = root;

        while (currentNode != null) {
            if (value > currentNode.value) {
                possAnswer = currentNode.value;
                currentNode = currentNode.right;
            } else {
                currentNode = currentNode.left;
            }
        }

        return possAnswer;
    }

    private static class Node {
        int value;
        Node left, right;

        public Node(int value) {
            this.value = value;
            left = right = null;
        }
    }
}