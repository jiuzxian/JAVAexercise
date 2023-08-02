import java.lang.*;






public class Thread1 implements Runnable {

    int c = 0;

    @Override
    public void run() {
        //计数器
        while (true){
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

        Thread1 t1=new Thread1();
        Thread thread1 = new Thread(t1);
        thread1.start();

    }
}
