# week4 discussion 笔记

这周有两次 discussions，第二次的题目来自前几年课程的考试题。

## Inheritance Exam-Level Problem 2 解题记录

（看以下内容前要先读 Problem 2 题）

解第二题需要先推理，得出题目要求我们做什么。

题目中的 IntNode 的 max() 方法用了递归，但是没写 base case，显然递归的过程到了最后一个节点会出现问题，单向链表最后一个节点的 next 一般都为 null。但题目要求不能改 IntNode 的 max() 方法，但指出了另一个方向：
  1. 链表 DMSList 只会从头部插入节点，节点都是 IntNode；
  2. 给了一个 LastIntNode 类，要求实现其构造函数及它的 max() 方法
  3. DMSList 的构造函数留空要求实现

结合 LastIntNode 这个类的名字和留空的 DMSList 构造函数，可以推断出题目要求在 DMSList 的构造函数中将 LastIntNode 作为链表的最后一个节点，所以它的 max() 方法应该返回 0，成为 base case，而 DMSList 的构造函数就是让 sentinel 节点的 next 指向一个 LastIntNode 对象；

LastIntNode 由于继承了 IntNode 类，且 IntNode 的构造函数需要传参，所以 LastIntNode 的构造函数要调用父类构造函数且传参，即 `super(0, null)`

