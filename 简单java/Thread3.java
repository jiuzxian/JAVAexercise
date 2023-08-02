import java.lang.*;

public class Thread3{

    int c = 0;


    class T2 implements Runnable {
        @Override
        public void run() {
            //计数器=10结束
            synchronized (this) {
                while (c < 10) {
                    c = c + 1;
                    System.out.println(c);
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                c=3;
                System.out.println("c="+c);
            }
        }
    }

    //到5开始
    class T3 implements Runnable {
        @Override
        public void run() {
            while (true) {
                if(c>4)
                System.out.println(c+"另一个进程进行中");
                try {
                    Thread.sleep(495);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public static void main(String[] args) {

        Thread3 thread3 = new Thread3();
        Thread t2 = new Thread(thread3.new T2());
        Thread t3 = new Thread(thread3.new T3());
        t2.start();
        //可以从外部吗？
        t3.start();


    }
}
