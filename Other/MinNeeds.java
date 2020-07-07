package Other;

/**
 * 题目：累加出整个范围所有的数最少还需几个数
 * 题目描述： 给定一个有序的正数数组arr和一个正数range，如果自由选择arr中的数字，使其累加和覆盖1~range之间的所有数字，返回arr最少还缺少几个数
 */
public class MinNeeds {
    public static int solution(int[] arr, int range) {
        int needs = 0;
        //表示当前能够覆盖的范围。
        int curMax = 0;
        //依次考察arr的每个数, 每次确保覆盖arr[i]-1前的数
        for (int i = 0; i < arr.length; i++) {
            // 当前无法覆盖arr[i]-1前的数
            while (curMax <= arr[i] - 1) {
                needs++;
                //添加一个数curMax+1. 现在的范围变成2*curMax+1
                curMax = curMax + curMax + 1;
                if (curMax >= range) {
                    return needs;
                }
            }
            //添加arr[i]这个数字，使覆盖范围变大。
            curMax += arr[i];
            if (curMax >= range) {
                return needs;
            }
        }
        while (range >= curMax) {
            curMax = curMax + curMax + 1;
            needs++;
        }
        return needs;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 3, 7};
        int range = 15;
        System.out.println(solution(arr,range));
    }
}
