import java.util.*;

public class Main3 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] nums = new int[n];
        TreeSet<Integer> set = new TreeSet<>((a, b) -> {
            return b - a;
        });
        for (int i = 0; i < n; i++) {
            nums[i] = in.nextInt();
        }
        Arrays.sort(nums);
        for (int num : nums) {
            boolean tag = set.add(num);
            while (!tag) {
                set.remove(num);
                num = num * 2;
                tag = set.add(num);
            }
        }
        for (int num : set) {
            System.out.print(num+" ");
        }
    }
}
