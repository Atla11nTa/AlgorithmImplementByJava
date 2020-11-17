package 笔试汇总.搜狗;

public class Main2 {
    public void rotate(char[][] matrix, int n) {
        char temp;
        // 对称翻转
        for (int i = 0; i < n / 2; i++) {
            for (int j = 0; j < n; j++) {
                temp = matrix[i][j];
                matrix[i][j] = matrix[n - i - 1][j];
                matrix[n - i - 1][j] = temp;
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
    }

    public String rotatePassword(String[] s1, String[] s2) {
        // write code here
        int n = s1.length;
        // 解密格子
        char[][] matrix1 = new char[n][];
        // 密文
        char[][] matrix2 = new char[n][];
        for (int i = 0; i < n; i++) {
            matrix1[i] = s1[i].toCharArray();
            matrix2[i] = s2[i].toCharArray();
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    if (matrix1[j][k] == '0') {
                        stringBuilder.append(matrix2[j][k]);
                    }
                }
            }
            rotate(matrix1, n);
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        String[] str1 = {
                "1101", "1010", "1111", "1110"
        };
        String[] str2 = {
                "ABCD", "EFGH", "IJKL", "MNPQ"
        };
        Main2 m = new Main2();
        System.out.println(m.rotatePassword(str1, str2));
    }
}
