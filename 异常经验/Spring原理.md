AOP

<img src="D:\thing\JAVAdemo\src\异常经验\img\aop.png" style="zoom: 80%;" />

要理解**切面**编程，就需要先理解什么是切面。用刀把一个西瓜分成两瓣，切开的切口就是切面；炒菜，锅与炉子共同来完成炒菜，锅与炉子就是切面。web层级设计中，web层->网关层->服务层->数据层，每一层之间也是一个切面。**编程中，对象与对象之间，方法与方法之间，模块与模块之间都是一个个切面。**

- Aspect（切面）： Aspect 声明类似于 Java 中的类声明，在 Aspect 中会包含着一些 Pointcut 以及相应的 Advice。
- Joint point（连接点）：表示在程序中明确定义的点，典型的包括方法调用，对类成员的访问以及异常处理程序块的执行等等，它自身还可以嵌套其它 joint point。
- Pointcut（切点）：表示一组 joint point，这些 joint point 或是通过逻辑关系组合起来，或是通过通配、正则表达式等方式集中起来，它定义了相应的 Advice 将要发生的地方。
- Advice（增强）：Advice 定义了在 Pointcut 里面定义的程序点具体要做的操作，它通过 before、after 和 around 来区别是在每个 joint point 之前、之后还是代替执行的代码。
- Target（目标对象）：织入 Advice 的目标对象.。
- Weaving（织入）：将 Aspect 和其他对象连接起来, 并创建 Adviced object 的过程

![](D:\thing\JAVAdemo\src\异常经验\img\概念.png)

参考：[细说Spring——AOP详解（AOP概览）_Jivan2233的博客-CSDN博客](https://blog.csdn.net/q982151756/article/details/80513340?ops_request_misc=%7B%22request%5Fid%22%3A%22169147663316800227445943%22%2C%22scm%22%3A%2220140713.130102334..%22%7D&request_id=169147663316800227445943&biz_id=0&utm_medium=distribute.pc_search_result.none-task-blog-2~all~top_positive~default-1-80513340-null-null.142^v92^insert_down28v1&utm_term=aop&spm=1018.2226.3001.4187)







