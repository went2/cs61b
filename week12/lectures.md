# week 12 lectures

## lecture 29 Sorting I: Basic Sorts

### 排序的定义

1. Donald Knuth's TAOCP 的定义

排序是按照一定顺序排列元素，假设 a, b, c 有顺序关系，意味着它们具有以下属性：
  1. 三分律：有且仅有一种关系，要么 a < b, a = b 或 b < a；
  2. 传递率：如果 a < b 且 b < c，则 a < c

具有以上属性的关系叫做完全关系（total order）。

排序就是根据给定的顺序关系，将一系列元素按照非降序（non-decreasing order）进行重新排列的过程。

2. Josh 给的定义

排序排的是反序（inversion）的元素，反序指的是一对元素的关系不是小于（<）关系的情况。

`0 1 1 2 3 4 8 6 9 5 7`，这组数字中有 6 对反序元素，8-6, 8-5, 8-7, 6-5, 9-5, 9-7。

排序过程就是：
  1. 给定一组元素，其中有 Z 对反序；
  2. 进行一系列操作将反序的数量 Z 降为 0；

### 选择排序

- 传统选择排序：找到最小元素 -> 与首位交换 -> 直到所有元素都已排序
  - 时间复杂度 O(N^2)
- 堆排序（Heapsort）: 遍历所有元素，将它们存到最大堆（max-heap），然后依次取最大值，填充到新数组
  - 将所有元素插入堆 ，O(N logN)
  - 取出最大元素 O(logN)
  - 插入新数组 O(1)
  - 总时间 O(N logN)，比选择排序快多了，空间复杂度 Θ(N)，所有数据都要复制到一个堆中中转，然后还要新建一个数组返回。
- In-place Heap Sort：原地堆排序，直接将原本的数组堆化（Heapification）转成一个堆，然后进行位置交换
  - 时间复杂度 O(N logN)
  - 空间复杂度 O(1)

### 合并排序

### 插入排序

原地插入排序[过程示例](https://docs.google.com/presentation/d/10b9aRqpGJu8pUk8OpfqUIEEm8ou-zmmC7b_BE5wgNg0/edit#slide=id.g463de7561_042)

- 需要交换的次数就是数组中的反序的对数。
  - best-case: 数组已排序，Θ(N)
  - worst-case：数组是反序，O(N^2)
  - 因此在数组大部分已排序的情况下，插入排序执行很快，几乎可以达到线性时间
  - 此外，数组容量很小时（length <= 15），插入排序运行起来也非常快