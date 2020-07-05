package StringProblem;

/**
 * 题目：统计字符串
 * 题目描述： "aaabaa" -> "a_3b_1a_2"
 * 应用：压缩
 */
public class CountString {
    public static String Solution(String str){
        if(str.length() == 0)
            return str;
        StringBuilder result = new StringBuilder();
        result.append(str.charAt(0)).append("_");
        int count = 1;
        char preChar = str.charAt(0);
        for(int i = 1;i<str.length();i++){
            char curChar = str.charAt(i);
            if(curChar != preChar){
                result.append(count).append(curChar).append("_");
                preChar = curChar;
                count = 1;
            }else {
                count++;
            }
        }
        result.append(count);
        return result.toString();
    }

    public static void main(String[] args) {
        String str = "aaabbcc";
        System.out.println(Solution(str));
    }
}
