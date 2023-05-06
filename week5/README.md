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
