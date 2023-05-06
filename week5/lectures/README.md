# week5 lectures 笔记

## lecture 12  Iterators, Object Methods

- Java 中的 Set 结构，基于 Array 实现 Set 结构 ArraySet
- 使 ArraySet 支持增强版的 for 循环
  1. 在类中添加一个 `iterator()` 方法，返回一个 `Iterator<T>` 对象，[Iterator<T>](https://docs.oracle.com/javase/8/docs/api/java/util/Iterator.html)是一个接口，它要求实现 `hasNext()` 和 `next()` 方法
  2. 在自己数据结构类中创建类，实现 Iterator 接口
- 添加 toString 方法：该方法返回对象的字符串表示，Object 类的 toString 方法返回`类名@内存地址`，实现自己的数据结构时可重写该方法使之支持打印。
  - 实现时注意由于字符串是不可改变的，直接拼接字符串性能较低，用 StringBuilder 生成字符串
- 添加 equal(Object) 方法：Object 实现的 equal 的用的 `==`，可覆盖此方法，实现对象比较，详见 `./lectures/README.md`

```java
@Override
public Boolean equal(Object o) {
  if (this == o) { return true; }
  if (o instanceof ArraySet otherO) {
    if(this.size != otherO.size) { return false; }
    for(T item : o) {
      if(!otherO.contains(item)) { return false; }
    }
    return true;
  }
  return false;
}
```

Java 中的 instanceof 语法，`o instanceof ArraySet otherO` 做了两件事：
 1. 判断 o 的动态类型是否为 ArraySet，或者后者的子类；
 2. 将变量 o 转型为静态类型为 ArraySet 的一个新变量 otherO；

这种做法兼容 o 是 null 的情况。

及：支持泛型的类静态方法的写法：

```java
public class ArraySet<T> {

  // 第一个 <SomeItem> 表示这是个泛型方法
  // ArraySet<SomeItem> 表示返回的是 ArraySet<SomeItem> 独享
  // SomeItem... items，表示 items 是一组类型为 SomeItem 的参数，items 可迭代
  public static <SomeItem> ArraySet<SomeItem> of(SomeItem... items) {
    ArraySet<SomeItem> newSet = new ArraySet<>();
    for(SomeItem x : items) {
      newSet.add(x);
    }
    return newSet;
  }
}

// 使用该静态方法
ArraySet<String> strSet = ArraySet.of("Hello", "World");
```

### lecture 12 练习题

1. 修改课程中 ArraySetIterator 类，使它的构造函数接受一个 Comparator<T> 对象和泛型 T 的对象 ref，让迭代器只返回大于 T 的items：

```java
private class ArraySetIterator implements Iterator<T> {
	private int pos;
  private Comparator<T> cp;
  private T ref;

	
	public ArraySetIterator(Comparator<T> cp, T ref) {
		pos = 0;
    this.cp = cp;
    this.ref = ref;
	}
	
	public boolean hasNext() { 
		return pos < size; 
	}
	
	public T next() {
	 	T returnItem = items[pos];
    while(cp.compare(returnItem, ref) <= 0) {
      pos += 1;
      returnItem = items[pos];
    }
	 	return returnItem;
	}
}
```