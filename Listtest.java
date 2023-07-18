import java.util.*;


public class Listtest {

    public static void main(String[] args) {
        final int length=10;
        List<Integer> a=new ArrayList<>();
        List<Integer> b=new ArrayList<>();
        List<Integer> temp=new ArrayList<>();


        Random r = new Random();


        for(int i=0;i<length;i++){
            a.add(r.nextInt(20));
        }

        for(int i=0;i<length;i++){
            b.add(r.nextInt(20));
        }


        System.out.println("遍历相同：");
        for (int i = 0; i < length; i++) {
            if(b.contains(a.get(i)) && !temp.contains(a.get(i))){
                temp.add(a.get(i));
                System.out.print(a.get(i)+" ");}
        }

        System.out.println("");
        System.out.println("验证");
        System.out.println(a.toString());
        System.out.println(b.toString());

    }

}
