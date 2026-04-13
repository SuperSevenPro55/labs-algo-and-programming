package ru.Labs.core.structures.tree;

public class BinarySearchTree extends CommonTree<BinarySearchTree.BSTNode> implements ITree {
    static class BSTNode extends Node<BSTNode> {
        public BSTNode(int value) {
            super(value);
        }
    }

    @Override
    public void insert(int value) {
        root = insertRec(root, value);
    }

    @Override
    public void delete(int value) {
        root = deleteRec(root, value);
    }

    private BSTNode insertRec(BSTNode node, int value) {
        if (node == null) {
            return new BSTNode(value);
        }

        if (value < node.value) {
            node.left = insertRec(node.left, value);
        } else if (value > node.value) {
            node.right = insertRec(node.right, value);
        }
        return node;
    }

    private BSTNode deleteRec(BSTNode node, int value) {
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
}