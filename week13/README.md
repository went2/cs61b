# week 13 notes

## 1. lectures

- lecture 32 - 快速排序 II
  - 为什么 Java 的 Array.sort 对基本值（int,long,char）使用快速排序，对 Object 数组使用合并排序？
    - 因为基本值的排序不要求 stability，两个相同的整数，不论谁前谁后都没问题，但对象不同，位于不同内存地址的对象，可以是相同的（取决于它 .equals() 的实现），因此排序对象时要求 stability。
  - 既然快速排序可用来找中间值，为什么在使用快速排序时，先用快排找到中间值作为 pivot，然后在用它进行快排？这样可以保证分割的次数是 log N
    - 这会让快速排序的整体运行时有一个明显的常量的提升，反而拖慢了整体的执行速度，每次执行快排时，都先用一个耗时的算法找到中间值，并不能提升快排面对大多数输入情况下的速度

- lecture 33 - 软件工程 III

- lecture 34 - 排序算法的算法约束（Algorithmic Bounds）
  - 基于比较的排序算法，其最差情况最少需要 Θ(N logN) 次比较，没有比这更少的情况了。这节课主要演示该结论的得出过程与论证
 