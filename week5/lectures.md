# week5 lectures 笔记

## lecture 12  Iterators, Object Methods

- Java 中的 Set 结构，基于 Array 实现 Set 结构 ArraySet
- 使 ArraySet 支持增强版的 for 循环
  1. 在类中添加一个 `iterator()` 方法，返回一个 `Iterator<T>` 对象，[Iterator<T>](https://docs.oracle.com/javase/8/docs/api/java/util/Iterator.html)是一个接口，它要求实现 `hasNext()` 和 `next()` 方法
  2. 在自己数据结构类中创建类，实现 Iterator 接口
- 添加 toString 方法：该方法返回对象的字符串表示，Object 类的 toString 方法返回`类名@内存地址`，实现自己的数据结构时可重写该方法使之支持打印。
  - 实现时注意由于字符串是不可改变的，直接拼接字符串性能较低，用 StringBuilder 生成字符串
- 添加 equals(Object) 方法：Object 实现的 equals 的用的 `==`，可覆盖此方法，实现对象比较

```java
@Override
public String toString() {
    List<String> listOfItems = new ArrayList<>();

    for(T item : this) {
        items.add(listOfItems.toString());
    }

    return "[" + String.join(", ", listOfItems) + "]";
}
```

```java
@Override
public Boolean equals(Object o) {
  if (this == o) { return true; }
  if (o instanceof ArraySet otherO) {
    if(this.size != otherO.size) { return false; }
    for(T item : o) {
      if(!otherO.contains(item)) { return false; }
    }
    return true;
  }
  return false;
}
```

Java 中的 instanceof 语法，`o instanceof ArraySet otherO` 做了两件事：
 1. 判断 o 的动态类型是否为 ArraySet，或者后者的子类；
 2. 将变量 o 转型为静态类型为 ArraySet 的一个新变量 otherO；

这种做法兼容 o 是 null 的情况。

及：支持泛型的类静态方法的写法：

```java
public class ArraySet<T> {

  // 第一个 <SomeItem> 表示这是个泛型方法
  // ArraySet<SomeItem> 表示返回的是 ArraySet<SomeItem> 独享
  // SomeItem... items，表示 items 是一组类型为 SomeItem 的参数，items 可迭代
  public static <SomeItem> ArraySet<SomeItem> of(SomeItem... items) {
    ArraySet<SomeItem> newSet = new ArraySet<>();
    for(SomeItem x : items) {
      newSet.add(x);
    }
    return newSet;
  }
}

// 使用该静态方法
ArraySet<String> strSet = ArraySet.of("Hello", "World");
```

### lecture 12 练习题

1. 修改课程中 ArraySetIterator 类，使它的构造函数接受一个 Comparator<T> 对象和泛型 T 的对象 ref，让迭代器只返回大于 T 的items：

```java
private class ArraySetIterator implements Iterator<T> {
	private int pos;
  private Comparator<T> cp;
  private T ref;

	
	public ArraySetIterator(Comparator<T> cp, T ref) {
		pos = 0;
    this.cp = cp;
    this.ref = ref;
	}
	
	public boolean hasNext() { 
		return pos < size; 
	}
	
	public T next() {
	 	T returnItem = items[pos];
    while(cp.compare(returnItem, ref) <= 0) {
      pos += 1;
      returnItem = items[pos];
    }
	 	return returnItem;
	}
}
```

## lecture 13 渐进分析

### RunTime Characterization 刻画程序的运行时

如何知道程序在运行时的表现？
  1. 计时，用秒表、Unix 的 `time` 计算程序执行的耗时；
  2. 计算操作数（operations），计算在不同规模的输入下，执行程序需要的操作数量。

不同算法会产生不同的操作数量，在输入的 N 较小时，不同算法之间的差异可以忽略不计，但 N 以百万计时，算法的操作数会出现极大的差异，从而显出优劣，如下图：

![算法的 scaling](../images/scaling%20across%20many%20domains.png)

### Computing Worst Case Order of Growth

一个算法在输入为 N 时的操作包括以下组成：
  1. less than (<) ：100N^2 + 3N
  2. greater than (>) ： 2N^3 + 1
  3. and (&&) ： 5,000

问这个算法的增长曲线最最近哪种形状？N、N^2 还是 N^3？

答案是 N^3，因为 N 很大时，`2N^3 + 1` 这步操作的耗时**远远大于其他操作**。

![order-of-growth-identification](../images/Intuitive%20Order%20of%20Growth%20Identification.png)

## lecture 14 不交集数据结构

[disjoint-sets 教材](https://cs61b-2.gitbook.io/cs61b-textbook/14.-disjoint-sets/14.1-introduction)

- 不交集：如果两个集合中的元素没有重合的，这两个集合叫做不交集。
- 不交集数据结构：不交集数据结构是用于追踪元素在不交集之间的分布情况的数据结构。

不交集数据结构追踪的元素数量是固定的，一开始所有元素分别属于一个集合，简略起见，以下讨论的元素都是整型。不交集数据结构提供两个方法：

```java
public interface DisjointSets {
    void connect(int p, int q);
    boolean isConnected(int p, int q);
}
```

`connect(int p, int q)`: 将不交集数据结构中的 p 和 q 元素联合起来，此操作会合并 p 和 q 所在的集合；
`isConnected(int p, int q)`：查询元素p和元素q是否属于同一个集合。

假设要用一个 DisjointSets 要管理 1、2、3、4、5 共五个元素的不交集情况。一开始用这些元素初始化一个 DisjointSets，所有的元素都是独立的几何，如下图：

![disjoint-init-state](../images/disjoint-init-state.png)

对它调用 `connect(1, 2)`，1 和 2 所在集合合并成一个，调用 `isConnected(1,2)` 返回 `true`：

![disjoint-state2](../images/disjoint-state2.png)

再调用 `connect(2, 4)`，2 所在集合和 4 所在集合合并成一个，调用 `isConnected(1,4)` 返回 `true`：

![disjoint-state3](../images/disjoint-state-3.png)

下面以不交集数据结构的实现为例，说明底层数据结构的选择和对它的优化，如何影响上层数据结构的效能。

### 一开始的想法

既然不交集数据结构管理元素归属集合的情况，就用 Set 保存元素，用 List 保存集合们，得到的结构如：`List<Set<Integer>>`，用 1-5 初始化这个结构，得到类似 `[ {1}, {2}, {3}, {4}, {5} ]`，

- `connect(int p, int q)` 的实现：遍历 List，找到 p 和 q 所在集合，合并两个集合的元素；
- `isConnected(int p, int q)` 的实现：遍历 List 找到 p 所在集合，判断该集合中是否有元素 q；

这种实现的性能：

|  实现   | 构建  |  connect   | isConnected  |
|  ----  | ----  | ----  | ----  |
| `List<Set<Integer>>` | Θ(N) | O(N) | O(N) |

这种方式底层使用集合与列表来实现，每个操作的复杂度都是线性的，接着我们换一种底层结构来实现。

### Quick Find

我们使用数组进行底层结构，用数组的索引表示集合中的元素，用索引处的值表示它所属的集合（一开始接触这种思路时，觉得神奇，数组的索引还可以这么用。但这适用于元素为整数的情况，能对应到数组的索引，如果元素时浮点数，或是对象，这种方式就不适用了），索引处的值相同，表示索引代表的元素属于相同集合，我们只判断是否相同，不判断具体相同的值时多少。

仍以 1-5 元素为例，一开始时它的结构为：`[0,1,2,3,4,5]`，`0` 号位的元素可以是任意值。

**`connect(int p, int q)` 的实现**

遍历数组，找到与 `arr[p]` 相同值的元素，将它们设为 `arr[q]` 处的值。
- 调用 `connect(1, 3)` ，将 `arr[1]` 的值设为 `arr[3]`，此时数组为 `[0,3,2,3,4,5]`；
- 再调用 `connect(3, 5)`，将 `arr[1]`、`arr[3]` 处的值设为 `arr[5]`，此时数组为 `[0,5,2,5,4,5]`

`isConnected(int p, int q)` 的实现：返回 `arr[p] == arr[q]`

示例代码如下：

```java
public class QuickFindDS implements DisjointsSets {
    private int[] id;
    // 用数据填充该结构，Θ(N)
    public QuickFindDS(int N) {
        id = new [N];
        for(int i = 0; i < N; i++) {
            id[i] = i;
        }
    }
    // 合并两个元素所在集合，Θ(N)
    public void connect(int p, int q) {
        int pid = id[p];
        int qid = id[q];
        for(int i=0; i<id.length; i++) {
            if(id[i] == pid) {
                id[i] = qid;
            }
        }
    }
    // 查询是否属于同一个集合，Θ(1)
    public boolean isConnected(int p, int q) {
        return arr[p] == arr[q];
    }
}
```

这种实现的查询性能是常数，所以叫 QuickFind：

|  实现   | 构建  |  connect   | isConnected  |
|  ----  | ----  | ----  | ----  |
|  QuickFind | Θ(N) | Θ(N) | Θ(1) |

对比上一种实现，`isConnected` 操作的性能现在是常数级别了，那么 `connect` 的性能是否可以继续提升？

### Quick Union

QuickFind 中我们用数组索引处的值的**相同**表示这些索引属于同一个集合，每次合并两个元素时，就要找到所有与其中一个元素相同的值，这种操作的复杂度是 Θ(N)。

下面，我们要用树这个概念表示集合，用“属于同一棵树”表示属于同一个集合，合并两个集合，就是合并两棵树；查询两个元素是否属于相同集合，就是查询两个元素是否在同一树。

底层仍用数组实现，且用索引号表示元素，用索引处的值表示该元素的父元素。以 0-5 这 6 个元素为例，初始化该结构，得到`[-1,-1,-1,-1,-1,-1]`，值 `-1` 表示这个元素为树的根节点，一开始时每个元素表示一个独立的树。

`[-1,0,1,-1,0,3,-1]` 表示的结构如下图：

![quick-union-1](../images/quick-union-1.png)

我们定义一个 `find(int p)` 操作，表示找到元素 p 所在树的根，根据上图，`find(4)` 返回 `0`，`find(5)` 返回 `3`。

**`connect(int p, int q)` 的实现**

合并两个元素所在的树，要分别找到元素 p、元素 q 所在树的根，然后将其中一个树设为另一个树子树（将其中一个树的根保存的值改为另一个树的根保存的值），参考上图，执行 `connect(5, 2)`:
    1. find(5) 得到 3
    2. find(2) 得到 0
    3. 将 `arr[3]` 的值设为 0，aka `arr[find(5)] = find(2)`

此时，数据结构如下图所示：

![quick-union-2](../images/quick-union-2.png)

`isConnected(int p, int q)` 的实现：返回 `find(p) == find(q)`

这种实现由于合并操作本身（将一棵树设为另一颗树的子树，不含查询过程）很快，叫 QuickUnion，它的性能是

|  实现   | 构建  |  connect   | isConnected  |
|  ----  | ----  | ----  | ----  |
|  QuickUnion | Θ(N) | O(N) | O(N) |

这样一看 QuickUnion 比 QuickFind 的性能反而更低，但 O(N) 表示复杂度上限，代表着还有优化空间。

以下是 QuickUnion 的示例实现代码：

```java
public class QuickUnionDS implements DisjointSets {
    private int[] parent;

    public QuickUnionDS(int num) {
        parent = new int[num];
        for (int i = 0; i < num; i++) {
            parent[i] = -1;
        }
    }

    private int find(int p) {
        while (parent[p] >= 0) {
            p = parent[p];
        }
        return p;
    }

    @Override
    public void connect(int p, int q) {
        int i = find(p);
        int j= find(q);
        parent[i] = j;
    }

    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }
}
```

### Weighted Quick Union

如何提升 Quick Union 的性能？我们发现 `find()` 操作是查询父元素直到树根，树越短，查询的速度越快。基于这个发现，我们增加一条 connect() 时的规则：合并两个树时，我们总是把较小树的根添加到较大的树上，使用这个规则构建的树，它的最大高度会是 `logN`，即 `H <= LogN`

衡量两个树的大小的规则是树的节点数，树A 包含的元素数量比树B 多，就说树A 更大。数的大小保存到树根上，原来树根保存 -1，现在保存 `-(size of tree)`。

这种实现的性能是：

|  实现   | 构建  |  connect   | isConnected  |
|  ----  | ----  | ----  | ----  |
|  WeightedQuickUnion | Θ(N) | O(log(N)) | O(log(N)) |

### Weighted Quick Union with Path Compression

在 WQN 实现基础上，我们做一步路径压缩的操作，可以继续提升性能：在 find(x) 寻找树根的过程中，我们将沿途找到的所有元素的父元素都设置为最后找到的树根元素，这样 `find()` 越多，树就越扁平，这种算法的摊销运行时（amortized runtime）能达到近乎常量。准确地说，它的复杂度是 `O(N+M(lg*N))`，`lg*` 是迭代对数，在真实世界中不会超过 5。

以上几种实现方式的性能比较如下：

|  实现   | 构建  |  connect   | isConnected  |
|  ----  | ----  | ----  | ----  |
|  QuickFind | Θ(N) | Θ(N) | Θ(1) |
|  QuickUnion | Θ(N) | O(N) | O(N) |
|  WeightedQuickUnion | Θ(N) | O(log(N)) | O(log(N)) |
|  WQN with 路径压缩 | Θ(N) | O(α(N))* | O(α(N))* |

### 几道练习题

[Exercises](https://cs61b-2.gitbook.io/cs61b-textbook/14.-disjoint-sets/14.6-exercises)

1. Suppose we create a WQU with N items, then we perform M1 union operations and M2 union operations. Using big O notation, what is the runtime of this sequence of operations?

问 WQU 操作 M1 次 和 M2 次的复杂度，应为 O(N + (M1+M2)logN)

2. Using the same variables as problem 2, describe a sequence of operations that would result in a runtime of O(N + M1 + M2).

问什么操作会使运行时操作为 O(N + M1 + M2)？从题目推断 connect(), isConnected() 的复杂度均变成了常量，这是形成了高度只有 1 的树会产生的情况，也就是 connect(0,1)、connect(0,2) 一直到 connect(0,N)

3. Write a int find(int p) method for the WQU with path compression. It should perform path compression as described in lecture: any node on the path from root to our target node should have its parent reset to the root. It takes in the target node p and returns the root of the tree p is in.

写一个带路径压缩的查找根树的函数 `int find(int p)`，传入一个节点的索引，返回根树的索引。

可以用递归求解（没实际跑过，根据对递归的理解写的）：

```java
public int find(int p) {
  if(parent[p] < 0) { // 约定根元素索引存的值是 -(size of tree)
    return p;
  }
  int rootIdx = find(parent[p]); // rootIdx 在最后一个 frame 返回后开始有值 
  parent[p] = rootIdx;
  return rootIdx;
}
```

参考答案使用了两次循环，一次找到根，一次将沿途所有元素的父级替换为根：

```java
public int find(int p) {
  int root = p;
  while(root != parent[root]) { // 这个终止条件什么意思？root 的 parent[root] 是它自己？
    root = parent[root];
  }

  while(p != root) {
    int newParent = parent[p];
    parent[p] = root;
    p = newParent;
  }

  return root;
}
```