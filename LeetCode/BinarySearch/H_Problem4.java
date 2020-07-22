package LeetCode.BinarySearch;

/**
 * 4. 寻找两个正序数组的中位数
 * 思路: 拓展一下就是找最小的第K个数.
 * 假设两个数组总长为n, 若n为奇数, 那么中位数就是两个数组的第n/2 + 1个数, 若n为偶数, 那么中位数就是第 n/2和n/2+1的平均数.
 * 现在就解决如何找两个有序数组的第k个数.
 * 思路1: 合并数组, 时间复杂度O(n), 空间复杂度O(n), 适合用于链表
 * 思路2: 堆, 保留一个大小为k的大顶堆,堆顶就是第k大的数.
 * 若是求中位数就小顶堆保存后n/2个数,堆顶是最小的数, 大顶堆保存前n/2个数,堆顶是最大的数, 那么最终的中位数就由两个堆顶元素得到. 时间复杂度O(nlongn),空间复杂度O(n), 适合用于求动态更新数组的中位数.
 *
 * 思路3: 本文解法!!!时间复杂度O(n),接下来,详细说明这种方案. 这种思想来自于二分查找,即排除那些肯定不是答案的部分.
 * 1. 先假设两个数组元素个数都大于k/2, 那么我们可以安全的删除其中一个数组的前k/2个元素, 删除规则?
 * num1是数组1的第k/2个数, num2是数组2的第k/2个数, 如果num1>num2,那么数组2的前k/2个数肯定不是答案, 那么我们就安全的去除这些数, 在剩下的数组中找第k-k/2个数.
 * 2. 当k==1时,这是返回两个数组的第一个元素中的较小值即可.
 * 3. 如果某个数组的元素个数不足k/2, 这时如何处理? 假设数组1元素个数不足k/2
 * 数组1元素个数不足k/2, 就直接删除数组2的前k/2个元素, 因为这些都不可能是答案, 因为这些元素即使比数组1所有元素都大,都不会是第k个数.
 */

class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int len1 = nums1.length;
        int len2 = nums2.length;
        int total = len1 + len2;
        int k1 = (total + 1) / 2;
        int k2 = (total + 2) / 2;
        // 如果长度为偶数, 找最中间的两个数
        if (k1 != k2) {
            return (findK(nums1, 0, nums2, 0, k1) + findK(nums1, 0, nums2, 0, k2)) / 2.0;
        } else {
            return findK(nums1, 0, nums2, 0, k1);
        }
    }

    //找到两个数组中第k小的元素
    public int findK(int[] nums1, int left1, int[] nums2, int left2, int k) {
        // nums1已经没有元素了, 那么返回nums2的第k个元素即可
        if (left1 >= nums1.length)
            return nums2[left2 + k - 1];
        // 同上
        if (left2 >= nums2.length)
            return nums1[left1 + k - 1];
        // k==1, 返回二者中较小的值.
        if (k == 1) {
            return Math.min(nums1[left1], nums2[left2]);
        }
        // 如果数组1没有k/2个数了怎么办? 那就直接把数组2的前k/2个数删除, 因为这些都不可能是答案.
        if (left1 + k / 2 - 1 >= nums1.length) {
            return findK(nums1, left1, nums2, left2 + k / 2, k - k / 2);
        }
        if (left2 + k / 2 - 1 >= nums2.length) {
            return findK(nums1, left1 + k / 2, nums2, left2, k - k / 2);
        }
        // num1是数组1的第k/2个数
        int num1 = nums1[left1 + k / 2 - 1];
        int num2 = nums2[left2 + k / 2 - 1];
        // num1较小, 说明nums1的前k/2个元素肯定不包含答案, 删除, 在剩下的序列中找第k-k/2个元素.
        if (num1 < num2) {
            return findK(nums1, left1 + k / 2, nums2, left2, k - k / 2);
        } else {
            return findK(nums1, left1, nums2, left2 + k / 2, k - k / 2);
        }
    }

    public static void main(String[] args) {
        Solution so = new Solution();
        int[] nums1 = new int[]{1, 2, 4, 5, 6};
        int[] nums2 = new int[]{7};
        System.out.print(so.findK(nums1, 0, nums2, 0, 6));
    }
}
