# **Springboot及插件注解**
1. **@NoArgsConstructor：**生成无参的构造方法。

2. **@AllArgsConstructor：**生成该类下全部属性的构造方法。

3. **@RequiredArgsConstructor：**生成该类下被final修饰或者non-null字段生成一个构造方法。

4. **@RestController**和 **@Controller**

   是 Spring Framework 中用于处理 Web 请求的注解，它们之间有一些区别：
   1. **功能不同**:
      - **@Controller:** 用于标识一个类是 Spring 的控制器(Controller)类，通常用于处理 Web 请求并返回视图页面。
      - **@RestController:** 是 Spring 4.0 引入的一个注解，结合了 `@Controller` 和 `@ResponseBody`。它表示该类是一个控制器，但是其中的方法都会以 JSON/XML 等形式直接返回给客户端，而不是返回视图页面。适用于 RESTful 风格的 Web服务，通常用于返回数据。
   2. **返回类型不同**:
      - 在 **@Controller** 注解的方法中，返回值通常是一个字符串，表示视图的名称或是视图的路径，Spring 将根据该字符串去寻找对应的视图页面并返回给客户端。
      - 在 **@RestController**注解的方法中，返回值将会直接作为响应体返回给客户端，不会经过视图解析器处理。默认情况下，返回的数据会以 JSON 格式返回。
   3. **@ResponseBody**:
      - 使用**@Controller** 注解时，如果希望某个方法返回 JSON 或其他数据类型，需要在该方法上添加 `@ResponseBody` 注解。
      -  **@RestController**中的所有方法默认都会使用 `@ResponseBody` 注解，无需再额外添加。

5. **@EqualsAndHashCode：**Lombok 会自动为该类生成 `equals()` 和 `hashCode()` 方法的实现，方法会根据类的所有非静态（non-static）属性自动生成比较和计算哈希码的逻辑。

6. **@Accessors(chain = true)：**Lombok 会为该类生成链式调用风格的 Setter 方法，使得我们可以在一行代码中连续设置多个属性的值。`person.setName("John").setAge(30).setAddress("New York");`