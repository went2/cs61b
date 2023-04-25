# Exerceses

第二周第三个 Lecture 的 Guide 中的有道练习题，来自 Spring 2015 学期期中考卷的第 7 题，花了较长时间才明白，做个记录。

（待记录一个 Guide 习题）

第二周第三个 Discussion/Review 是 8 道练习：
  - 题目[Tutor Review Session](https://drive.google.com/file/d/135cZ5OyhJzvrkc-wpj7KRiwCNCuNWTkB/view)
  - 答案[solutions](https://drive.google.com/file/d/147wgz1ztOTuq6Rgfcvs3dW-xu93ZNE8c/view?usp=sharing)
  - 我的练习见 `./TRS1.java`

其中一道题目是翻转链表，我用循环实现了一次，但没想通用递归怎么实现，我的实现如下：

```java
/** Destructively reverses IntList L using recursion. */
public static IntList reverseDestructive(IntList l) {
  IntList prev = null;
  IntList curr = l;
  while(curr != null) {
    // 暂存下一个
    IntList temp = curr.rest;
    // 将当前节点的指向从下一个改为上一个
    curr.rest = prev;
    // 保存当前节点为下一次替换的上一个
    prev = curr;
    curr = temp;
  }
  return prev;
}
```

参考答案用的是递归，我看了一下有所领悟，写递归关键要想出来哪个小步骤是可以不断重复的。就翻转链表来说，这个步骤是：在本次操作中，将下一个节点的 next 指向当前节点。

当前节点的值由参数传入，下一个节点的值由递归调用返回。随着递归的展开，函数调用栈相继叠加，每个调用栈中都保存了链表中的一个节点。最后一个调用栈处理最后一个节点，它直接返回该节点，在倒数第二个调用栈中，就得到了变量名为 `L` 的倒数第二个节点，变量名为 `reversed` 的最后一个节点，此时开始翻转，将最后一个节点的 next 指向当前节点 `L`。

```java
/** Destructively reverses IntList L using recursion. */
public static IntList reverseDestructiveRecur(IntList L) {
  if (L == null || L.next == null) {
    return L;
  } else {
    IntList reversed = reverseDestructive(L.next);
    L.next.next = L;
    L.next = null; // 保证第一个节点翻转后的 next 为 null，其他节点的 next 会在本调用栈返回后进入的上一个调用栈中被覆盖。
    return reversed;
  }
}
```




