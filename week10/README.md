# week 10 课程纲要

## 1. Lectures

详细见 `./lectures.md`

- lecture 26: 

## 2. Discussion/Review 

- [disc 10 Shortest Paths, MSTs 练习题](https://drive.google.com/file/d/1BS-5XtL9GpkmeW3IWx44xt-qJHD3pk7P/view?usp=sharing)
- [Shortest Paths, MSTs 考试题](https://drive.google.com/file/d/1At-W5zAQzyrbUyX6VdDJJPh9UY9rTWYx/view?usp=share_link)：对一个图进行深度优先搜索、广度优先搜索、执行 Dijkstra's 和 A* 算法。概念上把握的是，dfs、bfs 目的是遍历图，走遍每个节点，这是处理图的通用做法；Dijkstra's 是找出从初始节点开始到所有节点的最短路径，根据这个需求，要遍历图，但遍历方法既不是 dfs 也不是 bfs，而是 best-first-search，所以要管理一个优先队列，用于获取下一个 best 节点；A* 算法目的是找出图中从点 A 到点 G 的最短路径，和 Dijkstra's 解决的不是一个问题，但很相似，在 Dijkstra's 基础上增加一个节点到目标节点的估算值，就可以达到要求。另外记录几个反直觉的判断题：
  - 给每条边的权重增加常量 k，不会影响已有两个节点的最短路径，错误。反例，节点 A、B、C，AB 权重 1, AC 权重 2.5，BC 权重 1，没加前 A 到 C 的最短路径 A-B-C。给每个边加权重 1，最短路径变为 A-C
  - 给每条边的权重乘以一个正整数 k，不会影响已有两个节点的最短路径，正确。设节点 u、v 原先的最短路径总权重 w，则 u、v 其他路径的总权重一定大于 w；每个边权重乘以 k 后，u、v 最短路径总权重变为 kw，其他路径总权重也乘以 k，还会大于 kw，所以最短路径不变
  - 如果一个图的所有边的权重都不同，则根据 cut property，这个图的 MST 只有一个
  - 在一个图中，节点 A 和节点 B 之间的最短路径，未必会出现在图的 MST 中

## 3. Lab

[Lab 09: Project 2B check point](https://sp23.datastructur.es/materials/lab/lab09/): 校内学生参与，Project 2B 项目的过程性讨论

## 4. Assignment

[Homework 3](https://www.gradescope.com/courses/484660/assignments/2713206)：校内学生参与。
Midterm 2 3/16: 第二次其中考试，校内学生参与。
