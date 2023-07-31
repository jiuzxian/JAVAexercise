import java.util.*;

public class Caculate {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        float a = 0, b = 0;
        char fh;
        String cx="y";


        do{while (true) {
            System.out.println("请输入第一个数：");
            if (s.hasNextFloat()) {
                a = s.nextFloat();
                break;
            } else {
                s.next();
                System.out.println("输入不合法！");
            }
        }

        while(true) {
            System.out.println("请运算符号：(+,-,*,/)");

            fh = s.next().charAt(0);

            if(fh=='+' || fh=='-' || fh=='*' || fh=='/')
                break;
            else
                System.out.println("输入不合法！");
        }

        while(true) {
            System.out.println("请输入第二个数：");

            if (s.hasNextFloat()) {
                b = s.nextFloat();
                break;
            } else{
                s.next();
                System.out.println("输入不合法！");}
        }



        double result = 0;


            switch (fh){
                case '+':
                    result = a+b;
                    System.out.println(""+a+fh+b+"结果是："+result);
                    break;

                case '-':
                    result = a-b;
                    System.out.println(""+a+fh+b+"结果是："+result);
                    break;

                case '*':
                    result = a*b;
                    System.out.println(""+a+fh+b+"结果是："+result);
                    break;

                case '/':
//                    if(b==0){
//                        System.out.println("被除数为0，请重新输入！");
//                        break;}

                    try {
                        result = a/b;
                    }catch (ArithmeticException e) {
                        e.printStackTrace();//找到异常出现的位置
                        System.out.println("被除数为0！");
                    }
                        System.out.println(""+a+fh+b+"结果是："+result);
                        break;

                default:
                    System.out.println("输入的符号无效");

        }

        System.out.println("重新开始按任意键，退出请按q：");
        cx=s.next();}while (cx!="q");

//        if(cx=="q")
//            return;
//        else
//            // 这种场景不适合递归的方式
//            main(new String[0]);
    }
}