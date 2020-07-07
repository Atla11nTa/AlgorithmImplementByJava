package Other;

/**
 * 题目: 判断一个数字是否是回文数
 * 思路分析：定义一个help使其保持与num的位数相同。
 * num%10取得低位数字，num/help取高位数字，比较即可知高低位是否相同，若相同就通过num/10去除低位，num%help去除高位，继续下一步比较。
 */
public class NumIsPalindrome {
    public static boolean solution(int num) {
        num = Math.abs(num);
        int help = 1;
        while (num/help >= 10) {
            help *= 10;
        }
        while (num != 0) {
            if (num / help != num % 10) {
                return false;
            }
            num = (num % help) / 10;
            help /= 100;
        }
        return false;
    }

    public static void main(String[] args) {

    }
}
