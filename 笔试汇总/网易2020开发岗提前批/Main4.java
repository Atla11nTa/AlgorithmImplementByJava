package 笔试汇总.网易2020开发岗提前批;

import java.util.*;

/**
 * 思路： 利用或的特点， 若x是要找的数， 如果x|num != x，那么num就一定不是结果集中的数，因为num有多余的1, 利用这个特点，遍历所有的数，
 * 把符合条件的加入到结果集。
 */
public class Main4 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int caseNum = in.nextInt();
        int[] nums = new int[100010];
        int count = 0;
        boolean[] numsSet = new boolean[100010];
        for (int i = 0; i < caseNum; i++) {
            int op = in.nextInt();
            int num = in.nextInt();
            if (op == 1) {
                if (!numsSet[num]) {
                    nums[count++] = num;
                    numsSet[num] = true;
                }
            }
            if (op == 2) {
                int res = 0;
                for (int j = 0; j < count; j++) {
                    // nums[j]可能是结果集中的元素，可多不可少。
                    if ((nums[j] | num) == num) {
                        res |= nums[j];
                        if (res == num) {
                            break;
                        }
                    }
                }
                if (res == num) {
                    System.out.println("YES");
                } else {
                    System.out.println("NO");
                }
            }
        }
    }
}
