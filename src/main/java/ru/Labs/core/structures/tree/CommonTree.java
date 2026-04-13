package ru.Labs.core.structures.tree;

public abstract class CommonTree<N extends CommonTree.Node<N>> implements ITree {
    protected N root;

    protected static abstract class Node<T extends Node<T>> {
        int value;
        T left;
        T right;

        public Node(int value) {
            this.value = value;
            left = right = null;
        }
    }

    @Override
    public abstract void insert(int value);

    @Override
    public abstract void delete(int value);

    protected int minValue(N node) {
        int minValue = node.value;

        while (node.left != null) {
            minValue = node.left.value;
            node = node.left;
        }

        return minValue;
    }

    @Override
    public boolean hasValue(int value) {
        return hasValueCheck(value, root);
    }

    private boolean hasValueCheck(int value, N root) {
        N currentNode = root;

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

    @Override
    public Integer next(int value) { // minTreeElement > value
        Integer possAnswer = null;
        N currentNode = root;

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
        N currentNode = root;

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