# week3 projects 实现 Deque

本周的项目实作有两个：

- [proj1a](https://sp23.datastructur.es/materials/proj/proj1a)：详细介绍了编写一个测试用例的步骤，并一步一步介绍了如何采用测试驱动开发（TDD）的方式，实现一个基于双向链表的 Deque（双端队列）结构。
- [proj1b](https://sp23.datastructur.es/materials/proj/proj1b/)：要求自己实现一个基于数组的 Deque 结构。

记录完成这个项目过程中的获得：

## 编写测试用例的模式

编写测试可用 JUnit 或 Google 的 [Truth](https://truth.dev/) 断言库，本课程使用 Truth，它易读易写、对列表错误的提示信息更友好。

编写测试用例常用 Arrange-Act-Assert（组织-实施-验证？）模式：
  1. 组织测试用例，初始化一些用于测试的数据结构；
  2. 实施测试，将测试应用到测试数据结构上；
  3. 验证第 2 步的结果

一个测试用例举例：

```java
@Test
public void addLastTestBasic() {
    Deque<String> lld1 = new LinkedListDeque<>();

    lld1.addLast("front"); // after this call we expect: ["front"]
    lld1.addLast("middle"); // after this call we expect: ["front", "middle"]
    lld1.addLast("back"); // after this call we expect: ["front", "middle", "back"]
    assertThat(lld1.toList()).containsExactly("front", "middle", "back").inOrder();
}
```

- `@Test` 告诉 Java 此方法个测试，当我们跑测试时要跑这个方法；
- Arrange: 构建了一个新的 Deque 结构，向其中添加了 3 个元素；
- Act: 调用了 Deque 的 toList 方法，得到一个列表，列表中的元素中的顺序取决于上一步 调用 addLast 方法的顺序；
- Assert: 用了一句 Truth 断言来检验 toList 得到的结果列表中元素的顺序是否符合期望。

## 基于数组实现双端队列的问题与解决
