package Other;

import java.util.*;

public class ShortestSubArray {
    public static boolean isSubArr(int[] arr,int left,int right,int[] subArr){
        Set<Integer> set = new HashSet<>();
        for(int i=left;i<=right;i++)
            set.add(arr[i]);
        for(int i=0;i<subArr.length;i++){
            if(!set.contains(subArr[i]))
                return false;
        }
        return true;
    }
    public static int Solution(int[] arr,int[] subArr){
        //先把要查找的数组加入集中
        Set<Integer> set = new HashSet<>();
        for(int i=0;i<subArr.length;i++){
            set.add(subArr[i]);
        }
        int minLen = Integer.MAX_VALUE;
        int minSubArrLeft = -1;
        int minSubArrRight = -1;
        //子串从是[i...j]部分
        for(int i=0;i<arr.length;i++){
            //如果集中不包含这个数，右移
            if(!set.contains(arr[i]))
                continue;
            for(int j=i;j<arr.length;j++){
                if(isSubArr(arr,i,j,subArr) && j-i+1<minLen){
                    minSubArrLeft = i;
                    minSubArrRight = j;
                    minLen = j-i+1;
                    break;
                }
            }
        }
        if(minSubArrLeft == -1){
            return 0;
        }
        return minSubArrRight-minSubArrLeft+1;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int caseNum = in.nextInt();
        int[] re = new int[caseNum];
        for(int i=0;i<caseNum;i++){
            int arrLen = in.nextInt();
            int[] arr = new int[arrLen];
            for(int j=0;j<arrLen;j++)
                arr[j] = in.nextInt();
            int subArrLen = in.nextInt();
            int[] subArr = new int[subArrLen];
            for(int j=0;j<subArrLen;j++)
                subArr[j] = in.nextInt();

            re[i] = Solution(arr,subArr);
        }

        for(int i=0;i<re.length;i++)
            System.out.println(re[i]);
    }
}
