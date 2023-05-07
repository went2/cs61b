# week5 课程纲要

本周将结束 Java 基本语法，进入课程 phase2 阶段：Data Structure。

## Lectures

- Java 中的 Set 结构，基于 Array 实现 Set 结构 ArraySet
- 使 ArraySet 支持增强版的 for 循环
  1. 在类中添加一个 `iterator()` 方法，返回一个 `Iterator<T>` 对象，[Iterator<T>](https://docs.oracle.com/javase/8/docs/api/java/util/Iterator.html)是一个接口，它要求实现 `hasNext()` 和 `next()` 方法
  2. 在自己数据结构类中创建类，实现 Iterator 接口
- 添加 toString 方法，直接拼接字符串性能较低，可用 StringBuilder 生成字符串
- 添加 equal(Object) 方法：

详见 `./lectures/README.md`

写有效的程序，有效体现在两方面:
    1. 人，开发程序的消耗，开发一个程序需要多长时间，维护程序需要多大精力（大部分 cost 用在维护程序上）
    2. 机器，执行消耗，执行程序花费多少时间，占用多少内存

课程的后续部分围绕 2 展开。

- 渐进分析：在输入 N 非常大的情况下，计算函数的 order of growth
- Big-Theta：θ(f(N)) 表示函数 f(N) 的 order of growth 的记法
- Big-O：O(f(N)) 表示小于等于函数 f(N) 的 order of growth 的记法
  For example, the following statements are all true:

## Lab05 深入探究 Git 和 Debugger 的用法

[Lab 05: Advanced Git and Debugging](https://sp23.datastructur.es/materials/lab/lab05/)

- Git： 解决本地分支与远程分支文件的冲突。需要配合 Grandscope 课程评分系统使用
- Debugger：