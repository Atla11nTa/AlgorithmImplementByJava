package BitOperation;

/**
 * 题目：位运算实现整数的加减乘除运算
 * 题目分析：
 * 加法： a^b是不带进位的加法，a&b<<1是进位，所以把不带进位的结果加上进位就是带进位加法，循环相加，直到进位为0
 * 减法： a-b = a+(-b)，-b = (~b)+1;
 * 乘法： a*b = sum(a*bi*2^i)。其中a*2^i = a<<i。
 * 除法： 贪心策略。乘法的逆过程，a/b = res, 即a = b*res = sum(b*res_i*2^i)。
 *     采用贪心策略进行上面的逆过程，从最大的i找。每次减去b*2^i，并把res_i置1。
 */
public class BitCompute {
    //位运算加法
    public static int add(int a, int b) {
        int sum = a;
        while (b != 0) {
            //不算进位相加，异或运算。0+0 = 0^0 , 0+1 = 0^1, 1+1 = 1^1
            sum = a ^ b;
            //进位，a+b的进位， 1+1才会产生进位，所以把a&b的结果左移一位就是进位。
            b = (a & b) << 1;
            a = sum;
        }
        return sum;
    }
    //位运算减法， a-b = a+(-b)。负数表示是补码，需要转换。
    public static int minus(int a,int b) {
        //得到一个数的相反数。补码(取反加1)
        b = (~b) + 1;
        return add(a, b);
    }

    //a*b = sum(a*2^i*bi)
    public static int mul(int a, int b) {
        int bi = 0;
        int i = 0;
        int sum = 0;
        while (b > 0) {
            // 求a * 2^i，乘2就是左移一位
            if ((b & 1) != 0) {
                sum = add(sum, a);
            }
            a <<= 1;
            b = b >>> 1; //高位补0
        }
        return sum;
    }

    //a/b = res, 即a = b*res = sum(b*res_i*2^i)。
    //采用贪心策略进行上面的逆过程，从最大的i找。每次减去b*2^i，并把res_i置1.
    public static int div(int a,int b) {
        //先把负数转为正数
        a = a < 0 ? (~a) + 1 : a;
        b = b < 0 ? (~b) + 1 : b;
        int res = 0;
        for (int i = 31; i >= 0; i = minus(i, 1)) {
            //a可以包含一个b<<i
            if (a >> i >= b) {
                //结果的i位置1
                res |= 1 << i;
                a = minus(a, b << i);
            }
        }
        //根据结果的正负改变res的符号
        return a < 0 ^ b < 0 ? (~res) + 1 : res;
    }

    public static void main(String[] args) {
        System.out.println(add(12, -2));
        System.out.println(minus(12,2));
        System.out.println(mul(12,2));
        System.out.println(div(12, 2));
    }
}
