# week4 课程纲要

## 1. Lectures

- 实现继承（Implementation Inheritence），子类继承父类的实现。
- 封装
- 编译时类型检测
- 高阶函数
- 子类多态
- Java 中使用 interface 实现其他语言中 callback 的功能

详见 `./lectures/README.md`

## 2. Discussion/Review

这周开始会把 Discussion/Review 部分的作业单保存下来，于 `./discussions` 下

- [Inheritance（discussion04）](https://drive.google.com/file/d/19GsS_5ZH-UQU1wSifvRNdqC2Qgueb7_L/view)：继承与静态类型校验的练习题
  - 一个变量的静态类型声明为父类，则调用静态方法时，调用的是父类的静态方法
  - casting 会让编译器通过静态类型检查，但可能会在运行时报错 ClassCastException

- [Inheritance(Exam-Level 04)](https://drive.google.com/file/d/1R7Zxxt_xu-gIZuJXsqeXkGn-T_hgUZ0A/view?usp=share_link)：区分变量的静态类型与动态类型的练习题
  - 写变量的静态类型和动态类型过程中，感觉到对象会产生什么行为要先找到对象属于什么类

## 3. Lab

这周的 Lab 是完成 Project 1

## 4. Assignments/Exams

- 在校学生参加第一次期中考试。
- 提交 Project 1。

## 5. week4 学习回顾

这周内容印象较深的一是做了很多练习题来区别一个变量的编译时类型和运行时类型，二是 Java 用 interface 的机制实现其他语言中 callback 的功能，因为 Java 中的一切都是让对象干的。

这周的编码任务是延续上周的 Project 1B。

调整了学习策略，现在改为，周一的 lecture 看完后，完成所有的 discussions；周三的 lecture 看完后，完成 lab；周五的 lecture 完成后，写 project。