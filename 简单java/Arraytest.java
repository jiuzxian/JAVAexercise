import java.util.*;


public class Arraytest {

    public static void main(String[] args) {
        final int length=10;
        int[] a = new int[length];
        int[] b = new int[length];


        HashSet temp = new HashSet();

        Random r = new Random();


        for(int i=0;i<length;i++){
            a[i]=r.nextInt(20);
        }

        for(int i=0;i<length;i++){
            b[i]=r.nextInt(20);
        }

        for (int i = 0; i < length; i++) {
            //System.out.println(a[i]);
            temp.add(a[i]);
        }

        System.out.println("遍历相同：");
        for (int i = 0; i < length; i++) {
            if(temp.contains(b[i]))
                System.out.print(b[i]+" ");
        }

        System.out.println("");
        System.out.println("验证");
        bl(a);
        bl(b);

    }
    private static void bl(int[] n){
        for (int i = 0; i < 10; i++) {
            System.out.print(n[i]+" ");
        }
        System.out.println("");
    }

}