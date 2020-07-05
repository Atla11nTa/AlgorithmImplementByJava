package GreedyAlgorithm;

import java.util.Scanner;

public class problem1 {
    public static int Solution(int x, int a, int b){
        int minMoney = 0;
        double preA = 500.0/a;
        double preB = 1500.0/b;
        int aNum = 0;
        int bNum = 0;
        int result = x;
        while (result>0){
            if(result<=500){
                if(a<b){
                    aNum++;
                }else
                    bNum++;
                break;

            }
            else if(result>500 && result<=1500){
                int bCost = b;
                int aCurNum = result/500;
                if(aCurNum*500 <result)
                    aCurNum++;
                int aCost = aCurNum*a;
                if(bCost<aCost)
                    bNum++;
                else
                    aNum += aCurNum;
                break;
            }
            else {
                //A单价便宜
                if(preA>preB){
                    aNum++;
                    result -= 500;
                }else{
                    bNum++;
                    result -= 1500;
                }
            }
        }
        minMoney = aNum*a + bNum*b;
        return minMoney;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int num = in.nextInt();
        int[] re = new int[num];
        int[][] input = new int[num][3];
        for(int i=0;i<num;i++){
            input[i][0] = in.nextInt();
            input[i][1] = in.nextInt();
            input[i][2] = in.nextInt();
        }
        for(int i=0;i<num;i++)
            re[i] = Solution(input[i][0],input[i][1],input[i][2]);
        for(int i=0;i<re.length;i++){
            System.out.println(re[i]);
        }
        //System.out.println(Solution(1100 ,26 ,44));
    }
}
