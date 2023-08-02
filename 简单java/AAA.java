import java.util.concurrent.Semaphore;

public class AAA {
    private final int capacity = 5; // 缓冲区容量
    private final Semaphore empty = new Semaphore(capacity); // 空缓冲区的信号量
    private final Semaphore full = new Semaphore(0); // 满缓冲区的信号量
    private final Semaphore mutex = new Semaphore(1); // 互斥信号量

    private int food = 0; // 缓冲区

    // 生产
    class Producer implements Runnable {
        @Override
        public void run() {
            try {
                while (true) {
                    empty.acquire(); // 等待空缓冲区
                    mutex.acquire(); // 互斥访问缓冲区
                    System.out.println("生产食品 " + food);
                    food++;
                    Thread.sleep(500);
                    mutex.release(); // 释放缓冲区
                    full.release(); // 增加满缓冲区
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // 消费
    class Consumer implements Runnable {
        @Override
        public void run() {
            try {
                while (true) {
                    full.acquire(); // 等待满缓冲区
                    mutex.acquire(); // 互斥访问缓冲区
                    food--;
                    System.out.println("消费食品 " + food);
                    Thread.sleep(500);
                    mutex.release(); // 释放缓冲区
                    empty.release(); // 增加空缓冲区
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        AAA AAA = new AAA();
        Thread producer = new Thread(AAA.new Producer());
        Thread consumer = new Thread(AAA.new Consumer());
        producer.start();
        consumer.start();
    }
}

