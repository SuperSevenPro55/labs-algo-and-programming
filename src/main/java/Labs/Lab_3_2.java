package Labs;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import Sorting.sorting;
import Sorting.RadixSort;

public class Lab_3_2 {
    public static void start() {
        List<BigInteger> nums = new ArrayList<>();
        Input(nums);

        sorting<BigInteger> radix_sort = new RadixSort();
        radix_sort.sort(nums);

        Output(nums);
    }

    public static void Input(List<BigInteger> nums) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Введите количество элементов: ");
        int quantity = sc.nextInt();
        System.out.println("Введите элементы:");
        for (int i = 0; i < quantity; i++) {
            nums.add(sc.nextBigInteger());
        }
    }

    public static void Output(List<BigInteger> nums) {
        for (BigInteger num : nums) {
            System.out.println(num);
        }
    }
}
