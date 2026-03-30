package ru.Labs.core.structures.queue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PriorityQueueImpl implements PriorityQueue<Integer>{
    private final List<Node> heap = new ArrayList<>();
    private final Map<Long, Integer> idToIndex = new HashMap<>();
    private long operationCounter = 0;

    private static class Node {
        int value;
        long operationId;

        Node(int value, long operationId) {
            this.value = value;
            this.operationId = operationId;
        }
    }

    @Override
    public void enqueue(Integer value) { // Вставка в очередь
        operationCounter++;

        Node newNode = new Node(value, operationCounter);
        heap.add(newNode);

        int currentIndex = heap.size() - 1;
        idToIndex.put(operationCounter, currentIndex);

        siftUp(currentIndex);
    }

    @Override
    public Integer dequeueMax() { // Извлечение
        if (heap.isEmpty()) {
            System.out.println("*");
            return null;
        }

        Node maxNode = heap.getFirst();

        System.out.println(maxNode.operationId + " " + maxNode.value);

        idToIndex.remove(maxNode.operationId);

        Node lastNode = heap.getLast();
        heap.removeLast();

        if (!heap.isEmpty()) {
            heap.set(0, lastNode);
            idToIndex.put(lastNode.operationId, 0);
            siftDown(0);
        }

        return maxNode.value;
    }

    @Override
    public void increment(long operation, Integer addition) { // Увеличение i-го элемента
        Integer index = idToIndex.get(operation);
        if (index == null) {
            return;
        }

        Node node = heap.get(index);
        node.value += addition;

        siftUp(index);
    }

    private void siftUp(int index) {
        while (index > 0) {
            int parentIndex = (index - 1) / 2;

            if (heap.get(index).value <= heap.get(parentIndex).value) {
                break;
            }

            swap(index, parentIndex);
            index = parentIndex;
        }
    }

    private void siftDown(int index) {
        int size = heap.size();

        while (true) {
            int leftChild = 2 * index + 1;
            int rightChild = 2 * index + 2;
            int largest = index;

            if (leftChild < size && heap.get(leftChild).value > heap.get(largest).value) {
                largest = leftChild;
            }
            if (rightChild < size && heap.get(rightChild).value > heap.get(largest).value) {
                largest = rightChild;
            }
            if (largest == index) {
                break;
            }

            swap(index, largest);
            index = largest;
        }
    }

    private void swap(int i, int j) {
        Node node1 = heap.get(i);
        Node node2 = heap.get(j);

        heap.set(i, node2);
        heap.set(j, node1);

        idToIndex.put(node1.operationId, j);
        idToIndex.put(node2.operationId, i);
    }
}