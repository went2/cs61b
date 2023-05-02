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

一个双端队列需要实现的接口有：
  - addFirst(T x)：向列表头部增加元素 x
  - addLast(T x)：向列表尾部增加元素 x
  - removeFirst()：取出列表头部元素
  - removeLast()：取出列表尾部元素
  - size()：返回列表元素个数
  - isEmpty()：返回列表是否为空
  - get(int index)：返回列表指定索引位置的元素，索引为负或过大，返回null
  - toList()：将列表元素按相同顺序存放到一个 List 结构中，这是工具方法，用于测试，不讲它看做 Deque 本身的功能。

proj1a 用双向链表实现这些接口，可以达到除了 `get()` 操作外所有的操作的时间复杂度都为常数。这里用环形的方式实现双向链表，链表入口节点 `sentinel`:
  - sentinel.next：指向第一个节点，没节点时，保存 sentinel 本身
  - sentinel.prev：指向最后一个节点，没节点时，保存 sentinel 本身
  - 链表中的最后一个节点的 `next` 指向 `sentinel` 节点

实现上述接口的操作主要是断开现有节点的链接，建立新链接的过程，这里不细表。双向链表实现的 `get()` 方法由于要遍历一次链表节点，在链表节点足够多时，性能不佳，我们希望引入数组的读取操作时间为常量的特性，实现这些接口。

使用数组实现双端队列面临的问题：
  1. 容量问题：Java 数组的容量在创建时确定，往其中增加元素时总会达到容量上限，需要在达到数组容量时另新建大容量数组替换当前数组；
  2. 索引问题：数组靠索引存取值，双端队列主要从队首、队尾存取值，所以要用数组的索引模拟出队首、队尾的概念；

1. 如何用索引模拟队首队尾？

首先处理用索引模拟队首队尾的问题，这里我采取了 Josh Hug 的思路，用变量保存下一个队首、下一个队尾的位置，将 `ArrayList` 类设置这些实例属性：

```java
public class ArrayList<T> implements Deque<T> {
  private T[] arr; // 保存元素的数组，内部使用
  private int nextFirst; // 数组索引，表示列表的下一个队首位置
  private int nextLast; // 数组索引，表示列表的下一个队尾位置
  public int size; // 队列元素的个数

  public ArrayList() {
    T[] arr = new Object[8]; // 初始化长度为 8 的数组
    size = 0;
    nextFirst = arr.length - 1;
    nextLast = 0;
  }
}
```

为什么要有 `nextFirst` 表示下一个队首，`nextLast` 表示下一个队尾？队列的头和尾不是固定的吗？

因为我们要区分两组概念，一组是使用者使用 Deque 结构时的需要了解的概念，这些概念通过 Deque 的接口表达出来；另一组是，实现者为了实现接口的逻辑，而对底层的结构进行操作产生的概念。

用数组是种固定的结构，能变的只有索引，表示头尾的索引是不断在数组间伸缩的，用 `nextFirst` 表示下一次 `addFirst` 时将插入的位置，`nextLast` 表示下一次 `addLast` 调用将插入的位置。并且，我们规定 `nextFirst` 只能向左移动，它的初始值 7（arr.length -1），`nextLast` 只能向右移动，初始值 0。

这样 `addLast` 对应从数组第一个位置开始依次向数组添加元素的操作；
`addFirst` 对应从数组最后一个位置开始向前添加元素的操作；
`removeFirst` 是将 `nextFirst` 向右移动一位，返回这一位的元素；
`removeLast` 是将 `nextLast` 向左移动一位，返回这一位的元素；

第一次遇到这种实现方式，会觉得奇怪，为什么 `addFirst` 是从后往前添加？这是因为我们将数组当成了一个环形结构，为什么要用环形结构处理数组，为了防止读取索引时越界。

这样处理后，会得到一个规律（invariant，不变式）：从索引 `nextFirst + 1` 的元素开始，从左往右依次在数组中读取当前队列的长度，即可得到当前队列顺序的元素。

这是一个重要规律，这个规律会用到数组的扩容、减缩和队列的 `get()` 方法实现中。

另外提一下实现环形数组的方式：取模，以 `addFitst()` 实现为例：

```java
    @Override
    public void addFirst(T x) { 
      // 在 nextFirst 索引处设置新值，更新 nextFirst 与 size
        if(size == arr.length) { // 数组扩容，并更新索引
            resizing(size * RFACTOR);
        }

        arr[nextFirst] = x;
        // 插入完成后左移一个索引。左移时可能会越过数组左边界，额外加 arr.length 再求模
        nextFirst  = (nextFirst - 1 + arr.length) % arr.length;
        size += 1;
    }
```

`addFitst()` 实现是在 nextFirst 索引处设置新值，更新 nextFirst 与 size，期间要考虑数组扩容问题。

当插入元素完成，`nextFirst` 要左移一位，表示下一个插入的头部尾部，我们不用 `nextFirst--`，而是 `nextFirst  = (nextFirst - 1 + arr.length) % arr.length`，其目的是左移到 -1 时越过数组左边界，-1 其实表示的是 `arr.length` 加上 -1。所以给 nextFirst 额外加一份 arr.length 再求模。

addLast 中的处理是：`nextLast = (nextLast + 1) % arr.length`

插题外话：

这个规律的发现纯属偶然，当时我正苦恼怎么用数组中元素的顺序表示队列中元素的顺序，因为我把队列 addFirst 操作的元素按从右到左的方向插入数组，addLast 的方向从左到右插入数组，而这些元素又同时存在于一个数组，给我一种混在一起的感觉。当时的想法是把按 addLast 方式插入的分期一组，按 addFirst 插入的分为一组，然后计算当前的 last 元素与初始 last 位置的偏移，通过遍历数组得到这部分元素，依次逻辑再得到队首部分的元素，然后再将两部分拼接到一起，生成和表观的 Deque 顺序一样的元素列表。

这种思路没经实现就放弃了，我驾驭不了其中的复杂性，复杂性高就是同一时刻要照顾的变量太多了，大脑算不过来，心容易乱。我强迫自己用笨方法在纸上画出 8 格数组，然后模拟队首队尾插入、读取的操作，再将数组中的元素的顺序和 Deque 元素顺序比较，画了几种情况，数组满了的情况、右边越界的情况、左边越界的情况等等，试图从这些具体例子中找 pattern，画着画着突然就发现了这个规律。发现规律后，大脑就再也回不去没发现前的混沌状态，觉得这事骤然清晰，本来就该这么算，不知道这种突然到来的领悟是不是之前的笨方法操作产生的，这让我受到鼓励，在解后面的问题时，我也应当多从实例入手找规律。

2. 队列容量超过数组容量时，如何为数组扩容？

- 创建一个容量为当前数组容量乘以一个因子（如 2）的新数组；
- 将当前数组按照表观 Deque 元素顺序取出，从新数组从头开始的位置依次插入；
- 更新基于新数组的 nextFirst 和 nextLast 值，将新数组设为当前的可用的数组；

这里将数组扩容的实现贴一下：

```java
// 数组扩容和缩小的实现一样：将当前所有元素依次放到新数组从头开始处，只是新数组容量不同
private void resizing(int capacity) {
    T[] newArr = (T[]) new Object[capacity];
    // 不变式：从 nextFirst + 1 位置开始向右读取元素，读取 size 长度，即为表观 Deque 中元素的顺序
    // 将读取出来的元素放到新数组的开头
    // i 原数组索引，用取模处理越界，n 控制读取次数与新数组的索引
    for(int i = (nextFirst + 1) % arr.length, n = 0; n < capacity; n+=1, i = (i + 1) % arr.length) {
        newArr[n] = arr[i];
    }

    // initiate nextFirst and nextLast
    nextFirst = newArr.length - 1;
    nextLast = arr.length;
    arr = newArr;
}
```

3. 如何验证我实现的方法功能正常？

先写测试，再写实现，再跑测试。

每个实现的方法我都写了测试，这也是 project1b 要求做到的，跑测试 + IntelliJ Debuger 帮我指出了几个实现中的问题，这是我之前写前端代码没有的体验。