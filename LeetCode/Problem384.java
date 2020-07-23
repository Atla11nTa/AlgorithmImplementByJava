package LeetCode;

import java.util.Arrays;
import java.util.Random;

/**
 * 题目: 384. 打乱数组
 * 思路: Shuffle洗牌算法 每次将i-len一个随机位置(包含i)与i位置交换
 */
public class Problem384 {
    private int[] origin;
    private Random rand = new Random();
    public Problem384(int[] nums) {
        origin = nums;
    }

    /** Resets the array to its original configuration and return it. */
    public int[] reset() {
        return origin;
    }

    /**
     * Returns a random shuffling of the array.
     */
    public int[] shuffle() {
        int[] afterShuffle = Arrays.copyOf(origin, origin.length);
        int index;
        int temp;
        int len = afterShuffle.length;
        for (int i = 0; i < len - 1; i++) {
            // 随机从i以及其后选择一个数,然后与i位置交换.
            index = rand.nextInt(len - i) + i;
            temp = afterShuffle[index];
            afterShuffle[index] = afterShuffle[i];
            afterShuffle[i] = temp;
        }
        return afterShuffle;
    }
}
