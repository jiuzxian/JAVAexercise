# MYSQL要点

### 显式连接和隐式连接

显式连接和隐式连接都是数据库查询中的概念，它们都是用于从两个或更多的表中获取数据。

1. 显式连接（Explicit JOIN）：显式连接使用JOIN关键字明确地指明要连接的表和连接条件。它的语法通常是：SELECT ... FROM table1 JOIN table2 ON condition。显式连接比较直观，因为你可以清楚地看到连接发生在哪里以及连接的条件是什么。例如：
   
   ```sql
   SELECT Orders.OrderID, Customers.CustomerName
   FROM Orders
   JOIN Customers ON Orders.CustomerID = Customers.CustomerID;
   ```

2. 隐式连接（Implicit JOIN）：隐式连接则是在WHERE子句中指定连接条件，而不使用JOIN关键字。它的语法通常是：SELECT ... FROM table1, table2 WHERE condition。虽然隐式连接的语法较短，但它可能会造成混淆，尤其是在查询涉及到多个表和复杂条件时。例如：

   ```sql
   SELECT Orders.OrderID, Customers.CustomerName
   FROM Orders, Customers
   WHERE Orders.CustomerID = Customers.CustomerID;
   ```

现代的SQL标准和大多数数据库推荐使用显式连接，因为它的语法更清晰，更易于阅读和维护。

### IN:在WHERE中指定多个值

```sql
SELECT column_name(s)
FROM table_name
WHERE column_name IN (value1, value2, ...);
```

