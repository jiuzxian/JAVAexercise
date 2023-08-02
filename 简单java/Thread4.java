import java.lang.*;
//超级干货
public class Thread4{

    volatile static int c = 0;
    Object s= new Object();


    class T2 implements Runnable {
        @Override
        public void run() {
            //计数器=10结束, 同步必要吗？

                while (c < 10) {
                    if(c==5) {
                        Thread t4 = new Thread(new T4());
                        t4.start();
                    }
                    c = c + 1;
                    System.out.println(c);
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                //唤醒
                synchronized (s) {
                    s.notify();
            }
        }
    }

    //到5开始,10结束
    /////////////重点！！！
    class T4 implements Runnable {
        @Override
        public void run() {
//            while (c<10) {
//                if(c>4){


                    System.out.println(c+"另一个进程进行中111");

            //同步锁
            synchronized (s) {
                try {
                    s.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                System.out.println("结束");

            }


            }
        }



    public static void main(String[] args) {

        Thread4 thread4 = new Thread4();
        Thread t2 = new Thread(thread4.new T2());
        Thread t4 = new Thread(thread4.new T4());
        t2.start();
        //可以从外部吗？
        //在run内部用while来作为条件的话，运行的频率是什么？加了同步以后是等t2全部运行完才运行吗？
//        if(c==5){
//            System.out.println("111");
////
//        }
//
//        t4.start();


    }
}
