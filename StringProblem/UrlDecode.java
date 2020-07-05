package StringProblem;

import java.util.Scanner;

/**
 * 题目：url解码
 * 题目描述： 一串字符串，遇到%说明后两位是16进制需要对其解码转为字符。
 *
 * 题目分析：
 * 注意ASCII表，‘0’=48,‘A’=65,‘a’=97。
 * 1. 注意点1： 两个char作为16进制数转为10进制数，然后转为char字符。
 * 2. 还有就是解码后的符号也可能是%，需要继续解码
 */

public class UrlDecode {
    public static String Solution(String str){
        String result = "";
        for(int i=str.length()-1;i>=0;i--){
            char ch = str.charAt(i);
            if(ch != '%'){
                result = String.valueOf(ch).concat(result);
                continue;
            }
            while(ch == '%'){
                ch = HexToChar(result);
                if(result.length()>2){
                    result = result.substring(2,result.length());
                }else
                    result = "";
                if(ch != '%')
                    result = String.valueOf(ch).concat(result);
            }
        }
        return result;
    }

    public static int HexToInt(char ch){
        int num = -1;
        if(ch>='0'&& ch<='9')
            num = (int)(ch-48);
        if(ch>='A'&&ch<='F')
            num= (int) (ch-55);
        if(ch>='a'&&ch<='f')
            num= (int) (ch-87);
        return num;
    }

    public static char HexToChar(String hex){
        char ch = 0;
        int num1 = HexToInt(hex.charAt(0));
        int num2 = HexToInt(hex.charAt(1));
        ch = (char)(num1*16+num2);
        return ch;
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int caseNum = in.nextInt();
        String[] reArr = new String[caseNum];
        for(int i=0;i<caseNum;i++){
            reArr[i] = Solution(in.next());
        }
        for(int i=0;i<reArr.length;i++)
            System.out.println(reArr[i]);
//        System.out.println(Solution("a%%32f"));
    }
}
