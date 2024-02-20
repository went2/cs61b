# week7 lectures 笔记

## lecture 17 B-Tree 介绍（以及 2-3, 2-3-4 Trees）

[video](https://www.youtube.com/watch?v=0SCtnf84QrI&list=PL8FaHk7qbOD41EHkD7CgQuRw1jpH_Fv7-&index=4)

[slides](https://docs.google.com/presentation/d/1mNAtzfc7Mna1rpVzoTcrn9p8ejWIh72QBuEqOJwQzbk)

### 1. θ 比 O 记法更准确

- BST 的高度在最差情况下是 θ(N)，表示 BST 的高度在最差情况下能达到 θ(N)
- BST 的高度在最优情况下是 θ(logN)，表示 BST 的高度在最优情况下能达到 θ(logN)
- BST 的高度是 O(N)，表示 BST 的高度总是小于等于 O(N)

前两种说法比第三种说法更准确，举个例子，旅馆A 的房间最高价是 640元/晚，旅馆B 的房间价都小于等于 640元/晚，问上面两种说法哪种更能得到确定的信息？答案是第一种，我们可以大致判断旅馆A是个档次较高的旅馆，对于旅馆B 的档次则得不出什么判断，因为符合小于等于的情况太多了。旅馆 A 的说法就是 θ 表示的含义：会达到、等于，旅馆B 的说法就是 O 的含义：小于等于。

所以从实际情况下，big O 不表示 worst case，但现实生活中，big O 更常用，我们见到一个算法的运行时是 O(N)时，我们假定它想表达的是这个算法的 worst case 就是 N。

### 2. B-Tree：一种对 BST 结构的改进

BST（二分查找树）的高度增长最优是 O(logN)，最差是O(N)，B-Tree 在 BST 概念的基础上改进，使之避免最差的情况。B 树的高度增长的最优最差情况都是 O(logN)。

B 树相比 BST 改进的地方是，插入节点时，不往叶子节点下面添加，而将其合并到当前的叶子节点，叶子节点超过容量时，会发生上浮操作。

B 树的特点：

    - 一个节点可以保存 1 到 L 个元素
    - `contains`： 操作跟普通 BST 几乎一样
    - `add`：新元素会加到叶子节点中，如果节点保存的元素的数量超过 L，则将其中一个元素移到父节点，同时将节点拆分。

B 树的高度增长在于当根节点的元素超过 L 时，需要上浮产生新的根节点，从而导致树的高度增长。

从 B 树结构中可以得出两个不变式：
    1. 所有的叶子节点到根节点的距离都一样
    2. 一个有 k 个元素的非叶子节点，它的子节点数一定是 k + 1

2-3-4 树，L=3 的 B 树，每个节点最多保存 3 个元素，最多有 4 个子节点
2-3 树，L=2 的 B 树，每个节点最多有 2 个元素，最多有 3 个子节点

B 的最常见形式：
    - L 值小（L=2 或者L=3）：
        - 常用于在概念上表示平衡二叉树。
    - L 值很大（成千上万）：
        - 工业上用作数据库或文件系统的实现。

## lecture 18 红黑树

[video](https://www.youtube.com/watch?v=kkd8d0QhiQ0&list=PL8FaHk7qbOD6aKgTz2W-foDiTeBEaBoS3&index=3)

[slides](https://docs.google.com/presentation/d/1GP_5y1hpfF7SfUz4rfwcP7uXIYZ4cNRREFyz6IDfWhg/edit?usp=sharing)

不幸的是，实现B树比较复杂，且有性能问题，包括：
    - 要为2-nodes, 3-nodes, 4-nodes 建立不同的节点类型
    - 节点类型之间会变化，如从 2-nodes 变成 3-nodes
    - 上浮节点以及拆分节点的操作复杂

幸运的是，在 BST 上进行旋转操作，可以让 BST 变得平衡，从而实现B树的效果。

### 保存相同元素的 BST 可以有多种形态

根据元素的插入顺序不同而不同，正是 BST 的形态可能有多种，其中高度的增长的最优是O(logN)，最差是O(N)。

BST 可以通过旋转操作从一种形态转换为另一种形态，形态的变化可能造成树高度的增加（或减少） 。

`rotateRight(X)`的含义：X 是父节点，Y 是它的左子节点，将 X 节点右转的意思是先让 X、Y 合并，再让 Y 成为父节点，X 成为它的右侧子节点。旋转操作跟 X 的父节点无关。

### 红黑树：2-3 树在 BST 上的映射

在 2-3 树中，一个节点可以有2个元素，这两个元素在 BST 上是父子节点的关系，且是父节点和左子节点的关系，这两个节点的连接标记为红色，以区别普通父子节点之间的连接。注，不是所有父节点和它的左子节点的连接都是红色，而是在 2-3 树中的一个保存了2个元素的节点，在 BST 中用父节点和左子节点红线连接。这样表示的 BST 就叫红黑树。

LLRB：红支总是向左的红黑树。

一个 2-3 树有且仅有一个 LLRB 表示。

实现红黑树意味将 2-3 树（B树）的表现与操作用 BST 的操作来实现。

插入操作有以下规则：

- 使用红链连接新插入的节点
- 如果形成了红色右向的链接，要进行节点的左旋 (rotateLeft)
- 如果形成了连续的红色链接（对应的 2-3 树中出现了一个节点有3个元素的情况，这个情况是临时的），要进行节点的右旋（rotateRight）
- 如果一个节点有两条红色的链接，要将该节点所有链接的颜色翻转（对应 2-3 树中节点元素分离的操作）

[示例](https://docs.google.com/presentation/d/1jgOgvx8tyu_LQ5Y21k4wYLffwp84putW8iD7_EerQmI/edit#slide=id.g463de7561_042)：向 LLRB 中连续插入 7,6,5,4,3,2,1 最终会得到一个完全平衡的 BST，如果是在普通的 BST 中插入，得到的是单向链表的结构。

LLRB 的运行时：
- 树高的增长 O(logN)
- contains 操作 O(logN)
- 插入操作 O(logN)
    - 添加新节点 O(logN)
    - 旋转节点或者翻转颜色 O(logN)

如何表示红色的边？将子节点标记为红色。

LLRB 的实现：相比一般的 BST 只是多了三行维持 2-3 树结构的操作，以下是 LLRB 的 一个实现：

```java
private Node put(Node h, Key key, Value val) {
	if (h == null) { return new Node(key, val, RED); }
 
	int cmp = key.compareTo(h.key);
    if (cmp < 0)      { h.left  = put(h.left,  key, val); }
    else if (cmp > 0) { h.right = put(h.right, key, val); }
    else              { h.val   = val;                    }
 
	if (isRed(h.right) && !isRed(h.left))      { h = rotateLeft(h);  }
	if (isRed(h.left)  &&  isRed(h.left.left)) { h = rotateRight(h); }
	if (isRed(h.left)  &&  isRed(h.right))     { flipColors(h);      } 
 
	return h;
}
```

Java 的 [TreeMap](https://github.com/AdoptOpenJDK/openjdk-jdk11/blob/999dbd4192d0f819cb5224f26e9e7fa75ca6f289/src/java.base/share/classes/java/util/TreeMap.java) 就是一种红黑树的实现，但不是左向的结构，并且它映射的是 2-3-4 树结构。

lecture 16、17、18 的主题是，我们要用搜索树（search tree）结构实现 Set 和 Map。Set 和 Map 几乎是相同的结构，除了 Map 会额外保存 key 对应的 value：
    - 先介绍二分搜索树（BST），一种查询效率是 O(logN) 的结构，但受困于树的平衡情况；
    - 接着讲 B 树，一种优化了高度的树结构，它的高度增长始终为 O(logN)，但它实现起来复杂；
    - 最后介绍红黑树，用 BST 实现 B 树的操作，使它其操作的运行时在 O(logN) 的级别，且实现起来不复杂。

课程介绍这些结构的重点放在概念的过程性理解，比如 B 树添加元素的过程、红黑树对 B 树的映射如何维持，都用了分步骤的图示表示，便于理解，对这些结构的实现一带而过。

lecture 18 最后讲，除了用搜索树实现 Set 和 Map，还有其他有效率的方式，如：
    - 链表结构：Skip lists are linked lists with express lanes；
    - 哈希 Hashing：lecture 19 开始讲