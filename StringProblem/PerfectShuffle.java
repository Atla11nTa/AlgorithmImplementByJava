package StringProblem;

import java.util.Arrays;

/**
 * 题目：完美洗牌问题
 * 题目描述： 长度为2N的数组，将后半区的元素依次前半区每个元素之前，要求时间复杂度O(N)，空间复杂度O(1)。
 * eg:[1,2,3,4,5,6] -> [4,1,5,2,6,3]
 * 分析： 首先想到插入法，但时间复杂度是O(N^2)，因为要求时间复杂度为O（N），所以肯定只能遍历一次，应该要根据规律将元素一次性放入最后的位置上。
 * 对比元素调整前后的位置发现对于前半区的元素(i<=N)，调整后位置是2i，而后半的元素(N<i<=2N)，调整后是2*(i-N)-1。
 * 根据规律就可以依次调整，先将1放入2号位置，又把2放入4号位置，4放入1号位置，这叫“下标连续推法”。
 * 但是仍然存在问题，一次推导1->2->4->1，从1开始又从到1结束了，还需要从3开始。
 * 对于求解的轮数和每轮的起点，存在一个计算公式，长度为 3^k - 1的数组，需要进行k轮，每轮起点是1,3,9,27...
 * 例如数组 [1,2,3,4,5,6,7,8,9,10]，为了满足长度是3^k-1，就要先划分出一个长度为8的子数组。
 * 数组原本是[L1,L2,L3,L4,L5,R1,R2,R3,R4,R5]，要取的子数组应该是[L1,L2,L3,L4,R1,R2,R3,R4],这就需要将R1,R2,R3,R4循环移到前面，数组的循环移动
 */

public class PerfectShuffle {
    public static void Solution(int[] arr, int left, int right) {
        if(arr != null && arr.length != 0 && (arr.length & 1)==0){
            while (right-left+1>0){
                //待处理的数组长度
                int len = right - left + 1;
                //计算子数组部分的长度和递推起始点个数
                int subLen = 0;
                int k = 1;
                while(Math.pow(3,k)-1 <= len){
                    subLen = (int) Math.pow(3, k) - 1;
                    k++;
                }
                k--;
                //数组的中点
                int mid = (left + right) / 2;
                //待处理数组一半的长度
                int half = subLen / 2;
                ArrayRotate.Solution(arr,left+half,mid,mid+half);
                Cycles(arr,left,subLen,k);
                left = left + subLen;
            }

        }
    }
    public static void Cycles(int[] arr, int left, int len, int k) {
        int N = len/2;
        //trigger表示递推的起始位置,分别为1, 3, 9 ,27...相对位置
        for(int i=0,trigger = 1;i<k;i++,trigger *= 3){
            //用beginIndex记录递推开始的位置,注意是相对位置
            int beginIndex = trigger -1;
            //先完成第一次递推
            int nextIndex = 2*(beginIndex + 1)-1;
            int last = arr[left + nextIndex];
            arr[left + nextIndex] = arr[left + beginIndex];
            //当递推回到起点时退出循环
            while (nextIndex != beginIndex) {
                //属于前半区
                if (nextIndex <= N - 1) {
                    nextIndex = 2 * (nextIndex + 1) - 1;
                }
                //属于后半区
                else {
                    nextIndex = 2 * (nextIndex - N);
                }
                int temp = arr[nextIndex+left];
                arr[nextIndex+left] = last;
                last = temp;
            }
        }

    }

    public static void main(String[] args) {
        int[] arr = new int[]{1,2,3,4,5,6};
        Solution(arr,0,arr.length-1);
        System.out.println(Arrays.toString(arr));
    }
}
