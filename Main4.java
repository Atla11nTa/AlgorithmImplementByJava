import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Main4 {
     static volatile boolean flag = false;
    static volatile boolean free = false;
    public static void main(String[] args) {
        String str = "12A34B56C78D910E1112F1314G1516H1718I1920J2122K";
        ReentrantLock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        char[] chars = str.toCharArray();
        List<Integer> numList = new ArrayList<>();
        List<Character> charList = new ArrayList<>();
        for (char ch : chars) {
            if (Character.isDigit(ch)) {
                numList.add(ch - '0');
            } else {
                charList.add(ch);
            }
        }
        Thread numThread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int num : numList) {
                    if (free) {
                        System.out.println(Thread.currentThread().getName()+":"+num);
                        continue;
                    }
                    try {
                        lock.lock();
                        while (!flag) {
                            condition.await();
                        }
                        System.out.println(Thread.currentThread().getName()+":"+num);
                        flag = false;
                        condition.signal();
                    } catch (InterruptedException e) {
                    } finally {
                        lock.unlock();
                    }
                }
                free = true;
            }
        },"Thread1");
        Thread charThread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (char ch : charList) {
                    if (free) {
                        System.out.println(Thread.currentThread().getName()+":"+ch);
                        continue;
                    }
                    try {
                        lock.lock();
                        while (flag) {
                            condition.await();
                        }
                        System.out.println(Thread.currentThread().getName()+":"+ch);
                        flag = true;
                        condition.signal();
                    }
                    catch (InterruptedException e){
                    }
                    finally {
                        lock.unlock();
                    }
                }
                free = true;
            }
        },"Thread2");
        numThread.start();
        charThread.start();
    }
}
