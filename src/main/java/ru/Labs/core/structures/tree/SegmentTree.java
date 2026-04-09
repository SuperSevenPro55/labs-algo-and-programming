package ru.Labs.core.structures.tree;

public class SegmentTree {
    private final long[] tree;
    private final int n;

    public SegmentTree(long[] arr) {
        this.n = arr.length;
        this.tree = new long[4 * n];

        if (n > 0) {
            buildTree(1, 0, n - 1, arr);
        }
    }

    private void buildTree(int node, int start, int end, long[] arr) {
        if (start == end) {
            tree[node] = arr[start];
            return;
        }

        int mid = start + (end - start) / 2;

        int left = 2 * node;
        int right = 2 * node + 1;

        buildTree(left, start, mid, arr);
        buildTree(right, mid + 1, end, arr);

        tree[node] = Math.min(tree[left], tree[right]);
    }

    public long getMin(int left, int right) {
        return query(1, 0, n - 1, left, right);
    }

    private long query(int node, int start, int end, int left, int right) {
        // Отрезок не пересекается
        if (right < start || left > end) {
            return Long.MAX_VALUE;
        }

        // Идеальное пересечение
        if (left <= start && end <= right) {
            return tree[node];
        }

        // Частичное пересечение
        int mid = start + (end - start) / 2;
        int leftChild = 2 * node;
        int rightChild = 2 * node + 1;

        long leftMin = query(leftChild, start, mid, left, right);
        long rightMin = query(rightChild, mid + 1, end, left, right);

        return Math.min(leftMin, rightMin);
    }
}
