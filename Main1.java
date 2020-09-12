import java.util.*;

public class Main1 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        int x = in.nextInt();
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            priorityQueue.offer(in.nextInt());
        }
        for (int i = 0; i < m; i++) {
            int num = priorityQueue.poll();
            num += x;
            priorityQueue.offer(num);
        }
        System.out.println(priorityQueue.peek());
    }
}
