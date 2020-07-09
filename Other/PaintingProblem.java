package Other;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane;

/**
 * 题目：画匠问题
 * 题目描述：数组arr存放n幅画作，arr[i]表示画作i完成所需时间。num表示画匠数量，多个画匠并行画画，每个画匠只能画连在一起的画,且每幅画只能由一个画家完成
 * 求最短完成时间.
 * 解题思路:
 * 设置每个画家的作画时间在[min, max]之间, 不难得出, max = sum{arr[i]}, min = min{arr[i]}. 采用二分查找每个画家的作画时间.
 * 如何二分?
 * 假设画家的工作时间为 maxTime, 那么可以求出在当前条件下所需的画家数量, 若需要的画家数量小于等于条件给出的画家数量, 说明画家数量可以增加,也就是
 * 每个画家的工作时间可以降低, max = mid,反之min =mid, 二分成功.
 */
public class PaintingProblem {
    //maxTime表示每个画家允许的最大工作时间
    public static int getPainterCount(int[] arr, int maxTime) {
        int painterCount = 1;
        int curTime = 0;
        for (int i = 0; i < arr.length; i++) {
            //说明无法完成,单幅作品的时间超过了规定时间
            if (arr[i] > maxTime) {

                return Integer.MAX_VALUE;
            }
            curTime += arr[i];
            //当前工作时间大于规定时间, 画家数量++.
            if (curTime > maxTime) {
                painterCount++;
                curTime = arr[i];
            }
        }
        return painterCount;
    }
    public static int solution(int[] arr, int num) {
        if (arr == null || arr.length < 1 || num < 1) {
            throw new RuntimeException("err");
        }
        if (arr.length <= num) {
            int max = Integer.MIN_VALUE;
            for (int i = 0; i < arr.length; i++) {
                max = Math.max(arr[i], max);
            }
            return max;
        } else {
            int min = Integer.MAX_VALUE;
            int max = 0;
            for (int i = 0; i < arr.length; i++) {
                min = Math.min(arr[i], min);
                max += arr[i];
            }
            int mid = 0;
            int painterCount = 0;
            //不能是min<max - 1, 因为这样的mid就是min,会导致死循环.
            while (min < max - 1) {
                mid = (min + max) / 2;
                painterCount = getPainterCount(arr, mid);
                //如果画家数量大于限定值,说明要减少画家数量,这样的话,每个画家的最大作画时间要增加.
                if (painterCount > num) {
                    min = mid;
                } else {
                    max = mid;
                }
            }
            return max;
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, 1, 1, 3, 4};
        System.out.println(solution(arr, 3));
    }
}
