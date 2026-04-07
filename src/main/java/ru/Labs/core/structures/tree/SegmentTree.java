package ru.Labs.core.structures.tree;

public class SegmentTree {
    public long[] getTree() {
        return tree;
    }

    private final long[] tree;
    private final int n;

    public SegmentTree(long[] arr) {
        this.n = arr.length;
        this.tree = new long[4 * n];

        if (n > 0) {
            buildTree(1, 0, n - 1, arr);
        }
    }

    public void buildTree(int node, int start, int end, long[] arr) {
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

    private void getMin(int left, int right) {
        // TODO
    }

    private long query(int node, int start, int end, int left, int right) {
        // TODO
        return 0;
    }
}
