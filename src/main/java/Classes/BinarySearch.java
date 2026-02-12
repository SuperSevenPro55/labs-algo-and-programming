package Classes;

public class BinarySearch {
    public static int binarySearch(int[] arr, int place) {
        int left = 0;
        int right = arr.length;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (arr[mid] >= place) {
                right = mid;
            }
            else {
                left = mid + 1;
            }
        }
        return left;
    }
}
