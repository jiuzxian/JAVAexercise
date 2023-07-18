import java.util.*;


public class Maptest {

    public static void main(String[] args) {
        final int length=10;
        Map<Integer, Integer> a = new HashMap<>();
        Map<Integer, Integer> b = new HashMap<>();

        HashSet temp = new HashSet();

        Random r = new Random();


        for(int i=0;i<length;i++){
            a.put(i,r.nextInt(20));
        }

        for(int i=0;i<length;i++){
            b.put(i,r.nextInt(20));
        }
        Collection<Integer> collectiona=a.values();
        Collection<Integer> collectionb=b.values();

        System.out.println("遍历相同：");

        Set<Integer> ak = a.keySet();
        for(int key:ak) {
            if(b.containsValue(a.get(key))){
                System.out.print(a.get(key)+" ");}
        }
//////////////////////
        System.out.println("通过iterator遍历所有的value,但是不能遍历key");
        Iterator<Map.Entry<Integer, Integer>> iterator = a.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<Integer, Integer> next = iterator.next();
            System.out.println("key="+next.getKey()+"value="+next.getValue());
        }



        System.out.println("");
        System.out.println("验证");

        System.out.println(collectiona.toString());
        System.out.println(collectionb.toString());



    }


}