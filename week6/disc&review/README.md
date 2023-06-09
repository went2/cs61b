# week6 讨论与回顾部分笔记

这周的讨论与回顾围绕不交集结构和算法运行时分析，记录我解题后的反思：

## 1. 关于不交集结构

对于不交集结构我无法获得直观上的理解，只能记住结论，有时候用具体的例子推导出普遍情况

### 1.1 符合 Weighted Quick Union 的判别

判断一个不交集结构的数组形式是否符合 WQU，不仅要考虑树的高度是否小于等于 logN，还要看其树的结构，是否符合 WQU 连接时的规则：小树添加到大树上。

### 1.2 WQU 的运行时分析

WQU 的操作（connect，isConnected）主要耗费在从指定节点查找它所属的根节点上，`int find(int value)`，一般情况下的 WQU 的 find 操作运行时是 O(logN)，从而 `connect`，`isConnected` 操作运行时也是 O(logN)，如果实现了路径压缩，那么 find 操作可以达到 O(1) 的性能，从而 `connect`，`isConnected` 也能达到 O(1)：
  - 实现了路径压缩的 WQU ，连接的节点时类似`(0,1)`, `(0,2)`, `(0,3)` 的形式，新节点直接添加到树根上

## 2. 关于嵌套循环的算法运行时分析

嵌套循环要**分别**搞清楚外层循环和内层循环的终止条件，我按直觉理解的嵌套循环的算法复杂度 O(N^2)，是在外层无条件遍历 N 次，且内层无条件遍历 N 次的情况下成立，除此以外，要仔细看代码的条件中的判断。理论上根据内外两层是否无条件遍历可以有以下情况：
  1. 外层无条件遍历 N 次：
    1.1 内层无条件遍历 N 次，算法运行时 Θ(N^2)；
    1.2 内层特殊情况下可以只执行 1 次，运行时 Ω(N),O(N^2)；
  2. 外层特定条件下可以只执行 1 次：
    2.1 内层无条件遍历 N 次，运行时 Ω(N),O(N^2)；
    2.2 内层特殊情况下可以只执行 1 次，运行时 Ω(1),O(N^2)；

上述情况假定的内外两层遍历的序号按照线性增长，如果遇到序号按照指数级别增长，情况大不相同，如外层循环 `for (int i = 1; i < N; i *= 2)`，意味着 N 与外层执行次数的关系（C(N)）是 `C(N) = logN`，N 每增加当前的 1 倍，C(N) 才增加 1，假设此时内层循环 `for (int j=0; j<i; j++)`，整个算法的运行时**不是** `NlogN`，要参考下图，列出具体的例子推算规律：

![nested-loop-count](../images/nested-loop-count-1.png)

其运行时是 O(N)

## 3. 几何级数相加（Geometric Add）的简化：

- Q 是 power of 2 的情况下，`1 + 2 + 4 + 8 + ... + Q = 2Q-1`