# 使用数组实现 List 结构

列表（List） 是一种抽象线性结构，它可以通过索引 i 访问列表中的第 i 的元素。在 Java 中列表可以用链表和数组实现。

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

一开始，我用自己的思路，遇到问题：Java 中的数组必须在初始化时确定大小，而题目要求在初始化 AList 时得到一个大小为 0 的列表，我就把 AList 的大小和用来实现它的数组的大小混为一谈，试图在此基础上实现其他方法。照此思路，初始化 AList 时，创建一个大小为 0 的数组，接着在实现 `addLast(int x)` 时，就创建一个比当前数组大小大 1 的新数组，将当前数组的所有元素复制到新数组。这种实现方式在写的时候很怪，尽管最后通过测试，但性能很差。

直到看到 Josh Hug 在 AList 类的开头就写了一个 `size` 成员变量，以及在构造函数初始化一个大小为 100 的数组，就明白过来了：使用者使用 AList 的 `size` 属性，这跟实现它的数组的 `length` 是**两个需要区分的概念**，这就是用类来封装实现，只暴露简单接口的体现。
