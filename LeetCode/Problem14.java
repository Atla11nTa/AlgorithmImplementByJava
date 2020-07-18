package LeetCode;

public class Problem14 {
    public static String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        if (strs.length == 1) {
            return strs[0];
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < strs[0].length(); i++) {
            char ch = strs[0].charAt(i);
            for (int j = 1; j < strs.length; j++) {
                if (i >= strs[j].length() || strs[j].charAt(i) != ch) {
                    return stringBuilder.toString();
                }
            }
            stringBuilder.append(ch);
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        System.out.print(longestCommonPrefix(new String[]{"aa","a"}));
    }
}
