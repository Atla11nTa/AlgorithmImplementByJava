package LeetCode;

/**
 * 题目： 393. UTF-8 编码验证
 * 思路：注意检验要求。
 * 正确的UTF-8格式：
 * 1. 高位连续1的个数不能是1个，1个是无意义的，也不能超过4个。
 * 2. 高位n个1，那么后面紧跟的n-1个字节高位必须是10.
 */
public class Problem393 {
    //检查高位连续1的个数。
    private static int oneCount(int num) {
        int mask = 1 << 7;
        int count = 0;
        while ((mask & num) != 0) {
            mask = mask >>> 1;
            count++;
        }
        return count;
    }

    // 检查高位是否是10
    private static boolean isOneZero(int num) {
        int mask1 = 1 << 7;
        int mask2 = 1 << 6;
        //判断是否高位是10
        return (num & mask1) != 0 && (num & mask2) == 0;
    }

    public static boolean validUtf8(int[] data) {
        // curLen记录当前数高位连续1的个数。
        int curLen = oneCount(data[0]);
        if (curLen == 1 || curLen > 4) {
            return false;
        }
        //记录接下来高位为10的字节个数。
        int validCount;
        validCount = curLen == 0 ? 0 : curLen - 1;
        for (int i = 1; i < data.length; i++) {
            // 仍需10
            if (validCount > 0) {
                if (isOneZero(data[i])) {
                    validCount--;
                } else {
                    return false;
                }
            } else {
                curLen = oneCount(data[i]);
                if (curLen == 1 || curLen > 4) {
                    return false;
                }
                validCount = curLen == 0 ? 0 : curLen - 1;
            }
        }
        return validCount == 0;
    }

    private static void numToBit(int num) {
        int count = 7;
        while (count >= 0) {
            System.out.print((1 << count & num) >>> count--);
        }
    }

    public static void main(String[] args) {
        int[] data = new int[]{248, 130, 130, 130};
        numToBit(248);
        System.out.println(validUtf8(data));
    }
}
