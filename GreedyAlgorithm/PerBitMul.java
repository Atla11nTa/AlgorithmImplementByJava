package GreedyAlgorithm;

/**
 * 题目描述： 给定n，找到最小的数m，使得m的每一位乘积等于n
 * 分析： 典型的贪心问题，因为m的所有位乘积为n，所以从个位开始，让大数排低位，小数排高位即可
 * 找每一位的数时，先从9开始比较，若n能整除，那就找下一位，若从9比较到2都无法整除，那么说明找不到。
 */

public class PerBitMul {
    public static int Solution(int n){
        if(n<10)
            return n;
        int[] resultList = new int[2000];
        int resultLen = 0;
        while(true){
            boolean flag = false;
            for(int i=9;i>=2;i--){
                if(n%i == 0){
                    resultList[resultLen++] = i;
                    n /=i;
                    flag = true;
                    break;
                }
            }
            if(!flag){
                return -1;
            }
            if(n<10){
                resultList[resultLen] = n;
                break;
            }
        }
        int m = 0;
        for(int i=resultLen;i>=0;i--){
            m += resultList[i]*Math.pow(10,i);
        }
        return m;
    }

    public static void main(String[] args) {
        int n = 100;
        int m = Solution(n);
        System.out.println(m);
    }
}
