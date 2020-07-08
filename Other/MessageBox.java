package Other;

/**
 * 题目：将乱序接收的消息按顺序打印
 * 题目描述： 一个长度为n的数组arr乱序存放了数字1～n，这个数组作为消息流，当接收1~i中的所有数字才可以打印。
 * 解题分析： 和计网中的滑动窗口类似。
 * 用一个messageBox(hashMap)保存数字i是否已经接收，lastSortedNum上一次有序达到的数字，curSortedNum记录当前有序到达的数字。
 * 每当一个新数字，都先将messageBox中对应位置置为true，然后检查有序达到的部分没有有增加，如果增加了就打印这一部分。
 */
public class MessageBox {
    public static void solution(int[] arr) throws InterruptedException {
        int n = arr.length;
        boolean[] messageBox = new boolean[n + 1];
        int lastSortedNum = 0;
        int curSortedNum = 0;
        for (int i = 0; i < n; i++) {
            curSortedNum = lastSortedNum;
            messageBox[arr[i]] = true;
            //检查有序达到的部分是否增加。
            while (curSortedNum + 1 <= n && messageBox[curSortedNum + 1]) {
                curSortedNum++;
            }
            //打印新增加的有序部分，
            for (int j = lastSortedNum + 1; j <= curSortedNum; j++) {
                System.out.println(j);
            }
            Thread.sleep(1000);
            lastSortedNum = curSortedNum;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        int[] arr = new int[]{1, 3, 2, 4, 7, 5, 8, 9, 6, 10};
        solution(arr);
    }
}
