# Discussion 03

## 第一次讨论

第三周第一次讨论内容是一份练习题，见 [Discussion 03](https://drive.google.com/file/d/1qiF-aEYKl6-Y8gsf_SfHSjlgQ9SlLHTb/view)。

参考答案见：[discussion03_sol](https://drive.google.com/file/d/1b9-OZnczWknxe9huqJhUkjIjhEqNuF_e/view)

第四题有点意思，将一个一维链表保存到 x 行、y 列的二维数组中，链表的节点可能不满二维数组，也可能超过二维数组，超过的部分不计。

解题的基本思路是，每次获取一次链表的节点，插入到二维数组中下一个空位。核心过程是：1）算出链当前的节点是第几个插入的，2）根据这个数字算出它要放到二维数组的哪一行哪一列。如，链表：`5->3->7->2->8`，放到一个 `2 × 3` 的二维数组，得到的二维数组应为 `int[][] result = {{5,3,7}, {2,8,0}};`，放不满位置用 0 填充。其过程为：

- 第一次操作，初始化一个索引值 num 为 0， 取出链表的第一个节点 5，它应该放到二维数组`result[0][0]` 的位置，用 num 进行计算是，`int row = num / 3`，`int col = num % 3`，将 num 值增加 1，进行下一个节点的存放
- 第二次操作，num 为 1，链表的节点为 3，它位于二维数组中的第 `1 / 3 = 0` 行，第 ` 1 % 3 = 1` 列，赋值 `result[0][1] = 1; `，将 num 值增加 1，进行下一个节点的操作；
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