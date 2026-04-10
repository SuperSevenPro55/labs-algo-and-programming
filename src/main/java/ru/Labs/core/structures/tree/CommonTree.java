package ru.Labs.core.structures.tree;

public abstract class CommonTree {
    protected static class Node {
        int value;
        Node left, right;

        public Node(int value) {
            this.value = value;
            left = right = null;
        }
    }

    protected int minValue(Node node) {
        int minValue = node.value;

        while (node.left != null) {
            minValue = node.left.value;
            node = node.left;
        }

        return minValue;
    }

    protected Node insertRec(Node node, int value) {
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

    protected Node deleteRec(Node node, int value) {
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

    protected boolean hasValueCheck(int value, Node root) {
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


}
