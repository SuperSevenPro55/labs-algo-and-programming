package ru.labs.core.structures.stack;

public class MinStackImpl implements MinStack<Integer> {
    @Override
    public void push(Integer data) { // Положить число в стек
        if (head == null) {
            head = new Node(data, data, null);
        }
        else {
            int newMin = Math.min(data, head.min);
            head = new Node(data, newMin, head);
        }
    }

    @Override
    public void pop() {          // Убрать последний элемент в стеке
        //Integer buffer = head.data;
        head = head.next;
    }

    @Override
    public Integer top() {          // Выдать последний элемент в стеке
        return head.data;
    }

    @Override
    public Integer min() {          // Выдать текущий минимум в стеке
        return head.min;
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    private Node head;              // Голова списка (для выполнения за O(1))

    private record Node(int data, int min, Node next) {}
}
