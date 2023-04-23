# Exam Level 2

## 说明

第二周第二次讨论作业，考查基本数据数据结构(Map, List)的使用，以及类的静态方法、实例方法的区别。

作业要求：[Introduction to Java - Exam Level 2](https://drive.google.com/file/d/1uVmerLST6KTWih6JFgB2XwpKWH8XiXPX/view)

作业分两部分：

- 第一部分，设计一个衣柜（Closet）类，用于保存衣服（Clothing），并根据要求对保存的衣服进行操作。
- 第二部分，给定一个 Book 类和一个 Library 类的实现，回答问题。

## 回答与体会

第一部分的回答见 `./src/Closet.java`，第二部分的回答见 `./src/BookLibrary.java`

总体体会：

- 完成第一部分使用较多嵌套数据类型，如 `Map<String, List<Clothing>> colorItems`，表示 colorItems 是个 Map 对象，它的 key 是字符串类型，value 是一个 List 类型。这个 List 类型中的每个元素是 Clothing 类型。以前写 TypeScript 时对类型中嵌套类型的写法很抓马，哪知切换到 Java 后，这种写法变得很自然，其中的原因，我认为是 Java 提供了很多实用的数据类型，使用变量的时候会先思考要用哪种结构保存数据，反观 JavaScript，保存数据最常用的是对象，或者把对象放到数组中，JS 中的对象的功能太宽泛了，什么数据都能用对象表示，以至于可以略过选择数据结构的步骤，直接进入到对象的创建和使用中。

- 第二部分要求区别类的静态属性、静态方法、实例属性和实例方法的使用，这里得到的经验是，1）对所有实例都一样的属性，可以用静态变量保存，没必要每个实例都保存一份属性；2）可以在实例方法中访问静态属性，但不要修改静态属性。
