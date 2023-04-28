# week3 课程纲要

第一次课将如何写单元测试，推荐的阅读材料提供了对单元测试的不同看法，有的对 TDD 不以为然（[《测试驱动开发已死，测试长存》](https://dhh.dk/2014/tdd-is-dead-long-live-testing.html)，一文开篇说严格遵守测试优先的开发过程，就像围绕禁欲开展的性教育一样，是一场不现实、无结果，只会让自己感到厌弃与羞愧的道德运动），有的认为 TDD 是良好实践。

Josh Hug 以编写选择排序为例，演示了 TDD 的过程，使用了 Google 的 `Truth` 库和 JUnit 的 `Test` API。Josh Hug 认为开发中引入单元测试是必要的，写单元测试确实增加部分代码量，但它使开发者：1）将一个复杂逻辑拆分成独立的方法，实现起来容易，debug 时也有方向；2）后续修改代码时可快速验证修改的有效性。

我觉得大逻辑拆分小逻辑的思路要多多练习，Josh Hug 将选择排序的实现分成三个逻辑独立的步骤：
  1. 给定一个数组与一个起始位置，从起始位置开始，找出其中的最小者，返回它的索引；
  2. 给定一个数组和两个索引，将数组中的者两个索引的元素互换；
  3. 将 1-2 两个过程整合到一起完成选择排序：给定一个数组和起始位置 0，执行 1，得到的最小值的索引和起始位置 0 的元素互换，这样完成了一次最小值的排序。不断重复这个过程，起始位置不断向前推进，直到为 `arr.length - 1`，排序完成。

这三个过程都可以写成独立的方法，进行单元测试，在 3 中用递归的方式结合使用 1 和 2，实现选择排序的逻辑。

本周 Discussion/Review，共有 3 个，前两个 Discussion，最后一个 Review，见 `exercises/Discussion&Review.md`
  - 第一次 Discussion: 练习题[Discussion 03](https://drive.google.com/file/d/1qiF-aEYKl6-Y8gsf_SfHSjlgQ9SlLHTb/view)
  - 第二次 Discussion[Exam Levle 03](https://drive.google.com/file/d/14MsmrUNXnp-aiJVosQYTbMusnt2rrgeH/view)
  - 第三次[Exam Review](https://drive.google.com/file/d/1koTjUljHHI_ojyw5Q_DqBYhw6nnl7kh5/view)

本周 [Lab 03](https://sp23.datastructur.es/materials/lab/lab03/)，使用经验主义的方式，测试单向链表 `SLList` 和 `AList` 的执行时间。详见 `./lab/README.md`