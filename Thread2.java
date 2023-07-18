import java.lang.*;

public class Thread2 implements Runnable {

    int c = 0;

    @Override
    public void run() {
        //计数器=10结束
        while (c<10){
            c = c + 1;
            System.out.println(c);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public static void main(String[] args) {

        Thread2 t2=new Thread2();
        Thread thread2 = new Thread(t2);
        thread2.start();

    }
}
