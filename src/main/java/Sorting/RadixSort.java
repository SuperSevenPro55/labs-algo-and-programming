package Sorting;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class RadixSort implements sorting<BigInteger>{
    @Override
    public void sort(List<BigInteger> nums) {
        final int MAX_DIGIT = 20; // ТЗ
        List<List<BigInteger>> blocks = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            blocks.add(new ArrayList<>());
        }

        BigInteger divisor = BigInteger.ONE;

        for (int index = 0; index < MAX_DIGIT; index++) {
            for (BigInteger num : nums) {
                int digit = num.divide(divisor).remainder(BigInteger.TEN).intValue(); // (num / divisor) % 10
                blocks.get(digit).add(num);
            }

            nums.clear();

            for (List<BigInteger> block : blocks) {
                nums.addAll(block);
                block.clear();
            }
            divisor = divisor.multiply(BigInteger.TEN);
        }
    }
}