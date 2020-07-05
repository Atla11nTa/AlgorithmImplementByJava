package StringProblem;

/**
 * 问题：判断两个字符串是否互为旋转词
 * 问题描述：若将字符串a的前面部分转移到后面得到字符串b，那么ab就称互为旋转词
 */
public class isRotation {
    public static boolean Solution(String a, String b){
        if(a.equals(b))
            return true;
        if(a.length()!=b.length())
            return false;
        for(int i=0;i<a.length();i++){
            String sub1 = a.substring(0,i+1);
            String sub2 = i<a.length()-1?a.substring(i+1,a.length()):"";
            String newStr = sub2+sub1;
            if(newStr.equals(b)){
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        String a = "1ab2";
        String b = "ab12";
        System.out.println(Solution(a,b));
    }
}
