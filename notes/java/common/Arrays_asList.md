## java 的 Arrays.asList() 不能 remove() 和 add()

有时候我们会用 Arrays.asList() 来将一个数组转化成一个 List，然而在这样的操作之后，如果我们在新的 list 上去执行 remove() 或者 add() 操作的时候会报异常:
```java
public static void main(String[] args) {
    List<Integer> iList = Arrays.asList(1, 2, 3, 4);
    iList.remove(0);
}
```
异常如下:
```java
Exception in thread "main" java.lang.UnsupportedOperationException
	at java.util.AbstractList.remove(AbstractList.java:161)
	at com.ctrip.communication.util.TestUtil.main(TestUtil.java:14)
```

### 源码分析

通过检查 Arrays.asList() 这个方法我们可以发现它的确是返回了一个 ArrayList:
```java
public static <T> List<T> asList(T... a) {
    return new ArrayList<>(a);
}
```
然而再继续查看返回的 ArrayList 的时候，我们可以发现它其实不是 java.util 包下我们通常以为的 ArrayList，而是在 Arrays 这个类的内部的一个内部类：
```java
private static class ArrayList<E> extends AbstractList<E>
        implements RandomAccess, java.io.Serializable {}
```
它继承了 AbstractList，但是却没有重写 remove() 和 add() 方法，而 AbstractList 中这两个方法默认是抛出 UnsupportedOperationException 这个异常的，因此我们在执行这两个方法的时候会发现上述异常。

### 解决
如果想要使用 remove() 或者 add()，我们可以对上述 iList 进行进一步转化，把它变成一个真正的 java.util.ArrayList 类型，方法大致如下：
```java
public static void main(String[] args) {
    List<Integer> iList = Arrays.asList(1, 2, 3, 4);
    List<Integer> iArrList = new ArrayList<>(iList.size());
    iArrList.addAll(iList);
    iArrList.remove(0);
    System.out.println(iArrList);
}
```

对此方法我们可以进行封装成一个通用的方法：
```java
public static <T> List<T> toArrayList(T... item){
    List<T> iArrList = new ArrayList<>(item.length);
    iArrList.addAll(Arrays.asList(item));
    return iArrList;
}
```

java8 以后提供了 stream，通过它我们可以进一步简化代码：
```java
public static <T> List<T> toArrayList1(T... item){
    return Arrays.stream(item).collect(Collectors.toList());
}
```