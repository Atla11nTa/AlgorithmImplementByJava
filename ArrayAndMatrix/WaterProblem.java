package ArrayAndMatrix;

/**
 * 题目：容器装水问题
 * 题目描述： 数组arr的每个元素表示容器壁高度，求这个容器的总共能装多少水。
 * 解题思路：
 * 最简单的思路：求每一格上方能装几格水，分别该格子左边和右边最高的格子，然后考虑两者较小值，较小值与格子本身的高度之差就是能装的水，但如果差值为负
 * 说明这一格装不了水。把每一格能装的水加起来就是总的。
 * 解题方法：暴力法每次求左边和右边的最大值，时间O(N^2)
 * 方法2： 每个格子求左右最大值的过程是有重复的，所以可以先遍历两边数组，从左到右遍历，把每个格子左边最大值保存在数组leftMaxArr中，从右到左遍历。。。
 * 最后再求每个格子的存水量。 时间复杂度O(N)，空间复杂度O(N)
 */
public class WaterProblem {
    public static int solution(int[] arr) {
        if (arr.length < 3) {
            return 0;
        }
        int maxWater = 0;
        for (int i = 1; i < arr.length - 1; i++) {
            int leftMax = Integer.MIN_VALUE;
            for (int j = 0; j < i; j++) {
                leftMax = Math.max(leftMax, arr[j]);
            }
            int rightMax = Integer.MIN_VALUE;
            for (int j = i + 1; j < arr.length; j++) {
                rightMax = Math.max(rightMax, arr[j]);
            }
            maxWater += Math.max(Math.min(leftMax, rightMax) - arr[i], 0);
        }
        return maxWater;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{4, 5, 1, 3, 2};
        System.out.println(solution(arr));

    }
}
