package Classes;

import MinSearch.PriorityQueue;
import java.util.ArrayList;
import java.util.List;

public class PriorityQueueImpl implements PriorityQueue<Integer>{
    private List<Node> heap = new ArrayList<>(); // Создание кучи на основе структуры с двумя полями
    private long operationCounter = 0; // Для введения приоритетов

    private static class Node {
        int data;
        final long operationNum;

        Node (int data, long operationNum) {
            this.data = data;
            this.operationNum = operationNum;
        }
    }

    @Override
    public void enqueue(Integer value) { // Вставка в очередь
        // TODO
    }

    @Override
    public Integer dequeueMax() { // Извлечение
        // TODO
        return null;
    }

    @Override
    public void increment(long operation, Integer addition) { // Увеличение i-го элемента
        // TODO
    }
}
