package LeetCode.BinarySearch;

import java.util.PriorityQueue;


/**
 * 题目:378. 有序矩阵中第K小的元素
 * 思路: 这里主要讲二分法的思路
 * 这个有序矩阵, 最小的数在左上角, 最大的数在右下角. 第k个数肯定是在这之间, 而且通过观察这个矩阵, 发现其实是可以通过某个方式划分为左右两部分的.
 * 现在的问题就转变成了二分问题, 首先计算mid, 然后通过mid来统计这个矩阵中小于等于mid的数的数量, 若数量大于k,说明在mid左边部分, 否则在右边.
 * 还需要理解的一个点是check函数的思路, 利用矩阵有序的特点(思路就是有序矩阵的搜索), 通过搜索路径来统计数目.
 */
public class H_Problem378 {
    public int kthSmallest(int[][] matrix, int k) {
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a,b)->{
            return a[0] - b[0];
        });
        int row = matrix.length;
        int col = matrix[0].length;
        for (int i = 0; i < row; i++) {
            minHeap.offer(new int[]{matrix[i][0], i, 0});
        }
        int count = 0;
        while (!minHeap.isEmpty() && count < k) {
            int[] cur = minHeap.poll();
            count++;
            if (cur[2] + 1 < col) {
                minHeap.offer(new int[]{matrix[cur[1]][cur[2] + 1], cur[1], cur[2] + 1});
            }
        }
        return minHeap.poll()[0];
    }

    public int kthSmallest2(int[][] matrix, int k) {
        int n = matrix.length;
        // 最小的数
        int left = matrix[0][0];
        // 最大的数
        int right = matrix[n - 1][n - 1];
        while (left < right) {
            int mid = left + ((right - left) >> 1);
            // 如果比mid小的数的个数大于等于k, 在左边找.
            if (check(matrix, mid, k, n)) {
                right = mid;
            }
            // 否则在右边找
            else {
                left = mid + 1;
            }
        }
        return left;
    }

    // 统计比mid小的数的数量与k的关系. 这个其实就是有序矩阵中搜索的思路, 左下角右上角开始都可以.
    public boolean check(int[][] matrix, int mid, int k, int n) {
        int i = n - 1;
        int j = 0;
        int num = 0;
        while (i >= 0 && j < n) {
            // 小于mid就往右走
            if (matrix[i][j] <= mid) {
                // 加上左边一列的i+1个数, 因为左边这一列全部的数都肯定小于mid
                num += i + 1;
                j++;
            }
            // 不然往上走
            else {
                i--;
            }
        }
        return num >= k;
    }
}
