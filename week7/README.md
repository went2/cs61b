# week7 课程纲要

## 1. Lectures

详细见 `./lectures.md`

- lecture 17: B 树
    - 特点：一个节点保存多个元素；节点元素满了后，元素会移动到父节点，同时节点进行拆分；
    - 不变式：所有叶子节点到根节点的距离一样；有 k 个元素的非叶子节点，其子节点树必为 k+1 个；
    - 插入元素的规则
- lecture 18: 红黑树
    - 概念：用 BST 实现 2-3 树的操作
    - 插入元素的规则
    - 效能与实现
- lecture 19: 哈希，笔记内容放到 week 8 中的 HashingII 一并记录。

## 2. Discussion/Review 

- disc07: [ADTs, Asymptotics II, BSTs](https://drive.google.com/file/d/1a1SWyETjnPTgkkTiXmMYzfJNwWEYrPfx/view?usp=sharing)
- exam-level07：[ADTs, Asymptotics II, BSTs Exam-level](https://drive.google.com/file/d/1DDGhpJEy6TdUkK-ru8kITbYs4u6HTs62/view?usp=share_link)

## 3. Lab

- [BSTMap](https://sp23.datastructur.es/materials/lab/lab07/)：提供了一个 `Map61B` 接口文件，要求 BST 实现这个接口。

## 4. Assignment

- [Proj2A](https://sp23.datastructur.es/materials/proj/proj2a)：实现一项类似 [Google Ngram Viewer](https://books.google.com/ngrams/graph?content=global+warming%2Cto+the+moon&year_start=1800&year_end=2019&corpus=en-2019&smoothing=0) 的服务，输入一个单词，显示它出现在不同年份中的文献中提到词汇的比例，如 acreage 这个单词在 1472 年的所有文献中提到了 1 次，1472 年的文献统计包含总词汇数量 117652 个，acreage 在 1472 年的词汇频率是 `0.00084996%`。
    - 项目提供了 [Google Ngram 数据集](http://storage.googleapis.com/books/ngrams/books/datasetsv3.html)，是多个 csv 格式的文档，首要工作是实现一个数据结构 TimeSeries，TimeSeries 是个继承了 `HashMap<Integer, Double>` 的结构，保存的是一个词汇在不同年间出现的频率，有多少词汇需要统计，就要实例化相应数量的 TimeSeries
    - `NgramMap` 功能是能够读取 csv 文档内容，把词汇的频率用一个个 TimeSeries 保存一起来，我使用 `HashMap<Character, TimeSeries> stringTS` 结构，还有一些对这个结构中数据的查询方法。很多方法要求返回 TimeSeries 对象时用到 ”defensive copy“，就是不要直接返回查询 stringTS 后的 TimeSeries，要返回一个和它一模一样的 TimeSeries 对象。
    - proj2A 提供了需要实现的方法列表和注释作为脚手架，参考注释完成这些方法比较容易。并且我有一点 Node.js 搭 web server 的经验，看到课程中用 `NgordnetServer` 启动服务器也没有太惊讶。对我来说新的经验是：
        1. 完成这样一个显示词汇频率散点图服务背后需要的数据结构，以及处理数据结构的方法；
        2. 散点图是在 Java 端生成，然后通过 base64 编码传给浏览器，浏览器解码显示。任何数据都可用 ”a string of bits“ 来表示
        3. proj2B 以及proj3 就不会提供注释，要自己设计方法时，可以回过头来参考这里的方法拆分。
