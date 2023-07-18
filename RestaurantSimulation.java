import java.util.concurrent.Semaphore;

public class RestaurantSimulation {
    private static final int MAX_DISHES = 5;        // 最大菜品数量，厨师可以准备的最大菜品数
    private static final int MAX_CUSTOMERS = 10;    // 餐厅内允许同时容纳的最大顾客数
    private static final int TOTAL_CUSTOMERS = 15;  // 模拟中的总顾客数
    private static final int CUSTOMER_INTERVAL = 1000;  // 顾客到达的时间间隔
    private static final int COOK_TIME = 500;       // 厨师准备一份菜品所需时间
    private static final int EAT_TIME = 1500;       // 顾客用餐时间
    private static final int OPEN_TIME = 5000;      // 餐厅开业时间

    public static void main(String[] args) throws InterruptedException {
        // 创建两个Semaphore实例，用于控制厨师和顾客的并发访问
        Semaphore dishesSemaphore = new Semaphore(MAX_DISHES, true);  // 控制厨师准备菜品的信号量
        Semaphore customersSemaphore = new Semaphore(MAX_CUSTOMERS, true);  // 控制顾客进入餐厅的信号量

        // 厨师线程
        Thread cook = new Thread(() -> {
            for (int i = 0; i < TOTAL_CUSTOMERS; i++) {
                try {
                    dishesSemaphore.acquire();  // 获取一个菜品的信号量，表示厨师开始准备菜品
                    Thread.sleep(COOK_TIME);   // 模拟厨师准备一份菜品的时间
                    System.out.println("Cooked a dish. Dishes available: " + dishesSemaphore.availablePermits());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "Cook");

        cook.start();  // 启动厨师线程

        Thread.sleep(OPEN_TIME);  // 等待餐厅开业时间

        // 顾客线程
        for (int i = 0; i < TOTAL_CUSTOMERS; i++) {
            Thread.sleep(CUSTOMER_INTERVAL);  // 模拟顾客到达的时间间隔

            new Thread(() -> {
                try {
                    System.out.println("Customer arrived.");
                    customersSemaphore.acquire();  // 获取一个顾客的信号量，表示顾客进入餐厅

                    dishesSemaphore.release();  // 顾客获取到菜品后，厨师可以准备新的菜品
                    System.out.println("Customer is eating. Dishes available: " + dishesSemaphore.availablePermits());

                    Thread.sleep(EAT_TIME);  // 模拟顾客用餐时间

                    customersSemaphore.release();  // 顾客用餐完毕，释放顾客信号量，允许下一位顾客进入
                    System.out.println("Customer left.");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, "Customer-" + (i + 1)).start();  // 启动顾客线程
        }

        cook.join();  // 等待厨师线程结束

        // 等待所有顾客离开
        for (int i = 0; i < MAX_CUSTOMERS; i++) {
            customersSemaphore.acquire();  // 获取所有顾客信号量，确保所有顾客都已离开
        }

        System.out.println("All customers left.");
    }
}

