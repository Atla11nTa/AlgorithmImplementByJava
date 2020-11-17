import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class MyThreadPool {
    private LinkedBlockingQueue<Runnable> taskQueue;
    private final Queue<Thread> threadQueue;
    private volatile int capacity;
    private volatile int size;
    ReentrantLock lock = new ReentrantLock();
    Condition empty = lock.newCondition();
    volatile boolean isShutDown = false;
    public MyThreadPool(int size){
        this.capacity = size;
        this.taskQueue = new LinkedBlockingQueue<>();
        this.threadQueue = new ArrayDeque<>();
        this.size = 0;
    }

    public void submit(Runnable runnable) throws InterruptedException {
        try {
            lock.lock();
            taskQueue.put(runnable);
            empty.signal();
        }finally {
            lock.unlock();
        }
        // 线程容量不足
        if (size < capacity) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (!Thread.currentThread().isInterrupted() && !isShutDown) {
                        try {
                            lock.lock();
                            Runnable task = taskQueue.poll();
                            if (task == null) {
                                empty.await();
                            } else {
                                task.run();
                            }
                        } catch (InterruptedException e) {

                        } finally {
                            lock.unlock();
                        }
                    }
                }
            });
            synchronized (threadQueue){
                threadQueue.offer(thread);
                size++;
            }
            thread.start();
        }
    }

    public void shutDown() {
        try {
            lock.lock();
            for (Thread thread : threadQueue) {
                thread.interrupt();
                System.out.print("thread down");
            }
            isShutDown = true;
            empty.signalAll();
        }finally {
            lock.unlock();
        }

    }

    public static void main(String[] args) throws InterruptedException {
        MyThreadPool myThreadPool = new MyThreadPool(5);
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            myThreadPool.submit(new Runnable() {
                @Override
                public void run() {
                    System.out.println(finalI);
                }
            });
        }
        Thread.sleep(1000);
        myThreadPool.shutDown();
    }
}
