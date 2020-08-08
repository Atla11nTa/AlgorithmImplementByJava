package 笔试汇总.网易;

public class Main1 {

    private static long getMax(long[] nums, int n) {
        long res = 0;
        for (int i = 0; i < n; i++) {
            long num = nums[i];
            long left = 0;
            long right = Long.MAX_VALUE / 2;
            long mid = 0;
            while (left < right) {
                mid = left + (right - left) / 2;
                if (2 * mid == num) {
                    break;
                } else if (2 * mid > num) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
            if (2 * mid == num) {
                res += mid;
                continue;
            }
            while (2 * left > num) {
                left--;
            }
            res += left;
        }
        return res;
    }

    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        int n = in.nextInt();
//        long[] nums = new long[n];
//        for (int i = 0; i < n; i++) {
//            nums[i] = in.nextInt();
//        }
        long[] nums = new long[]{1,1,7};
        int n = nums.length;
        System.out.println(getMax(nums, n));
    }
}
