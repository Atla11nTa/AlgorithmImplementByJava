package LeetCode;


/**
 * 题目：190. 颠倒二进制位
 * 思路： 每次提取最右边一位然后放到左边，不要原位的交换，那么麻烦。
 */
public class Problem190 {
    public int reverseBits(int n) {
        int ret = 0, power = 31;
        while (n != 0) {
            ret += (n & 1) << power;
            n = n >> 1;
            power -= 1;
        }
        return ret;
    }
}
