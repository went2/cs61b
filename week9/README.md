# week 9 课程纲要

## 1. Lectures

- lecture 22-23: 树与图的遍历（Tree and Graph Traversals）和实现
- lecture 24: 最短路径问题
- lecture 25: 最小生成树（Minimum Spanning Trees）

第九周的重点讲图遍历的算法：
  - 通用遍历方法：深度优先、广度优先
  - 找一个节点到所有节点的最短路径的算法：Dijkstra's 
  - 找一个节点到另一个节点的最短路径：A*
  - 找图的最小生成树的两种算法：
    - Prim's
    - Kruskal's

针对图遍历的练习题，大部分是，给一个图，让你执行 dfs、bsf、Dijkstra's、A* 等算法，写出算法执行过程中访问节点的顺序；给一个图，执行 Prim's、Kruskal's 算法，写出往 MST 中添加边的顺序等。

详见 `./lectures.md`

## 2. Discussion/Review 

- disc09: [Graphs, Heaps 练习题](https://drive.google.com/file/d/1xQuz9DGTkarjvl8MCxpbXiBBRFOIBj1_/view?usp=sharing)
- exam-level-09：[Graphs, Heaps 考试题](https://drive.google.com/file/d/1O3ks_kojbcnm-K2GkTGfniABxM0xiEwn/view?usp=share_link)

## 3. Lab

[Lab 09: Project 2B check point](https://sp23.datastructur.es/materials/lab/lab09/): 校内学生参与，Project 2B 项目的过程性讨论

## 4. Assignment

[Homework 3](https://www.gradescope.com/courses/484660/assignments/2713206)：校内学生参与。
Midterm 2 3/16: 第二次其中考试，校内学生参与。

## 5. Review

这周开始的内容对我是个大挑战，学习过程中不断怀疑自己的认知，几乎放弃（中断了几天），但想到我可能再接触不到 Josh 这样一步步推导算法给人启发的人，觉得可惜，后面借着练习题，在纸笔上演示深度优先、广度优先搜索、Dijkstra's、A*、Prim's、Kruskal's 的算法过程，哪里不会就回头看 slides，才有一点通顺的感觉，discussions 部分的练习题真重要啊。