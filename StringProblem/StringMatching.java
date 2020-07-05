package StringProblem;

/**
 * 题目：字符串匹配
 * 题目描述：给定字符串str，其中不含"."和"*"，再给定字符串exp，其中可以含有“."和"*"，”*“不能是首字符，并且任意两个"*"不能相邻。exp中的'.'
 * 代表任何一个字符，exp中的'*'代表'*'前一个字符可以有0个或者任意个。判断str是否可以被exp匹配。
 * 解题思路：
 */

public class StringMatching {
    //递归解法
    public static boolean process(char[] str, char[] exp, int si, int ei) {
        //匹配完成，str也匹配完就是true。
        if (ei == exp.length) {
            return si == str.length;
        }
        //考虑越界, 下一个字符不是'*'的情况
        if (ei == exp.length - 1 || exp[ei + 1] != '*') {
            // 本次校验结果
            boolean res = si != str.length && (exp[ei] == str[si] || exp[ei] == '.');
            // 结合之后的结果
            return res && process(str, exp, si + 1, ei + 1);
        }
        //下一个字符是'*'的情况，str[si]和exp[ei]能够匹配
        while (si != str.length && (str[si] == exp[ei] || exp[ei] == '.')) {
            //考虑str = "abbbc"，exp = "ab*bc"，所以"*"的匹配是尽量匹配，如果可以和"*"下一字符匹配，那就直接返回true。
            if(process(str,exp,si,ei+2))
                return true;
            //不能与"*"的下一位置匹配才继续移动si。
            si++;
        }
        //str[si]和exp[ei]无法匹配了，考察下一位置
        return process(str, exp, si, ei + 2);
    }
    public static boolean solution(String str,String exp){
        char[] strChar = str.toCharArray();
        char[] expChar = exp.toCharArray();
        return process(strChar,expChar,0,0);
    }

    public static void main(String[] args) {
        String str = "aabc";
        String exp = ".*bc";
        System.out.println(solution(str, exp));
    }
}
