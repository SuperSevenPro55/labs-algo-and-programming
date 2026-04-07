package ru.Labs.core.structures.tree;

public class AVLTree extends BinarySearchTree {
    @Override
    public void insert(int value) {
        root = insertRecAVL(root, value);
    }

    private Node insertRecAVL(Node node, int value) {
        if (node == null) {
            return new Node(value);
        }

        if (value < node.value) {
            node.left = insertRecAVL(node.left, value);
        } else if (value > node.value) {
            node.right = insertRecAVL(node.right, value);
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

    @Override
    public void delete(int value) {
        root = deleteRecAVL(root, value);
    }

    private Node deleteRecAVL(Node node, int value) {
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
    private int getHeight(Node node) {
        if (node == null) {
            return 0;
        }

        return node.height;
    }

    // Получить баланс
    private int getBalance(Node node) {
        if (node == null) {
            return 0;
        }

        return getHeight(node.left) - getHeight(node.right);
    }

    @SuppressWarnings("SuspiciousNameCombination")
    private Node rightRotate(Node yNode) {
        Node xNode = yNode.left;
        Node tail = xNode.right;

        xNode.right = yNode;
        yNode.left = tail;

        yNode.height = Math.max(getHeight(yNode.left), getHeight(yNode.right)) + 1;
        xNode.height = Math.max(getHeight(xNode.left), getHeight(xNode.right)) + 1;

        return xNode;
    }

    @SuppressWarnings("SuspiciousNameCombination")
    private Node leftRotate(Node xNode) {
        Node yNode = xNode.right;
        Node tail = yNode.left;

        yNode.left = xNode;
        xNode.right = tail;

        xNode.height = Math.max(getHeight(xNode.left), getHeight(xNode.right)) + 1;
        yNode.height = Math.max(getHeight(yNode.left), getHeight(yNode.right)) + 1;

        return yNode;
    }
}