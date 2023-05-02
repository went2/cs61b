# Discussion 03

## 第一次讨论

第三周第一次讨论内容是一份练习题，见 [Discussion 03](https://drive.google.com/file/d/1qiF-aEYKl6-Y8gsf_SfHSjlgQ9SlLHTb/view)。

参考答案见：[discussion03_sol](https://drive.google.com/file/d/1b9-OZnczWknxe9huqJhUkjIjhEqNuF_e/view)

第四题有意思，要求将一个一维链表保存到 x 行、y 列的二维数组中，链表的节点可能不满二维数组，也可能超过二维数组，超过的部分不计。

解题的基本思路是，每次获取一次链表的节点，插入到二维数组中下一个空位。

核心过程：1）算出链当前的节点是第几个插入的，2）根据这个数字算出它要放到二维数组的哪一行哪一列。如，链表：`5->3->7->2->8`，放到一个 `2 × 3` 的二维数组，得到的二维数组应为 `int[][] result = {{5,3,7}, {2,8,0}};`，放不满位置用 0 填充。具体过程：

  - 第一次操作，初始化一个索引值 num 为 0， 取出链表的第一个节点 5，它应该放到二维数组`result[0][0]` 的位置，二维数组的索引由 num 计算得到: `int row = num / 3`，`int col = num % 3`，然后将 num 值增加 1，准备放下一个节点；
  - 第二次操作，num 为 1，链表的节点 3，它应位于二维数组中的第 `1 / 3 = 0` 行，第 ` 1 % 3 = 1` 列，即 `result[0][1] = 1; `，然后将 num 值增加 1，准备下一个节点的操作；
  - 边界条件，一是链表的节点都取完，二是 num 达到了二维数组的容量 `arr.length * arr[0].length`

参考代码：

```java
public class SLList {
    Node sentinel;

    public SLList() {
        this.sentinel = new Node(0,null);
    }

    public void addFirst(int item) {
        this.sentinel.next = new Node(item, this.sentinel.next);
    }

    private static class Node {
        int item;
        Node next;
        public Node(int item, Node next) {
            this.item = item;
            this.next = next;
        }
    }

    public int[][] gridify(int rows, int cols) {
        int[][] grid = new int[rows][cols];
        gridifyHelper(grid, sentinel.next, 0);
        return grid;
    }

    private void gridifyHelper(int[][] grid, Node curr, int numFilled) {
        if(curr == null || grid.length * grid[0].length == numFilled) {
            return;
        }

        int row = numFilled / grid[0].length;
        int col = numFilled % grid[0].length;

        grid[row][col] = curr.item;
        gridifyHelper(grid, curr.next, numFilled + 1);
    }
}
```

## 第二次讨论 Exam Level 03

共三道练习题，[Exam Levle 03](https://drive.google.com/file/d/14MsmrUNXnp-aiJVosQYTbMusnt2rrgeH/view)

最后一题，双向链表去重。

## 第三次讨论 Exam Review

共八道练习题，[Exam Review](https://drive.google.com/file/d/1koTjUljHHI_ojyw5Q_DqBYhw6nnl7kh5/view)

主要考查接口继承与实现继承的区分、数组翻转、二维矩阵倒置、单向链表去重。

记录解题时的要点：

1. 从结果看 `class Corgi extends Dog implements RowdyDog` 中的 implements 指 Corgi 实现了 RowdyDog，而非 Dog 实现了 RowdyDog，Dog 的实例无法访问 RowdyDog 中的方法。

2. 父类变量保存一个子类实例时，变量只能调用父类声明的方法。即便子类实现了一个方法，但父类没有该方法，调用它的写法会报编译错误。

3. 数组翻转即首尾元素交换位置

4. 记录二维矩阵倒置的实现：

```java
// 倒转一个 2D 三角形矩阵，下一行比上一行长度小1，底行长度为1
public static int[][] transposeTriangular(int[][] A) {
    int rows = A.length;
    int[][] newArr = new int[rows][];

    for(int i=0; i<rows; i++) {
        int subLength = A[i].length;
        newArr[i] = new int[subLength];;

        // fill subArr
        for(int j=0; j<subLength; j++) {
            newArr[i][j] = A[j][i];
        }
    }
    return newArr;
}

// 倒转一个二维矩阵，下一行小于等于上一行的长度，不严格限定小 1
public static int[][] transposeTriangular2(int[][] A) {
    if (A.length == 0) {
        return new int[0][];
    }
    int[][] transpose = new int[A[0].length][];
    int t_row_len = A.length;
    for (int i = 0; i < transpose.length; i++) {
        while (A[t_row_len - 1].length <= i) {
            t_row_len -= 1; // 向上移动，直到能取到第 i 号元素
        }
        transpose[i] = new int[t_row_len];
        for (int j = 0; j < t_row_len; j++) {
            transpose[i][j] = A[j][i];
        }
    }
    return transpose;
}
```

倒置的意思是，原矩阵的行变为倒置后的矩阵的列，新矩阵的第 i 行的长度，等于原矩阵第 i 列的长度。

当矩阵为三角形矩阵时，实现比较简单。当它不一定是三角形时，新矩阵每行的长度无法预先知道，需要计算，计算依据的规律是，当计算第 i 列元素的个数时，从下往上找到能取到第 i 个元素的行，该及以上的行的第 i 列元素都会成为新矩阵的第 i 行元素。

5. 将一个已排序的单向链表去重

`1 -> 1 -> 2 -> 2 -> 2 -> 3` 的链表经过去重后变为 `1 -> 2 -> 3`。

去重的问题都需要两个指针。

```java
public static void  removeDuplicates(IntList p) {
    if(p.rest == null) {
        return;
    }
    IntList previous = p;
    IntList current = p.rest;
    while(current.rest != null) {
        if(current.first == previous.first) {
            previous.rest = current.rest;
        } else { // not the same and current is bigger than previous
            previous = current;
        }
        current = current.rest;
    }
}
```