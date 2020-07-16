package LeetCode;

/**
 * 题目: 1352.最后k个数的乘积
 * 思路: 基本思路是记录前缀积, pre[i]表示前i个数的乘积,那么后k个乘积等于pre[n]/pre[n-k]
 * 如果包含为0的数字,那么需要一些技巧处理.
 * 因为题目仅仅要求添加, 不会删除, 所以只需要记录尾部不含0的那一部分的前缀积, 假设这部分长度为count
 * 当k大于count,直接返回0, 因为count之前是有0的.
 * k小于等于count,还是用前缀积计算
 *
 * 附加: 题目要求有删除操作.
 * 1. 有删除操作, 那么上面的方法也是不行的, 用一个额外的数组zero保存0出现的位置.
 * 2. pre[i]表示从i到上一个0之间的前缀积.
 * 3. get操作时,就通过zero检查最后k个之中有没有0, 有0就直接返回0, 没0就用前缀积计算.
 */
public class Problem1352 {
    private int[] preMul;
    private int count;
    public Problem1352() {
        preMul = new int[40005];
        preMul[0] = 1;
        count = 0;
    }

    public void add(int num) {
        if (num == 0) {
            //遇到0就置0
            count = 0;
        } else {
            //仅记录尾部的不为0部分的前缀积
            preMul[++count] = preMul[count - 1] * num;
        }
    }

    public int getProduct(int k) {
        if (count < k) {
            return 0;
        }
        return preMul[count] / preMul[count - k];
    }

}
