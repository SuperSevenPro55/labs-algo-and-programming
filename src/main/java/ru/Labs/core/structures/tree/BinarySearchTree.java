package ru.Labs.core.structures.tree;

public class BinarySearchTree extends CommonTree implements ITree {
    protected Node root;

    public BinarySearchTree() {
        root = null;
    }

    @Override
    public void insert(int value) {
        root = insertRec(root, value);
    }

    @Override
    public void delete(int value) {
        root = deleteRec(root, value);
    }

    @Override
    public boolean hasValue(int value) {
        return hasValueCheck(value, root);
    }

    @Override
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

    @Override
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
}