package StringProblem;

public class StringToInt {
    public static int Solution(String str){
        int re = Integer.parseInt(str);
        return re;
    }

    public static void main(String[] args) {
        System.out.println(Solution(new String("-123")));
    }
}
