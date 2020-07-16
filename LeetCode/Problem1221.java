package LeetCode;

/**
 * 题目: 1221分割平衡字符串
 */
public class Problem1221 {
    public static int balancedStringSplit(String s) {
        int count = 0;
        int RCount = 0;
        int LCount = 0;
        char[] strArr = s.toCharArray();
        for (int i = 0; i < strArr.length; i++) {
            if (strArr[i] == 'R') {
                RCount++;
            } else {
                LCount++;
            }
            if (RCount == LCount) {
                count++;
                RCount = 0;
                LCount = 0;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.print(balancedStringSplit("RLRRLLRLRL"));
    }
}
