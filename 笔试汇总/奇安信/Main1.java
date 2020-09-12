package 笔试汇总.奇安信;

import java.util.Scanner;

public class Main1 {
    // 判断两个矩形是否有交集
    public boolean check(int x01, int y01, int x02, int y02, int x11, int y11, int x12, int y12) {
        int zx = Math.abs(x01 + x02 - x11 - x12);
        int x = Math.abs(x01 - x02) + Math.abs(x11 - x12);
        int zy = Math.abs(y01 + y02 - y11 - y12);
        int y = Math.abs(y01 - y02) + Math.abs(y11 - y12);
        if (zx < x && zy < y) {
            return false;
        } else {
            return true;
        }
    }
    public int getMaxArea (int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4) {
        int res = 0;
        //枚举所有可能的矩形
        for (int x00 = 0; x00 <= 10; x00++) {
            for (int y00 = 0; y00 <= 10; y00++) {
                for (int x01 = x00 + 1; x01 <= 10; x01++) {
                    for (int y01 = y00 + 1; y01 <= 10; y01++) {
                        if (check(x00, y00, x01, y01, x1, y1, x2, y2) && check(x00, y00, x01, y01, x3, y3, x4, y4)) {
                            res = Math.max(res, (x01 - x00) * (y01 - y00));
                        }
                    }
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Main1 m = new Main1();
        System.out.print(m.getMaxArea(0,0,1,1,9,9,10,10));
    }
}
