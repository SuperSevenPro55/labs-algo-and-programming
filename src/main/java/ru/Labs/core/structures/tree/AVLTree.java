package ru.Labs.core.structures.tree;

public class AVLTree extends CommonTree<AVLTree.AvlNode> implements ITree {

    static class AvlNode extends Node<AvlNode> {
        int height = 1;

        public AvlNode(int value) {
            super(value);
        }
    }

    @Override
    public void insert(int value) {
        root = insertRec(root, value);
    }

    @Override
    public void delete(int value) {
        root = deleteRecAVL(root, value);
    }

    private AvlNode insertRec(AvlNode node, int value) {
        if (node == null) {
            return new AvlNode(value);
        }

        if (value < node.value) {
            node.left = insertRec(node.left, value);
        } else if (value > node.value) {
            node.right = insertRec(node.right, value);
        } else {
            return node;
        }

        // Обновляем высоту
        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));

        // Разница высот левого и правого поддерева
        int balance = getBalance(node);

        // Случаи плохих узлов
        // 1. left-left (прямая линия влево)
        if (balance > 1 && value < node.left.value) {
            return rightRotate(node);
        }
        // 2. right-right (прямая линия вправо)
        if (balance < -1 && value > node.right.value) {
            return leftRotate(node);
        }
        // 3. left-right (зигзаг влево-вправо)
        if (balance > 1 && value > node.left.value) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
        // 4. right-left (загзаг вправо-влево)
        if (balance < -1 && value < node.right.value) {
            //noinspection SuspiciousNameCombination
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    private AvlNode deleteRecAVL(AvlNode node, int value) {
        if (node == null) {
            return null;
        }

        if (value < node.value) {
            node.left = deleteRecAVL(node.left, value);
        } else if (value > node.value) {
            node.right = deleteRecAVL(node.right, value);
        } else {
            if (node.left == null) { // Нет детей или один ребенок
                return node.right;
            }
            if (node.right == null) {
                return node.left;
            }

            node.value = minValue(node.right); // Два ребенка
            node.right = deleteRecAVL(node.right, node.value);
        }

        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));
        int balance = getBalance(node);

        // Случаи плохих узлов
        // left-left
        if (balance > 1 && getBalance(node.left) >= 0) {
            return rightRotate(node);
        }
        // right-right
        if (balance < -1 && getBalance(node.right) <= 0) {
            return leftRotate(node);
        }
        // left-right
        if (balance > 1 && getBalance(node.left) < 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
        // right-left
        if (balance < -1 && getBalance(node.right) > 0) {
            //noinspection SuspiciousNameCombination
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    // Получить высоту
    private int getHeight(AvlNode node) {
        if (node == null) {
            return 0;
        }

        return node.height;
    }

    // Получить баланс
    private int getBalance(AvlNode node) {
        if (node == null) {
            return 0;
        }

        return getHeight(node.left) - getHeight(node.right);
    }

    @SuppressWarnings("SuspiciousNameCombination")
    private AvlNode rightRotate(AvlNode yNode) {
        AvlNode xNode = yNode.left;
        AvlNode tail = xNode.right;

        xNode.right = yNode;
        yNode.left = tail;

        yNode.height = Math.max(getHeight(yNode.left), getHeight(yNode.right)) + 1;
        xNode.height = Math.max(getHeight(xNode.left), getHeight(xNode.right)) + 1;

        return xNode;
    }

    @SuppressWarnings("SuspiciousNameCombination")
    private AvlNode leftRotate(AvlNode xNode) {
        AvlNode yNode = xNode.right;
        AvlNode tail = yNode.left;

        yNode.left = xNode;
        xNode.right = tail;

        xNode.height = Math.max(getHeight(xNode.left), getHeight(xNode.right)) + 1;
        yNode.height = Math.max(getHeight(yNode.left), getHeight(yNode.right)) + 1;

        return yNode;
    }
}