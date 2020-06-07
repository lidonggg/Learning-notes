## Mysql 小贴士



### count(null) 与 sum(null)

Mysql 与 Oracle 表现一致：

- count(null) 返回 0
- sum(null) 返回 null



### count(1) 与 sum(1)

1. count(1) 返回 0:
   - 如果所查询的表或者where条件筛选后得到的结果集为空，则 count(1) 返回为 0
2. Count(1) 返回 null：
   - 如果所查询的表或者where条件筛选后得到的结果集为空且当前层查询中使用了 group by ，则 count(1) 返回为 NULL
3. sum(1) 返回 0：
   - 可以使用 IFNULL(expression_1,expression_2)，表示如果 expression_1 不为NULL，则 IFNULL 函数返回 expression_1; 否则返回 expression_2 的结果。
4. sum(1) 返回 null：
   - 如果所查询的表或者where条件筛选后得到的结果集为空 ，则 sum(1) 返回为 NULL