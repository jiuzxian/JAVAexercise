- ctrl+alt +L 排版
- ctrl+f/r搜索/替换
- /**+ ↵ 大注释

### 实体类方法的static

当使用`static`关键字声明变量和方法时，这些成员将属于类本身，而不是类的每个实例。这意味着无论创建多少个类的实例，静态成员的内存空间只有一份，被所有实例共享。

1. 静态变量的例子：
```java
public class Counter {
    // 静态变量，被所有实例共享
    public static int count = 0;

    // 实例变量，每个对象实例都有自己的副本
    public int instanceCount = 0;

    // 构造方法
    public Counter() {
        count++; // 每创建一个对象实例，静态变量 count 增加一次
        instanceCount++; // 每创建一个对象实例，实例变量 instanceCount 增加一次
    }
}
```

在这个例子中，`count`是一个静态变量，它被所有类的实例所共享。每当创建一个新的`Counter`对象时，静态变量 `count` 的值都会增加一次。而`instanceCount`是实例变量，它属于类的每个实例，每个实例都有自己的副本。

```java
Counter counter1 = new Counter();
System.out.println(counter1.count); // 输出：1
System.out.println(counter1.instanceCount); // 输出：1

Counter counter2 = new Counter();
System.out.println(counter2.count); // 输出：2（count 是静态变量，被所有实例共享）
System.out.println(counter2.instanceCount); // 输出：1（instanceCount 是实例变量，每个实例都有自己的副本）
```

2. 静态方法的例子：
```java
public class MathUtils {
    // 静态方法，不依赖于类的实例，可以通过类名直接调用
    public static int add(int a, int b) {
        return a + b;
    }

    // 实例方法，需要通过对象实例来调用
    public int multiply(int a, int b) {
        return a * b;
    }
}
```

在这个例子中，`add`是一个静态方法，可以通过类名直接调用，而不需要创建类的实例。`multiply`是一个实例方法，需要通过对象实例来调用。

```java
int sum = MathUtils.add(2, 3); // 静态方法可以通过类名直接调用
System.out.println(sum); // 输出：5

MathUtils mathUtils = new MathUtils();
int product = mathUtils.multiply(4, 5); // 实例方法需要通过对象实例来调用
System.out.println(product); // 输出：20
```

总结：
- 使用`static`关键字声明的变量和方法属于类本身，被所有实例共享。
- 静态变量在类加载时初始化，无需等待实例化。
- 静态方法不依赖于类的实例，可以通过类名直接调用。

### 实体类方法的public和private

1. `public`方法：
   - 使用`public`修饰符可以在任何地方都能访问和调用这些方法，包括类外部的代码。这样可以方便地获取和设置`data`字段的值。
   - `public`方法适用于希望外部代码能够直接操作字段的情况。这可能在某些场景下很方便，但也需要谨慎使用，因为直接暴露字段的访问可能会导致数据的不一致或不安全。
2. `private`方法：

   - 使用`private`修饰符可以限制方法的访问范围，只有**类内部的其他方法**可以调用这些私有方法，而外部代码无法直接访问和调用。
   - `private`方法适用于在类内部需要执行某些辅助逻辑或验证的情况。这样可以隐藏实现细节，保持类的封装性，避免外部代码直接操作内部状态。


### 数据库返回值判空用工具类

```java
         //对employeesList对象判空
	    if (Objects.isNull(employeesList)) {
            return Result.fail();
        }//对employeesList是否包含元素判空
        else if(employeesList.isEmpty()){
            return Result.fail(101,"未找到该员工！");
        }
		 //对employeesList对象和是否包含元素同时判空
		 //ObjectUtils, ArrayUtils.....
		if(CollectionUtils.isEmpty(employeesList)){
             return Result.fail(101,"未找到该员工！");
         }

```

### 事务

- 事务@Transactional由spring控制时，它会在抛出异常的时候进行回滚。如果自己使用**try-catch**捕获处理了，是不生效的。如果想事务生效可以进行手动回滚或者在catch里面将异常抛出throw new RuntimeException();有两种方法


**方案一：**手动抛出运行时异常(**缺陷是不能在catch代码块自定义返回值**)

```java
try{
      ....  
  }catch(Exception e){
      logger.error("fail",e);
      throw new RuntimeException;
}
```

**方案二：**手动进行回滚 **TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();**

```java


try{
      ...
  }catch(Exception e){
      log.error("fail",e);
    //手动进行回滚
      TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
      return false;
}
```


