import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
public class Juicepv {

    private   class Juice {
//...
    }

    private   BlockingQueue<Juice> queue = new LinkedBlockingQueue<>(10);
    /**
     * 打烊标记
     */
    private volatile boolean close = false;

    private void runProducer() {
        close = false;
        Thread producer = new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {//该商店一天最多生产100瓶果汁，卖完后打烊；
                    try {
                        Thread.sleep(100);//商店生产一瓶果汁需要0.1秒
                        boolean b = queue.offer(new Juice(), 5, TimeUnit.SECONDS);//当吧台满时等商店最多等待5秒
                        if (!b) {
                            System.err.println("生意不好提前打烊");
                            close = true;
                            return;
                        } else {
                            System.err.println("生产了1瓶，现有" + queue.size() + "瓶在吧台");
                        }
                    } catch (InterruptedException e) {
                    }
                }
                while (!close && !queue.isEmpty()) {//不是提前打烊并且吧台不为空，则一直休眠0.1秒，直到售完
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                    }
                }
                System.err.println("已全部卖完，打烊！");
                close = true;
            };
        };
        producer.start();
    }
    //生意不好提前打烊
    private void simulateEarlyClose() {
        Thread consumers = new Thread() {
            public void run() {
//TODO
                while(!close && !queue.isEmpty()) {//没有打烊，并且桌面上有饮料
                    try {
                        Random random = new Random();
                        int num = random.nextInt(151) + 50;///50--200
                        queue.poll(num, TimeUnit.MILLISECONDS);//消费者购买过程因付费方式不同随机耗时0.05~0.2秒；50-200ms
                        System.err.println("消费者消费了1瓶，现有" + queue.size() + "瓶在吧台");
                        Thread.sleep(6000);//先休眠6秒再开始线程
                    } catch (InterruptedException e) {
// TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            };
        };
        try {
            Thread.sleep(500);//先休眠0.5秒再开始线程
            consumers.start();//线程开始
            consumers.join();//让主线程等待(WAITING状态),一直等到其他线程不再活动为止
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    //卖完100瓶后打烊
    private void simulateSoldOut() {
        Thread consumers = new Thread() {
            public void run() {
//TODO
                while(!close && !queue.isEmpty()) {
                    try {
                        Random random = new Random();
                        int num = random.nextInt(151) + 50;///50--200
                        queue.poll(num, TimeUnit.MILLISECONDS);//消费者购买过程因付费方式不同随机耗时0.05~0.2秒；50-200ms
                        System.err.println("消费者消费了1瓶，现有" + queue.size() + "瓶在吧台");
                        Thread.sleep(1000);//先休眠1秒再开始线程
                    } catch (InterruptedException e) {
// TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            };
        };
        try {
            Thread.sleep(500);
            consumers.start();
            consumers.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    //至少有1名消费者等待超时
//当吧台为空时消费者需要等待，但每一个消费者最多等待1秒，超时后离开，下一个消费者重新等待，另外打烊后消费者都不再等待了
    private void simulateWaitingTimeOut() {
        for(int i=1;i<=10;i++) {
            Thread consumers = new Thread() {
                public void run() {
//TODO
                    while(!close) {//没有打烊
                        try {
                            if(queue.isEmpty()) {//如果桌面上没有饮料，则休眠1秒
                                System.err.println("吧台没有饮料，该消费者等待......");
                                Thread.sleep(1000);
                                if(!close && queue.isEmpty()) {
                                    System.err.println("一名消费者因为吧台没有饮料，等待超过1秒钟超时离开");
                                    break;
                                }
                            }else {
                                Random random = new Random();
                                int num = random.nextInt(151) + 50;///50--200
                                queue.poll(num, TimeUnit.MILLISECONDS);//消费者购买过程因付费方式不同随机耗时0.05~0.2秒；50-200ms
                                System.err.println("消费者消费了1瓶，现有" + queue.size() + "瓶在吧台");
                                Thread.sleep(900);//先休眠0.9秒再开始线程
                            }
                        } catch (InterruptedException e) {
// TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                };
            };
            try {
                Thread.sleep(100);
                consumers.start();
// consumers.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("==============================");
//        Question2 instance = new Question2();
//        instance.runProducer();
//        instance.simulateEarlyClose();

        System.out.println("==============================");
//        Question2 instance2 = new Question2();
//        instance2.runProducer();
//        instance2.simulateSoldOut();

        System.out.println("==============================");
        Juicepv instance3 = new Juicepv();
        instance3.runProducer();
        instance3.simulateWaitingTimeOut();
    }
}
