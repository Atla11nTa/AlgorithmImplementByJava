package LeetCode;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 题目: 497.非重叠矩形中的随机点
 * 题目地址: https://leetcode-cn.com/problems/random-point-in-non-overlapping-rectangles/
 * 问题描述: 有一组不重叠的矩形, 每次调用pick()将随机取出这些矩形内部(包含边界)的一个点.
 * 思路分析: 因为要求随机,而且矩形有多个.
 * 因为一个面积为n的矩形区域, 就有n个整数点. 计算这个矩形的总面积, 选一个点, 等价于从这些面积数值中选一个. 对于每个矩形中的点本方案是从左往右,从下往上逐行的设置的.
 * 1. 计算每个矩形的面积s[i], 然后用一个数组保存areaSum, 其中areaSum[i]表示前i个矩阵的累加和.
 * 2. 随机取得一个在0~totalArea之间的数,代表选中第choose个坐标.
 * 3. 根据这个面积通过二分查找, 就可以定位到那个具体的矩形.
 * 4. 然后根据面积计算相对偏移, 然后加上这个矩形左下角坐标即是最终结果.
 * 标记: 典型题,需要好好理解.
 */
public class Problem497 {
        int[] areaSum;
        int[][] rects;
        int totalArea = 0;
        Random rand = new Random();
        public Problem497(int[][] rects) {
            this.areaSum = new int[rects.length];
            this.rects = rects;
            int count = 0;
            for (var rect : rects) {
                int area = Math.abs(rect[2] - rect[0] + 1) * Math.abs(rect[3] - rect[1] + 1);
                totalArea += area;
                areaSum[count++] = totalArea;
            }
        }

        public int[] pick() {
            int chooseArea = rand.nextInt(totalArea);
            int left = 0;
            int right = rects.length - 1;
            int mid;
            while (left < right) {
                mid = (left + right) / 2;
                if (chooseArea >= areaSum[mid]) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }
            //chooseArea这个面积点落在第left个矩形中.
            int[] x = rects[left];
            int width = x[2] - x[0] + 1;
            int height = x[3] - x[1] + 1;
            int base = areaSum[left] - width * height;
            //本方案中坐标点是从左到右/从下到上编排的, 因为选中的面积点落在x矩形的第chooseArea-base点,所以分别对width求余和求商得到相对偏移.
            return new int[]{x[0] + (chooseArea - base) % width, x[1] + (chooseArea - base) / width};
        }

}
