package Other;

import javafx.collections.transformation.SortedList;

/**
 * 题目： 分糖果问题
 * 题目描述： 每个孩子不管得分多少，起码分得一个糖果，任意两个相邻的孩子之间，得分较多的孩子必须拿多一些的糖果。
 * 思路分析：假设一个得分序列。[1,2,3,5,2,1,3,4,2]
 * 这个序列可以看作两部分，[1,2,3,5,2,1] [3,4,2]
 * 两部分分别考虑，对于序列1：[1,2,3,5,2,1]，[1,2,3,5]逐渐增加，那么合格的糖果分配是1,2,3,4;[5,2,1]逐渐降低，合格的糖果分配是[3,2,1]
 * 对于同一个得分5存在矛盾，应该按两种分配的较大值分配顶点5才符合答案。
 * 所以思路出来了，左边除去峰顶分配，右边除去峰顶分配，而峰顶按较大值分配。
 */
public class CandySplit {
    public static int solution(int[] arr) {
        int res = 0;
        int index = 0;
        int leftBegin = 0;
        int peak = 0;
        int rightEnd = 0;
        int leftLen = 0;
        int rightLen = 0;
        while (index < arr.length) {
            peak = index;
            leftBegin = index;
            //上坡阶段，峰顶左移。
            while (peak + 1 < arr.length && arr[peak] < arr[peak + 1]) {
                peak++;
            }
            rightEnd = peak;
            //下坡阶段
            while (rightEnd + 1 < arr.length && arr[rightEnd] > arr[rightEnd + 1]) {
                rightEnd++;
            }
            //上坡长度
            leftLen = peak - leftBegin + 1;
            //下坡长度
            rightLen = rightEnd - peak + 1;
            //上坡阶段除去峰顶的分配
            res += leftLen > 1 ? leftLen * (leftLen - 1) / 2 : 0;
            //下坡阶段除去峰顶的分配
            res += rightLen > 1 ? rightLen * (rightLen - 1) / 2 : 0;
            //按较大值分配峰顶。
            res += Math.max(leftLen, rightLen);
            index = rightEnd + 1;
        }
        return res;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 2, 1};
        System.out.println(solution(arr));

    }
}
