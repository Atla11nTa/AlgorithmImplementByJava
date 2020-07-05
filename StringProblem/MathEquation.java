package StringProblem;

import java.util.Deque;
import java.util.Stack;

/**
 * 题目： 公式字符串求值
 * 题目描述： 给定一个字符串str，str表示一个公式，公式里可能有整数/加减乘除符号和左右括号，返回公式的计算结果。不需要异常检测
 */

public class MathEquation {
    public static boolean isSymbol(char ch){
        if (ch == '(' || ch == ')' || ch == '+' || ch == '-' || ch == '*' || ch == '/') {
            return true;
        }
        return false;
    }

    public static double calculate(double num1,double num2,char ch){
        switch (ch){
            case '/':
                return num1 / num2;
            case '*':
                return num1 * num2;
            case '+':
                return num1 + num2;
            case '-':
                return num1 - num2;
        }
        return 0;
    }

    //比较优先级，+-优先级低于*/
    public static int comparePriority(char ch1, char ch2) {
        if(ch1 == '+' || ch1 == '-'){
            switch (ch2){
                case '+':
                case '-':
                    return 0;
                case '*':
                case '/':
                    return -1;
            }
        }
        if(ch1 == '*' || ch1 == '/'){
            switch (ch2){
                case '+':
                case '-':
                    return 1;
                case '*':
                case '/':
                    return 0;
            }
        }
        return 1;
    }
    public static double Solution(String str){
        char[] strArr = str.toCharArray();
        Stack<Character> symbolStack = new Stack<>();
        Stack<Double> numStack = new Stack<>();
        double res = 0;
        double num1,num2;
        int i = 0;
        for(;i<strArr.length;i++){
            char ch = strArr[i];
            if(isSymbol(ch)){
                if(ch == '('){
                    symbolStack.push(ch);
                    continue;
                }
                if(ch == ')'){
                    while (true){
                        ch = symbolStack.pop();
                        //匹配到一个左括号时结束计算
                        if(ch == '(')
                            break;
                        num2 = numStack.pop();
                        num1 = numStack.pop();
                        numStack.push(calculate(num1,num2,ch));
                    }
                    continue;
                }
                //符号栈为空，或者优先级大于栈顶元素，就入栈
                if (symbolStack.empty() || comparePriority(ch, symbolStack.peek()) > 0) {
                    symbolStack.push(ch);
                    continue;
                }
                //当优先级小于栈顶元素时，就进行计算
                if (comparePriority(ch, symbolStack.peek()) <= 0) {
                    char ch1 = ch;
                    char ch2;
                    do {
                        ch2 = symbolStack.pop();
                        num2 = numStack.pop();
                        num1 = numStack.pop();
                        numStack.push(calculate(num1, num2, ch2));
                    } while (!symbolStack.empty() && comparePriority(ch, symbolStack.peek()) <= 0);
                    //最后记得把ch入栈
                    symbolStack.push(ch);
                }
            }
            //对于数字的处理
            else {
                double num = ch-48;
                while (i + 1 < strArr.length && !isSymbol(strArr[i + 1])) {
                    num = num * 10 + strArr[++i] - 48;
                }
                numStack.push((double)(num));
            }
        }
        //结尾计算
        char ch;
        while (!symbolStack.empty()){
            num2 = numStack.pop();
            num1 = numStack.pop();
            ch = symbolStack.pop();
            numStack.push(calculate(num1, num2, ch));
        }
        return numStack.peek();
    }

    public static void main(String[] args) {
        String str = "4*3"; //
        System.out.println(Solution(str));
    }
}
