# week2 课程纲要

本周主题：线性数据结构 List。

1. IntList： 使用递归的方式实现链表
2. SLList：对外隐藏递归细节的单向链表
3. DList：在单向链表基础上，做了读取性能优化的双向链表、支持通用类型的列表结构
4. AList：使用数组实现 List 结构
  - Java 中的数组固定长度，不能动态增加元素；
  - 有 length 属性，没有实例方法，只用来保存数据；
  - 初始化数组时，编译器会根据类型填入默认值，`int[] arr = new int[4]`，`arr[0]` 到 `arr[3]`的值都为 0；
  - 二维数组（又叫矩阵）的声明方式 `int[][] arr`；
  - 用数组实现列表结构的优势，随机读取的时间为常量；
  - 用数组实现列表时，超过数组预定大小，需创建一个大小为 `size * RFACTOR` 的新数组，RFACTOR（因子）可为常量，如 2，或按照一定算法生成；
  - 数组实现列表时，为了优化空间使用率，需要考虑空间使用率，`R = size / items.length`，当 `R < 0.25` 时，就将数组总长度缩小一半。

本周的 Discussion/Review：前两个是 Discussion，主要内容是巩固类、Map、List的使用，第三个是 Review，针对 IntList 结构的练习，如链表翻转等，详见 `./exercises/` 

本周的 Lab：[Lab 02 - Debugging](https://sp23.datastructur.es/materials/lab/lab02)。介绍了在源代码中打断点、使用 Debug 的方式跑代码以及 IntelliJ 的 Debug 面板上的按钮功能，提供了几段函数要求 Debug 使它们正常运行。