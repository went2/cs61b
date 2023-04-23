# week2 课程纲要

本周课程主题：线性数据结构。分别介绍两种

# 使用 Array 实现 List 结构

列表（List） 是一种抽象线性结构，它可以通过索引 i 访问列表中的第 i 的元素。在 Java 中列表可以用链表（Linked-list） 和数组（Array）实现。

基于数组而不是链表的实现的优势主要体现在：数组的随机读取的时间复杂度是常量，而随机读取链表则和链表的大小相关。

cs61B 课程中需要实现具有以下特性的列表结构：

```java
class AList {
    public AList() {}

    /** Inserts X into the back of the list. */
    public void addLast(int x) {}

    /** Returns the item from the back of the list. */
    public int getLast() {}
    /** Gets the ith item in the list (0 is the front). */
    public int get(int i) {}

    /** Returns the number of items in the list. */
    public int size() {}

    /** Deletes item from back of the list and
     * returns deleted item. */
    public int removeLast() {}
}
```

我用自己的思路实现时，主要的问题在于，Java 中的数组必须在初始化时确定大小，而题目要求在初始化 AList 时得到一个大小为 0 的列表。

我把 AList 的大小和用来实现它的数组的大小合并成一个概念，在初始化 AList 时，初始化一个大小为 0 的数组，之后每次在数组中添加一个元素，就创建一个比当前数组大小大 1 的新数组，再讲当前数组的所有元素复制到新数组。

这种实现方式在写的时候怪怪的，尽管最后能通过测试，但可想而知添加一个元素就进行一次数组复制的操作的性能消耗也太大了。

直到看到 Josh Hug 写到 AList 要有一个 `size` 成员变量，以及在构造函数初始化一个大小为 100 的数组，马上明白过来了：对外使用的 AList 的大小和实现它的数组的大小是**两个需要分开的概念**。
