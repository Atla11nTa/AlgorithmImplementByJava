package LeetCode;

/**
 * 题目： 168. Excel表列名称、171. Excel表列序号
 * 思路： 实际就是进制转换， 10进制<- ->26进制
 * 注意的是： 数字转字符串，因为这26进制数没有0， 所以进制转换时，每位-1.
 */
public class H_Problem_168 {
    public static String convertToTitle(int n) {
        StringBuilder stringBuilder = new StringBuilder();
        while (n != 0) {
            n --;//这里稍作处理，因为它是从1开始，每位-1
            stringBuilder.append((char)(n % 26 + 'A'));
            n /= 26;
        }
        return stringBuilder.reverse().toString();
    }
    public static int titleToNumber(String s) {
        char[] chars = s.toCharArray();
        int res = 0;
        int mask = 1;
        for (int i = chars.length - 1; i >= 0; i--) {
            res += (chars[i] - 'A' + 1) * mask;
            mask *= 26;
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(titleToNumber("ZY"));
    }
}
