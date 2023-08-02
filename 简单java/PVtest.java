import java.util.*;

public class PVtest {
    volatile int empty = 5; //饭空缓冲区的数目
    volatile int full = 0; //饭满缓冲区的数目

    volatile int food = 0;//缓冲区
    volatile int t = 0;//吃饭的时间段数

    volatile int linef = 0;//来的人排队
    //每一秒来一个人，有的吃就立即减少

    int mutex = 1;
    volatile boolean isRunning = true;
    volatile boolean unlast = true;


    Object s= new Object();//同步锁

    //生产
    class Producer implements Runnable {
        @Override
        public void run() {

            while (isRunning){
               while (empty<=0);//无限等
                System.out.println("空盘："+empty+" 备货中");
                empty--;//p(empty);
                mutex--;//p(mutex);
                food=food+1;

                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                mutex++;//v(mutex);
                full++;//v(full);
                System.out.println("现有："+full);
            }
            return;
        }
    }
    //消费
    class Consumer implements Runnable {
        @Override
        public void run() {

            while (isRunning) {
                //等人来
                synchronized (s) {
                    try {
                        s.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                }
                System.out.println("排队数："+(linef-1));
                while (linef<=0);
                while (full <= 0) ;
                full--;//p(empty);
                System.out.println("吃");
                mutex--;//p(mutex);
                System.out.println("还剩："+full);

                food--;
                linef--;

                System.out.println("空盘："+empty);

                if(!unlast){//判断是否最后一位
                    isRunning=false;
                    try {
                        Thread.sleep(1500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("人都走了，打烊");
                    break;
                    }

                mutex++;//v(mutex);
                empty++;//v(full);


            }
            return;
        }
    }

    //来人
    class Line implements Runnable {
        @Override
        public void run() {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //5秒后来人,15,唤醒，来人就开始消费
            for (int i = 0; i < 16; i++) {
                try {//每一秒来一人
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
               if(linef < 11) {
                   System.out.println("==来人："+i+"号");
                    linef++;
                   synchronized (s) {
                       s.notify();
                   }
                   if(i==15)
                   {
                       unlast=false;
                   }
                }
            }
            System.out.println("客满");
            return;
        }
        }


    public static void main(String[] args) {

        PVtest pVtest = new PVtest();
        Thread l = new Thread(pVtest.new Line());
        Thread p=new Thread(pVtest.new Producer());
        Thread c=new Thread(pVtest.new Consumer());
        l.start();
        p.start();
        c.start();

    }




}
