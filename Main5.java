public class Main5 {
    public static int solution(String str1, String str2) {
        char[] chars1 = str1.toCharArray();
        char[] chars2 = str2.toCharArray();
        int len1 = chars1.length;
        int len2 = chars2.length;
        boolean flag;
        for (int i = 0; i <= len1-len2; i++) {
            flag = true;
            for (int j = 0; j < len2; j++) {
                if (chars1[i + j] != chars2[j]) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                return i;
            }
        }
        return -1;
    }
    public static void main(String[] args) {
        String str1 = "www";
        String str2 = "www";
        System.out.println(solution(str1, str2));
    }
}
