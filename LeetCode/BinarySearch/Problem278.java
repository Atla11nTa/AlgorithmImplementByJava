package LeetCode.BinarySearch;

/**
 * 题目: 278.第一个错误版本.
 * 思路:二分查找
 * 注意当mid是错误的版本时, right = mid,而不是mid-1,因为 这个mid可能是答案,所以不能将其从下一次的搜索范围内排除.
 */
public class Problem278 {
    boolean isBadVersion(int version) {
        switch (version) {
            case 1:
            case 2:
            case 3:
                return false;
            case 4:
            case 5:
                return true;
        }
        return true;
    }
    public int firstBadVersion(int n) {
        int left = 1;
        int right = n;
        int mid;
        while (left < right) {
            mid = left + (right - left) / 2;
            // mid是错误的版本,因为当前mid可能是结果,所以right从mid开始,而不是mid-1
            if (isBadVersion(mid)) {
                right = mid;
            } else{
                left = mid + 1;
            }
        }
        return left;
    }
}
