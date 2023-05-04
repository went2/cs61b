# Week4 Lectures 笔记

## 1. 实现继承（Implementation Inheritence）

实现继承是接口继承以外另一种继承的方式，一般是一个类继承另一个类。实现继承使用关键词 `extends`，`extends` 使子类获得父类的所有成员，包括：
  1. 实例变量、静态变量；
  2. 方法；
  3. 内嵌的类；

- 子类不继承：父类的构造函数，也不继承 `private` 标识的变量。
- 子类构造函数与父类构造函数的交互：子类构造函数内必须首先调用父类的构造函数（通过 `super()` 调用），如果开发者没写，Java 会默认加上。如果父类的构造函数有重载方法，那么 `super()`, `super(x)` 是两个不同的调用，子类调用父类构造函数时要注意区分。
- Java 中的所有类都隐式地 `extends` Object 类。（JavaScript 中的所有对象的原型追溯到顶端也是 Object 的 prototype 对象）

及：interface 不继承 Object 类
再及：`extends` 表示的也是一种 `is-a` 关系，不能用来表示 `has-a` 关系

## 2. 封装

编程序的主要工作是对付复杂度。如何管理复杂度？
  1. 一层层地抽象，创建不同级别的抽象层，互相之间有清晰的屏障；
  2. Design for change(D Parnas) ，在写代码时就为变化留有余地：
    - 围绕对象组织程序；
    - Let objects deside how things are done;
    - Hide information others don't nedd. 隐藏信息

## 3. 编译时类型与类型转换 compile-time type and casting

Java 中的一个变量有两个类型：编译时类型（静态类型）和运行时类型（动态类型）。

编译时类型：
  - 在声明该变量时确定
  - 一经确定，这个变量的编译时类型不再改变

运行时类型：
  - 在变量初始化时确定（如使用 new）
  - 其类型就是变量实际指向的对象的类型
  - 影响调用实例方法时，调用哪个方法，比如，会优先调用子类覆盖实现的方法。

编译器在编译时会给赋值语句和函数调用操作做类型检验，检验的编译时类型：
  - 赋值语句：检验等号右边表达式的类型是否是左边的类型；
  - 函数调用：检查方法调用的返回类型是否是声明方法时的返回类型。

类型转换就是告诉 Java 将一个表达式的编译时类型当做另一个类型对待，从而通过类型检测，对执行没有影响，该出错还是会出错。

## 4. 高阶函数

高阶函数是一个把其他函数当做数据进行操作的函数。

Java 7 及以前没有直接指向函数的指针，使用高阶函数需要借助 interface + 类继承：
  1. 声明：声明一个 interface IA，包含函数的签名；
  2. 实现：用类 A 实现该接口定义的方法；
  3. 使用：在类 B 中实例化该类，在 B 的方法中接收 IA 类型的对象 a 为参数，在 B 方法内部通过 a 对象使用它的方法

## 5. 子类多态

假设我们要写一个函数 max，实现这个功能：比较两个对象的大小，将较大的对象用字符串打印出来。用函数式的方法来实现：

```python
def max(x, y, compare, stringify):
  ifcompare(x, y):
    return stringify(x)
  return stringify(y)
```

x, y 是需要比较的对象, compare 可以比较这两个对象，stringify 可以打印一个对象，这是用高阶函数进行实现的思路。

在 Java 中，我们要让“对象决定需要做什么事情”，用 OOP 的思路来实现 max 可能是这样：

```python
def printLarger(x, y):
  if x.isLarger(y):
    return x.str()
  return y.str()
```

对象 x 和 y 具备了比较大小和打印成字符串的能力，下面用更具体的例子说明在 Java 中如何处理这种通用情况。

假设有一个 max 方法，接收一个数组，数组中的元素是任意对象，这个 max 方法要返回其中“最大的对象”，怎么实现 max 呢？

```java
public static Object max(Object[] items) { }

public static void main(String[] args) {
  Dog[] dogs = {new Dog("Elyse", 3), new Dog("Anna", 5), new Dog("Jeffiny", 10)};
  Dog maxDog = (Dog) max(dogs);
  maxDog.bark();
}
```

上面提到写 Java 的思路是让对象决定做什么事情，所以真正实现比较大小能力的地方是在对象上实现，max 这里是调用有这些能力的对象。

要让对象有可比较的行为，先定义“可比较“行为的接口，然后让类实现这个接口。

```java
// 先声明一个接口，定义一种可比较的能力
interface MyComparable {
  // this 小于对象 o 返回负数，相等返回0，大于返回整数
  public int compareTo(Object o);
}

// 让具体的类实现接口，从而实现可比较的能力
public class Dog implements MyComparable {
  public String name;
  public int size;
  public Dog(String name, int size) {
    this.name = name;
    this.size = size;
  }
  public int compareTo(Object o) {
    Dog hahaDog = (Dog) o;
    return this.size - hahaDog.size;
  }
  public void bark() {
    System.out.println(this.name + " says: bark");
  }
}

// max 方法的参数是 MyComparable 对象，而不是 Object
public static MyComparable max(MyComparable[] items) {
  MyComparable maxObj = items[0];
  for(int i = 0; i < items.lenth; i += 1) {
    if(items[i].compareTo(maxObj) > 0) {
      maxObj = items[i];
    }
  }
  return maxObj;
}

// 后面如果要比较 Cat 对象，就让 Cat 实现 MyComparable
```

这里的重点是 max() 方法，虽然我们比较是 Dog 对象、Cat 对象，但 max 方法不指定比较的是猫还是狗，它只指定，如果要比较，那么这种对象必须是 MyComparable 的对象，这使得 max 方法可以独立于具体对象的实现，不管要比较的对象是什么，只要实现了 MyComparable，就能用 max 来比较。Josh Hug 说，这种方式，使得 max 即便是上古流传下来的代码，也可以正常运作。

Java 内置了 Comparable<T> 接口，用它改写 Dog 类，可以省去 compareTo 方法中的 casting。且有

```java
public class Dog implements Comparable<Dog> {
    public int compareTo(Dog o) {
    return this.size - hahaDog.size;
  }
}
```

## 6. Comparator

comparable 接口定义将自己和其他对象对比的行为，如果要进行其他规则的比较，需要另一个接口 Comparator：

```java
public interface Comparator<T> {
  int compare(T o1, T o2);
}
```

举例，让 Dog 类实现按名字首字母的顺序进行比较，要在 Dog 类中再定义一个类实现 Comparator

```java
public class Dog implements Comparable<Dog> {
  public int compareTo(Dog o) {
    return this.size - hahaDog.size;
  }

  private static class NameComparator implements Comparator<Dog> {
    public int compare(Dog dog1, Dog dog2) {
      return dog1.name.compareTo(dog2.name); // 借用了 String 实现的 comparaTo 方法
    }
  }

  // 对外提供一个 NameComparator 对象的 getter
  public static Comparator<Dog> getNameComparator() {
    return new NameComparator();
  }
}

// 使用 Dog 类的 NameComparator
// in DogLauncher.java
import java.util.Comparator;

public class DogLauncher {
  public static void main(String[] args) {
    Comparator<Dog> nc = Dog.getNameComparator();
    if(nc.compare(d1, d3) > 0) { // d1 comes later than d3 in alphabet
      d1.bark();
    } else {
      d3.bark();
    }
  }
}
```

这一节主要说明 Java 中通过 interface 中声明方法，由相应的类实现方法的方式，发挥其他语言中的回调函数的作用。