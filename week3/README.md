# week3 课程纲要

## 1. Lectures

第一次课讲如何写单元测试，推荐了三篇博客文章，从正反两个方面表达了对单元测试的看法，有的对 TDD 不以为然（如[《测试驱动开发已死，测试长存》](https://dhh.dk/2014/tdd-is-dead-long-live-testing.html)一文说严格遵守测试优先的开发过程，就像围绕禁欲开展的性教育一样，是一场不现实、无结果，只会让自己感到厌弃与羞愧的道德运动），有的认为 TDD 是良好实践，应该继续保持下去。

本课程倡导 TDD 开发方式，Josh Hug 认为开发中引入单元测试是必要的，写单元测试确实增加部分代码量，但它使开发者：1）将一个复杂逻辑拆分成独立的方法，实现起来容易，debug 时也有方向；2）后续修改代码时可快速验证修改的有效性，继而他以编写选择排序为例，演示了 TDD 的过程。

我觉得大逻辑拆分小逻辑的思路要多多练习，Josh Hug 将选择排序的实现分成三个逻辑独立的步骤：
  1. 给定一个数组与一个起始位置，从起始位置开始，找出其中的最小者，返回它的索引；
  2. 给定一个数组和两个索引，将数组中的者两个索引的元素互换；
  3. 将 1-2 两个过程整合到一起完成选择排序：给定一个数组和起始位置 0，执行 1，得到的最小值的索引和起始位置 0 的元素互换，这样完成了一次最小值的排序。不断重复这个过程，起始位置不断向前推进，直到为 `arr.length - 1`，排序完成。

这三个过程都可以写成独立的方法，进行单元测试，在 3 中用递归的方式结合使用 1 和 2，实现选择排序的逻辑。

第二次课程（2.5）讲基于数组实现列表结构，AList，见 `./lectures/基于数组实现列表结构.md`
  - Java 中的数组固定长度，不能动态增加元素；
  - 有 length 属性，没有实例方法，只用来保存数据；
  - 初始化数组时，编译器会根据类型填入默认值，`int[] arr = new int[4]`，`arr[0]` 到 `arr[3]`的值都为 0；
  - 二维数组（又叫矩阵）的声明方式 `int[][] arr`；
  - 用数组实现列表结构的优势，随机读取的时间为常量；
  - 用数组实现列表时，超过数组预定大小，需创建一个大小为 `size * RFACTOR` 的新数组，RFACTOR（因子）可为常量，如 2，或按照一定算法生成；
  - 数组实现列表时，为了优化空间使用率，需要考虑空间使用率，`R = size / items.length`，当 `R < 0.25` 时，就将数组总长度缩小一半。

第三次课程（4.1）讲接口继承（Interface Inheritence），同时提了一下实现继承（Implementation Inheritence）：
  - 接口继承：使用 `interface` 声明公共接口类，类中的方法只声明方法签名，不写具体实现。使用类 `implements` 继承接口的每个方法，方法需要声明为 `@override`
  - 实现继承：使用 `interface` 声明公共接口中中的方法时，同时指定了默认的实现，在接口类中实现的方法用 `default` 标识，继承该接口类的子类不实现该方法，用可以调用它，如果子类中实现的同名方法，则用子类实例优先调用子类实现的方法。

课程建议在声明接口的时候，尽量不做默认，因为这会让实现的来源变得难以确定，产生问题时需要层层查找。

## 2. Discussion/Review

本周的 Discussion/Review 共有 3 个，前两个 Discussion，最后一个 Review，见 `exercises/Discussion&Review.md`
  - 第一次 Discussion: 练习题[Discussion 03](https://drive.google.com/file/d/1qiF-aEYKl6-Y8gsf_SfHSjlgQ9SlLHTb/view)
  - 第二次 Discussion[Exam Levle 03](https://drive.google.com/file/d/14MsmrUNXnp-aiJVosQYTbMusnt2rrgeH/view)
  - 第三次[Exam Review](https://drive.google.com/file/d/1koTjUljHHI_ojyw5Q_DqBYhw6nnl7kh5/view)

## 3. Lab

本周 [Lab 03](https://sp23.datastructur.es/materials/lab/lab03/)，使用经验主义的方式，测试单向链表 `SLList` 和 `AList` 的执行时间。详见 `./lab/README.md`

## 4. Project

本周的项目两个：[project1a](https://sp23.datastructur.es/materials/proj/proj1a)、[project1b](https://sp23.datastructur.es/materials/proj/proj1b/)，详见 `./project/README.md`

- project1a：一步一步介绍了如何采用测试驱动开发（TDD）的方式，实现一个基于双向链表的 Deque（双端队列）结构。
- project1b：要求自己实现一个基于数组的 Deque 结构。实现的方式也是 TDD，先写关于一个方法的测试，跑测试，测试失败，然后实现方法，再跑测试。

## 5. 第三周学习回顾

我换了一种策略学习第三周的课程时，之前是学一次课的lecture，完成相应的讨论和课后作业、完成相应的project。这次我一次完成所有本周的 lectures，试图对这周讲的内容有整体把握，再集中做三个讨论和课后作业，再做 Lab 和 Project。这种方法相比第二周，减少了学习 lectures、discussion/review、lab、project 各模块内部内容时的隔离感，但增加了模块之内内容的隔离感，写完三个 discussion/review 的作业，忘了 lectures 讲的哪些内容。写完 project，忘了 discussion/review 写了哪些作业。

得到的结论是学的过程中应尽量记录费劲解题的过程，留下越深刻的印记越好，回顾的时候才会有迹可循。

我会用这种方式学习第四周的课程再论。