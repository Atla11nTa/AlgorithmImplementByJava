package Other;

/**
 * 简单题
 */
public class DayN {

    public static int Solution(int n){
        int day = 1;
        int sum = 0;
        int YesterdayMake = 1;
        int TodayMake = 1;
        int count = 1;
        int curCount = 0;
        while(day<=n){
            if(curCount < count){
                TodayMake = YesterdayMake;
            }else {
                TodayMake = YesterdayMake+1;
                curCount = 0;
                count++;
            }
            curCount++;
            sum += TodayMake;
            YesterdayMake = TodayMake;
            day ++;
        }
        return sum;
    }

    public static void main(String[] args) {
        int n = Solution(2);
        System.out.println(n);
    }
}
