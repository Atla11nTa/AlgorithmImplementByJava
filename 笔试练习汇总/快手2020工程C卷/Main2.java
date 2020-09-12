package 笔试练习汇总.快手2020工程C卷;

import java.util.Scanner;

public class Main2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int studentCount = in.nextInt();
        int problemCount = in.nextInt();
        String[] studentRes = new String[studentCount];
        int[] problemWight = new int[problemCount];
        for (int i = 0; i < studentCount; i++) {
            studentRes[i] = in.next();
        }
        for (int i = 0; i < problemCount; i++) {
            problemWight[i] = in.nextInt();
        }
        int ACount, BCount, CCount, DCount, ECount;
        int res = 0;
        // 遍历每个问题
        for (int i = 0; i < problemCount; i++) {
            ACount = 0;
            BCount = 0;
            CCount = 0;
            DCount = 0;
            ECount = 0;
            int maxCount = Integer.MIN_VALUE;
            for (int j = 0; j < studentCount; j++) {
                switch (studentRes[j].charAt(i)) {
                    case 'A':
                        ACount++;
                        maxCount = Math.max(maxCount, ACount);
                        break;
                    case 'B':
                        BCount++;
                        maxCount = Math.max(maxCount, BCount);
                        break;
                    case 'C':
                        CCount++;
                        maxCount = Math.max(maxCount, CCount);
                        break;
                    case 'D':
                        DCount++;
                        maxCount = Math.max(maxCount, DCount);
                        break;
                    case 'E':
                        ECount++;
                        maxCount = Math.max(maxCount, ECount);
                        break;
                }
            }
            res += problemWight[i] * maxCount;
        }
        System.out.println(res);

    }
}
