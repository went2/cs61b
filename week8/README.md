# week 8 课程纲要

## 1. Lectures

详细见 `./lectures.md`

- lecture 20: 哈希2
- lecture 21: 堆与优先队列（Heap and Priority Queue）
- lecture 22: 树与图的遍历（Tree and Graph Traversals），放到 week9 笔记中

## 2. Discussion/Review 

- disc08: [B-Trees, LLRBs, Hashing 的练习题](https://drive.google.com/file/d/1bsav6ui7bNOzgI_KfeOZ1a831KXbqzF8/view?usp=sharing)
    - 判断一个 `hashCode()` 是否合法的依据：1）两个通过 equals() 判断相同的对象，是否产生相同的哈希码；2）同一个对象，是否同时产生相同的哈希码
    - 判断一个 `hashCode()` 是否合理的依据：在合法的前提下，`hashCode()` 产生的整数要尽可能地随机。比如 `return -1;` 是合法的哈希码生成函数，但极不合理；对于一个整型来说 `return intValue()`，返回它自己的值作为哈希码，就是合法又合理的生成函数，因为整数的范围很广，而且两个不同的整数的哈希码也不相同，保证了最终在哈希表中的位置是均匀分布的
- exam-level08：[B-Trees, LLRBs, Hashing 的考试题](https://drive.google.com/file/d/1wFMeZYDfuTaeCCDPe7xRDIfZkjQdpOoi/view?usp=share_link)

## 3. Lab

[Lab 08: 实现一个 MyHashMap](https://sp23.datastructur.es/materials/lab/lab08/): 根据给定的 CS61B 接口要求和脚手架代码，实现一个 HashMap，详见 `./lab08-MyHashMap.md`

## 4. Assignment

[Homework 3](https://www.gradescope.com/courses/484660/assignments/2713206)：校内学生参与。
[Project 2B](https://sp23.datastructur.es/materials/proj/proj2b)
