package BitOperation;

/**
 * 题目：整数的二进制数表达中有多少个1
 * 思路分析：n &= n-1；每次运算将抹掉n最右边的一个1，所以循环i次后，n为0，1的个数也就是i。
 *
 */
public class OneCount {
    public static int solution(int n) {
        int res = 0;
        while (n != 0) {
            res += n & 1;
            n >>>=1;
        }
        return res;
    }
    public static int solution2(int n){
        int res = 0;
        while (n != 0) {
            //每次抹掉最右边的一个1，循环n次，抹掉n个1。
            n &= n - 1;
            res++;
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(solution2(10));
    }
}
