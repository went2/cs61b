# week6 lectures

（注：best case 和 worst case 指相同算法在不同形态的 N 下的表现，比如一个排序算法，当输入一个已经排好序的数组时，这种情况下算法的通常叫 best case；对于一个查找算法，执行一次就找到需要的值，就是 best case）

## lecture 15 渐进分析2

算法的运行时分析指分析算法随着输入规模（ input size）的增加造成的级数的增长（order of growth）情况。Josh Hug 提醒大家分析算法的运行时不是看一看就能得到结果，要具体问题具体分析，要着手进行运算，这次课程Josh Hug 带领大家分析循环、递归、二分查找、合并排序等算法的运行时，给大家积累通用算法的分析经验。

1. 分析嵌套的循环(nested loop)

假设有一个嵌套循环的算法，但其外层 i 增长是按指数级别的增长，问它的运行时怎么算，示例代码如下：

```java
public static void printParty(int N) {
    for (int i = 1; i <= N; i = i * 2) { // 外层 i 按照指数级增长
        for (int j = 0; j < i; j += 1) {
            System.out.println("hello");
            int ZUG = 1 + 1;
```

下面用三种方式分析上述代码的运行时

1.1 直觉

看代码的实现，凭经验和直觉分析它的运行时。

（我的直觉看法：一般来说嵌套循环的运行时一般是 N^2，但这个例子的外层循环的增长不是 N，而是指数级的增长，所以它的算法运行时应该是 "外层循环的增长 * N"，外层循环的增长怎么算，我认为是 logN，所以整个算法的运行时是 NlogN。从结果来看，外层循环的增长确实为 logN，因为 N 增加 1 倍，C(N) 增加 1，但整个算法的增长不是 简单的外层增长关系（logN）乘以内层增长关系（N），内层的增长取决于外层的增长情况，暂不知道如何用公式表示这种关系，可能是 log（N * N）？）

1.2 计数

计算不同取值的 N 下（1、2、3、5...）的算法中的某个步骤的操作数量，从中找规律。

![nested-loop-count](../images/nested-loop-count-1.png)
（图源自 lecture 15 slide，下同）

如上图所示，观察表格得出的结论是，只有当 N 增加当前的 1 倍，算法的操作数才发生变化。上图中左上角 6*6 的网格中第 i 行（含i）以上的有色格子的个数表示 N 为 i 时的算法的某个步骤的计数。

用代数式表达这个规律是：

C(N) = 1 + 2 + 4 + ... + N，在 N 是以 2 为底的指数级数的情况下，而 1 + 2 + 4 + ... + N = 2N - 1

1.3 数学推理

此外，不同的 N 下的函数操作数增长关系，总是处于 0.5N ~ 2N 的区间内，如下图：

![nested-loop-attempt3](../images/nested-loop-attempt3.png)
（图源自 lecture 15 slide）

由于这种增长永远落在两个线性增长的范围内，因此它也是一种线性增长。

所以它的 runtime 是 θ(N)。

2. 分析递归

树递归（tree recursion）的分析

递归调用是调用帧（frame，或执行上下文）的在栈上的叠加，分析递归函数的 runtime 就是分析调用帧的增加情况。假设每个调用帧执行的 runtime 是常数，分析下面这个递归调用算法的运行时：

```java
public static int f3(int n) {
    if (n <= 1) return 1;
    return f3(n-1) + f3(n-1);
}
```

每个函数调用都会新增两个自身函数调用，一生二、二生四、四生八这样的增长，算法的图示如下：

![tree-recursion](../images/tree-recursion.png)

用文字描述是，N 每增加 1，总工作量会增加 1 倍，增加 1 倍就是在当前的数量上乘以 2；
用数学表示是指数级别的增长，C(N) = 2 ^ N;

除了这种直觉上的分析，可用数学计算：

![tree-recurion-2](../images/tree-recurion-2.png)

- N=1，总数 1
- N=2，总数 2
- N=3，总数 1 + 2 + 4
- N=4，总数 1 + 2 + 4 + 8
- N=n，总数 1 + 2 + 4 + ... + 2 ^ (n-1)

根据下图计算公式，得到该算法的运行时时 `2^n - 1`

![tree-recursion-3](../images/tree-recursion-3.png)

3. 分析二分查找

在一个已排序的数组上查找一个值的算法，运行时 logN，logN 的算法在 N 很大的情况下运行起来也极快。

4. 分析合并排序（merge sort）

Merge：操作是指将两个已排序的数组合并成一个数组，做法是不断从两个数组中获取较小值，加入新数组，可参见[数组的合并过程演示](https://docs.google.com/presentation/d/1mdCppuWQfKG5JUBHAMHPgbSv326JtCi5mvjH1-6XcMw/edit#slide=id.g463de7561_042)，合并操作的运行时是O(N)。

![merge-sort-1](../images/merge-sort-1.png)

当不断将一个数组二分，直至只剩下 1 个元素，剩下的操作就是合并的操作。

以下是 merge sort 的实现：

```java
public void mergeSort(int[] arr) {
    int length = arr.length;
    if(length <= 1) { // do nothing
        return;
    }
    int mid = length / 2;

    int[] leftArr = new int[mid];
    int[] rightArr = new int[length - mid];

    // populate left half and right half, use common for loop
    for(int i=0; i<mid; i++) {
        leftArr[i] = arr[i];
    }
    for(int j=0; j<length - mid; j++) {
        rightArr[j] = arr[mid + j];
    }

    // 分别对两边进行 mergeSort
    mergeSort(leftArr);
    mergeSort(rightArr);

    merge(arr, leftArr, rightArr);
}

// merge two sorted arr to one arr
public void merge(int[] arr, int[] leftHalf, int[] rightHalf) {
    int leftLen = leftHalf.length;
    int rightLen = rightHalf.length;
    
    int i = j = k = 0;
    while(i < leftLen && j < rightLen) { // 两者只要一个遍历完，即退出
        if(leftHalf[i] <= rightHalf[j]) { // 取左边数组的值
            arr[k] = leftHalf[i];
            i += 1;
        } else {
            arr[k] = rightHalf[j]; // 取右边数组的值
            j += 1;
        }
        k += 1;
    }

    // 将左边或右边数组剩余的数填充到新数组
    while(i < leftLen) {
        arr[k] = leftLen[i];
        i += 1;
        k += 1;
    }
    while(j < rightLen) {
        arr[k] = rightLen[j];
        j += 1;
        k += 1;
    }
}
```

## lecture 16 抽象数据结构和二分查找树

[ADTs and BSTs](https://cs61b-2.gitbook.io/cs61b-textbook/16.-adts-and-bsts)

##　ADTs

ADTs，抽象数据结构，由它的操作定义，而不是实现。Java 中的 interface 如 Map，Set，List，DisjointSets 都属于 ADTs

## BSTs

二分查找树，一种很重要的基础结构，它可以用来实现 Map，Set 等结构。
    - 树：是一种有节点（node）和节点之间的连接（edge）组成的结构，任意两个节点之间**只有一条路径**。
    - 有根树（rooted tree）：指定了一个节点作为根的树，根节点是树的起点，它没有父节点。叶子（leaf）节点是树的末端，它没有子节点。
    - 二分树：每个节点只有 0-2 个子节点的有根树。
    - 二分查找树：具备 “二分查找属性” 的二分树。
        - 二分查找属性：对于任意一个节点，其左侧子树的所有节点的key都比它小，右侧子树所有节点的key都比它大。

二分查找树节点类型 BST 定义如下：

```java
private class BST<Key> {
    private Key key;
    private BST left;
    private BST right;

    public BST(Key key, BST left, BST right) {
        this.left = left;
        this.right = right;
        this.key = key;
    }
    public BST(Key key) {
        this.key = key;
    }
}
```

BST 的操作：
    - search(key)，运行时 O(logN)
    - insert(key)，被传插入的节点会作为叶子节点，操作运行时 O(logN)
    - delete(key)，运行时 O(logN)

删除一个节点操作的实现分三种情况：
    1. 节点无子节点，直接删除；
    2. 节点只有一个子节点（左节点或右节点）：将节点的父节点指向节点的子节点；
    3. 节点有两个子节点：要找节点的继任者：
        - 从节点的左树中找到最大key的节点（predecessor）
        - 或从节点的友树中找到最小key的节点（successor）
        - 用继任者节点替换该节点
        - 删除继任者节点