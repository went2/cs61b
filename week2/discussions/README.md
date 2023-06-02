# week 2 discussions

## 第二次讨论作业

作业的题目来自考试题，考查基本数据数据结构(Map, List)的使用，以及类的静态方法、实例方法的区别。

作业要求：[Introduction to Java - Exam Level 2](https://drive.google.com/file/d/1uVmerLST6KTWih6JFgB2XwpKWH8XiXPX/view)

作业分两部分：

- 第一部分，设计一个衣柜（Closet）类，用于保存衣服（Clothing），并根据要求对保存的衣服进行操作。
- 第二部分，给定一个 Book 类和一个 Library 类的实现，回答问题。

### 回答与体会

第一部分的回答见 `./Closet.java`，第二部分的回答见 `./BookLibrary.java`

总体体会：

- 完成第一部分使用较多嵌套数据类型，如 `Map<String, List<Clothing>> colorItems`，表示 colorItems 是个 Map 对象，它的 key 是字符串类型，value 是一个 List 类型。这个 List 类型中的每个元素是 Clothing 类型。以前写 TypeScript 时对类型中嵌套类型的写法很抓马，哪知切换到 Java 后，这种写法变得很自然，其中的原因，我认为是 Java 提供了很多实用的数据类型，使用变量的时候会先思考要用哪种结构保存数据，反观 JavaScript，保存数据最常用的是对象，或者把对象放到数组中，JS 中的对象的功能太宽泛了，什么数据都能用对象表示，以至于可以略过选择数据结构的步骤，直接进入到对象的创建和使用中。

- 第二部分要求区别类的静态属性、静态方法、实例属性和实例方法的使用，这里得到的经验是，1）对所有实例都一样的属性，可以用静态变量保存，没必要每个实例都保存一份属性；2）可以在实例方法中访问静态属性，但不要修改静态属性。

## 第三次讨论作业

第三次讨论叫 Tutor Review Session（导师引导的复习环节） 共 8 道练习：
  - 题目[Tutor Review Session](https://drive.google.com/file/d/135cZ5OyhJzvrkc-wpj7KRiwCNCuNWTkB/view)
  - 答案[solutions](https://drive.google.com/file/d/147wgz1ztOTuq6Rgfcvs3dW-xu93ZNE8c/view?usp=sharing)
  - 我的练习见 `./TRS1.java`

其中一道题目是翻转链表，我用循环实现了一次，但没想通用递归怎么实现，我自己的实现如下：

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