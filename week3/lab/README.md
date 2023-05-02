# Lab03 测定数据结构的运行时间

本周 [Lab 03](https://sp23.datastructur.es/materials/lab/lab03/)，使用经验主义的方式，测试单向链表 `SLList` 和 `AList` 的执行时间。

测试了两种情况：

1. 测定**构建**一个数据结构的时间，如给一个 `AList` 添加 1000、2000 直到 128000 个元素需要的平均时间，`AList` 基于数组实现，给数组不断添加元素最终达到数组的上限，这时需要另建一个大容量的数组，将当前数组全部拷贝至新数组，再添加新元素。

2. 测定一个数据结构在不同容量下，执行某个方法的时间。如，测试一个 `SLList` 在元素数量分别为 1000、2000 直到 128000 的情况下，执行 `getLast()` 的时间。课程中实现的 `SLList` 是个单向链表，单向链表获取最后一个元素需要遍历所有节点，因此 `getLast()` 操作会随着 N 的增加而增加。在现实环境中我们期望链表的 `getLast()` 的操作也是常量，如 Java 自带的 `LinkedList`，不管列表中有多少元素，执行 `get(list.size(). - 1)` 的时间都是常量。

介绍这个 Lab 测定时间的方法，课程中一个 `TimingData` 对象保存一次测试的所有时间数据，`TimingData` 的结构如下：

```java
public class TimingData {
    private List<Integer> Ns;
    private List<Double> times;
    private List<Integer> opCounts;

    public TimingData(List<Integer> Ns, List<Double> times, List<Integer> opCounts) {
        this.Ns = Ns;
        this.times = times;
        this.opCounts = opCounts;
    }
    public List<Integer> getNs() {
        return this.Ns;
    }
    public List<Double> getTimes() {
        return this.times;
    }
    public List<Integer> getOpCounts() {
        return this.opCounts;
    }
}
```

- `Ns`： 表示所测试的数据结构的元素数量的取值，如希望测定一个链表的 `getLast()` 操作在元素 1000，2000，直到 128000 个的情况中的时间是否常量，就要在 N 为 `{1000，2000,...,128000}` 的情况下分别测试，一个 N 对应一个 time。
- `times`：表示在容量为 N 的数据结构上测试某操作 opCounts 次的**总时间**。
- `opCounts`：表示在容量为 N 的数据结构上测试某操作的次数。比如，测试链表的 `getLast()` 操作，在一个容量为 1000 的链表上，会重复执行 `10000` 次，记录总时间 time，从而得到单词操作的平均时间。

统计时间用了包 `edu.princeton.cs.algs4.Stopwatch`

下面完整一个的测试单向链表 `SLList` 的 `getLast()` 的时间，分别测试在容量为 1000, 2000, 直到 128000 情况下的表现。

```java
package timing;

import edu.princeton.cs.algs4.Stopwatch;
import org.knowm.xchart.QuickChart;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Experiments {
  public static TimingData timeSLListGetLast() {
      List<Integer> Ns = new ArrayList<>();
      List<Double> times = new ArrayList<>();
      List<Integer> opCounts = new ArrayList<>();

      int ops = 10000;
      for(int N=1000; N<=128000; N*=2) {
        // 构建容量为 N 的链表
        SLList<Integer> list = new SLList<>();

          for(int i=0; i<N; i+=1) {
              list.addLast(i);
          }
          // 开始测试
          Ns.add(N);
          opCounts.add(ops);
          Stopwatch sw = new Stopwatch();
          for(int i=0; i<ops; i++) {
              list.get(list.size() - 1);
          }
          times.add(sw.elapsedTime());
      }

      return new TimingData(Ns, times, opCounts);
    }

    public static void main(String[] args) {
        TimingData td = timeSLListGetLast();
        // Modify this line to make the chart title make sense
        String title = "SLList GetLast Test";

        // Convert "times" (in seconds) and "opCounts" to nanoseconds / op
        List<Double> timesUsPerOp = new ArrayList<>();
        for (int i = 0; i < td.getTimes().size(); i++) {
            timesUsPerOp.add(td.getTimes().get(i) / td.getOpCounts().get(i) * 1e6);
        }

        // 打印测试结果表格
        printTimingTable(td);

        // 生成测试结果统计表
        XYChart chart = QuickChart.getChart(title, "N", "time (us per op)", "Time", td.getNs(), timesUsPerOp);
        new SwingWrapper<>(chart).displayChart();
    }

    // 工具方法，打印 TimingData 中的统计数据
    private static void printTimingTable(TimingData data) {
      System.out.printf("%12s %12s %12s %12s\n", "N", "time (s)", "# ops", "microsec/op");
      System.out.println("------------------------------------------------------------");
      for (int i = 0; i < data.getNs().size(); i += 1) {
          int N = data.getNs().get(i);
          double time = data.getTimes().get(i);
          int opCount = data.getOpCounts().get(i);
          double timePerOp = time / opCount * 1e6;
          System.out.printf("%12d %12.2f %12d %12.2f\n", N, time, opCount, timePerOp);
      }
    }
}

```