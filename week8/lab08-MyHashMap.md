# week8 - Lab 08 MyHashMap

[MyHashMap](https://sp23.datastructur.es/materials/lab/lab08/)

上周的 Lab 是用 BST 实现 Map 结构，本周 Lab 要求用哈希表实现 Map，同样，课程给定了需要实现的 CS61B 接口要求、脚手架代码和注释

没做之前以为这比实现 BSTMap 要难，实际写的时候发现都是学过的东西，记录实现过程中的几个要点：

### 1. interface CS61B 的要求如下

```java
public interface Map61B<K, V> extends Iterable<K> {
    void put(K key, V value); // 添加、更新key

    V get(K key); // 返回指定 key 对应的 value，无则返回 null

    boolean containsKey(K key); // 返回是否包含指定 key

    int size(); // 返回已保存的 key 的数量

    void clear(); // 清除所有的 key

    Set<K> keySet(); // 返回一个包含所有 key 的 Set，可选

    V remove(K key); // 移除指定 key，返回对应的值，没有则返回 null，可选
}
```

重点操作是put、get

### 2. 使用哈希表实现这些接口考虑的问题

1. 用什么类型的数组？哈希表底层使用数组，数组的每个栏位又是一个独立的数据结构，可以是 LinkedList，可以是红黑树，甚至是 HashSet，需要提供切换的能力。这个问题由 Lab 提供的脚手架代码解决了，解法是：
  - 数组的类型是 `Collection<Node>[] buckets`，用 `Collection` 来指代一大片实现了该接口的具体结构，如 LinkedList, ArrayList 等等
  - 初始化数组：Java [不允许创建类型作为参数](https://docs.oracle.com/javase/tutorial/java/generics/restrictions.html#createArrays)（parameterized type）的数组，所以不能写 `new Collection<Node>[size]`，而使用 `new Collection[size]`（这么写 IDE 会提示 Unchecked assignment: 'java.util.Collection[]' to 'java.util.Collection<hashmap.MyHashMap.Node>[]'，先不管它了）。
  - 初始化数组的栏位：不直接用 `new LinkedList<>()`，而是用工厂方法，创建一个 `createBucket` 方法 返回 `new LinkedList<>()` 实例。

2. 无论是添加还是查询，都有通用过程：查询
  - 添加之前要先查询该 key 是否已存在，查询是一个数据结构的最常用的操作
  - key ===(`hashCode()`)==> 哈希码 ====(Math.floorMod)===> 在数组中的索引
  - 然后是对该索引位置的 Collection 类型的数据结构的操作，哈希表能让查询操作的运行时近乎常量在于，每个索引位置的数据结构的元素数量近乎常量

3. 哈希表的数组 resizing
  - 创建容量倍增的新数组
  - 从当前数组读取所有位置的 Collection 结构中的所有元素，重新做哈希和 reduce，得到它们在新数组中的位置，存入

4. Java 中类作为参数传入函数的写法

```java
// 声明
public static <K,V> MyHashMap<K, V> createBucketedMap(Class<? extends Collection> bucketType) {
  return new MyHashMap<>() {
    @Override
    protected Collection<Node> createBucket() { // 覆盖 MyHashMap 类中的 createBucket 方法
        try {
            return bucketType.getConstructor().newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
  }
}

// 使用
public class TestMyHashMapBuckets {
    @DisplayName("containsKey")
    @ParameterizedTest
    @MethodSource("bucketArguments")
    public void sanityGenericsTest(Class<? extends Collection> bucketType) {
      TestMyHashMap.containsKeyTest(createBucketedMap(bucketType));
    }

    private static Stream<Arguments> bucketArguments() {
      return Stream.of(
              Arguments.of(Named.of("LinkedList", LinkedList.class)),
              Arguments.of(Named.of("ArrayList", ArrayList.class)),
              Arguments.of(Named.of("HashSet", HashSet.class)),
              Arguments.of(Named.of("Stack", Stack.class)),
              Arguments.of(Named.of("ArrayDeque", ArrayDeque.class))
      );
    }
}
```
### 3. 性能测试

插入 `<String, Integer>` key/value pair 的性能对比

|序号|  是否随机插入  | 字符串长度  | 数量 N| MyHashMap | Java HashMap|
|---|---|---|---|---|---|
|1| 是 | 5 | 1,000,000 | 0.58s | 0.23s |
|2| 是 | 5 | 100,000,000 | 59.94s | 31.74s |
|3| 否 | N/A | 1,000,000 | 0.30s | 0.35s |
|4| 否 | N/A | 10,000,000 | 3.68s | 2.20s |

MyHashMap 和 Java 的 HashMap 在千万（及以下）级别的节点插入操作中性能差距不大，上亿级别的数量有很大差距。

后来 参考[这个文档 p7](https://www.cs.princeton.edu/~appel/HashTables.pdf)，对 HashMap 的定义进行了一点优化，删除了初始化数组时为每个 bucket 创建一个数据结构的做法，得到了性能提升：
  - 随机插入 1 亿个节点，在我机器上跑 46.72s，对比之前（59.94s）提升了 22%