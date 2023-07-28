# week5 课程纲要

本周将结束 Java 基本语法，进入课程 phase2：Data Structure。

## Lectures

详见 `./lectures/README.md`

### lecture 12

- Java 中的 Set 结构，基于 Array 实现 Set 结构 ArraySet
- 使 ArraySet 支持增强版的 for 循环
  1. 在类中添加一个 `iterator()` 方法，返回一个 `Iterator<T>` 对象，[Iterator<T>](https://docs.oracle.com/javase/8/docs/api/java/util/Iterator.html)是一个接口，它要求实现 `hasNext()` 和 `next()` 方法
  2. 在自己数据结构类中创建类，实现 Iterator 接口
- 添加 toString 方法，直接拼接字符串性能较低，可用 StringBuilder 生成字符串
- 添加 equal(Object) 方法：

写有效的程序，有效体现在两方面:
    1. 人，开发程序的消耗，开发一个程序需要多长时间，维护程序需要多大精力（实际上一个项目大部分精力都用在维护上）
    2. 机器，执行消耗，执行程序花费多少时间，占用多少内存

课程的后续部分围绕 2 展开。

### lecture 13 渐近分析

- 渐进分析：在输入 N 非常大的情况下，计算函数的 order of growth
- Big-Theta：θ(f(N)) 表示函数 f(N) 的 order of growth 的记法
- Big-O：O(f(N)) 表示小于等于函数 f(N) 的 order of growth 的记法

### lecture 14 Disjoint Sets

- 不交集数据结构的实现
    - Weighted Quick Union 规则：合并时，节点较少的树增加到节点多的树，可使得整个树的高度最大为 logN。

## Lab05 深入探究 Git 和 Debugger 的用法

[Lab 05: Advanced Git and Debugging](https://sp23.datastructur.es/materials/lab/lab05/)

- Git： 解决本地分支与远程分支文件的冲突。需要配合 Grandscope 课程评分系统使用
- Debugger：在调试中使用表达式

详见 `./lab05/README.md`

## Assignment

本周作业 [project1c](https://sp23.datastructur.es/materials/proj/proj1c/)
    - 双端队列增强：添加迭代器、toString、equals 方法；
    - ArrayDeque 增强：实现 MaxArrayDeque 类，增加 max 方法，队列中的最大值，比较规则由传入的 Comparator 确定。
    - Guitar Hero：用以上的数据结构实现一个算法模拟吉他琴弦的拨动效果

## 小结

本周课程内容开始正式步入数据结构，介绍了第一个数据结构不交集数据结构的几种不同实现，对比了这些实现之间的运行时性能。本周的项目很有意思，是根据 Karplus-Strong 算法完成一个吉他拨弦声音的合成，并且用键盘上的 37 键模拟出从 110HZ 到 880 HZ 的 37 个音，这属于音频数字采样领域的工作，就作业本身来说，没有乐理相关的先备知识，也能完成 project1c。因为项目代码的注释提供了完整的指示。这次以完成任务前进学习进度为要，不对相关的背景知识做调查了，后续碰到再一并整理。