## CompletableFuture：异步编程
[烧水泡茶](https://github.com/lidonggg/Learning-notes/tree/master/java/src/main/java/com/lidong/java/concurrent/completablefuture/BoilTeaWithCompletableFuture.java)

异步编程就是利用多线程的优势，把串行的任务并行化，java 从 1.8 版本提供了 CompletableFuture 这个工具类来支持异步编程，它的内部实现很复杂（包含 50 多个方法 ），但是使用起来却很简单。

为了充分了解它，我们这里将再次实现在 **Future** 一节中的“烧水泡茶”的例子，为了充分体现出 CompletableFuture 的优势，这次我们会用三个线程来将其分成三个任务，分工方法如下图所示：

<div align=center><img src="https://github.com/lidonggg/Learning-notes/blob/master/imgs/boilteaWithCompletableFuture.png"/></div>
<br>
<div align=center>烧水泡茶工序图</div>
<br>

### 创建 CompletableFuture 对象

CompletableFuture 提供了四个静态方法来为一段异步执行的代码创建 CompletableFuture 对象，四个方法签名如下：
```java
// 以下两个方法使用默认的线程池
public static CompletableFuture<Void> runAsync(Runnable runnable);
public static <U> CompletableFuture<U> supplyAsync(Supplier<U> supplier);

// 以下两个方法可以指定线程池
public static CompletableFuture<Void> runAsync(Runnable runnable, Executor executor);
public static <U> CompletableFuture<U> supplyAsync(Supplier<U> supplier, Executor executor)
```
前两个方法之间的区别是：Runnable 接口的 run() 方法没有返回值，而 Supplier 接口的 get() 方法是有返回值的。

前两个和后两个方法的区别是后两个方法可以指定线程池参数。

如果在创建 CompletableFuture 对象时不指定线程池参数，则会默认使用公共的 ForkJoinPool 线程池，它默认创建的线程数是 CPU 的核数，当然通过 JVM option：Djava.util.concurrent.ForkJoinPool.common.parallelism 也可以设置 ForkJoinPool 线程池的线程数。这样做的坏处是如果所有的 CompletableFuture 都共享一个线程池，那么一旦有任务执行一些很慢的 /O 操作，就会导致线程池中所有的线程都阻塞在 I/O 操作上，从而造成线程饥饿，影响整个系统的性能。因此，最好要根据不同的业务来创建不同的线程池，以避免互相干扰。

CompletableFuture 对象创建完成之后，会自动异步执行 runnable.run() 方法或者 supplier.get() 方法。由于 CompletableFuture 类实现了 Future 接口，所以我们可以通过 Future 接口来获取异步操作的执行结果；另外 CompletableFuture 类还实现了 CompletionStage 接口。

### CompletionStage 接口
类似于现实中的分工，任务都是有时序关系的，比如**串行关系**、**并行关系**、**汇聚关系**等。例如在上图的“烧水泡茶”的分工方案中，洗水壶和烧开水就是串行关系，烧开水和洗茶壶、洗水杯之间就是并行关系，而烧开水、拿茶杯和最后的泡茶就是（AND）汇聚关系。CompletionStage 可以很清晰地描述以上几种关系，并且它还提供了非常方便的异常处理。

#### 1. 描述串行关系
CompletionStage 主要是利用 thenApply、thenAccept、thenRun 以及 thenCompose 这四个系列的接口来描述串行关系的。相关的函数签名如下：
```java
// 当一个线程依赖另一个线程时，可以使用 thenApply 方法来把这两个线程串行化
public <U> CompletableFuture<U> thenApply(Function<? super T,? extends U> fn);
public <U> CompletionStage<U> thenApplyAsync(Function<? super T,? extends U> fn);
public <U> CompletionStage<U> thenApplyAsync(Function<? super T,? extends U> fn, Executor executor);
// 接收任务的处理结果，并消费处理，无返回结果
public CompletableFuture<Void> thenAccept(Consumer<? super T> action);
public CompletionStage<Void> thenAcceptAsync(Consumer<? super T> action);
public CompletionStage<Void> thenAcceptAsync(Consumer<? super T> action, Executor executor);
// 跟 thenAccept 方法不一样的是，不关心任务的处理结果，只要上面的任务执行完成，就开始执行 thenAccept
public CompletableFuture<Void> thenRun(Runnable action);
public CompletionStage<Void> thenRunAsync(Runnable action);
public CompletionStage<Void> thenRunAsync(Runnable action, Executor executor);
// thenCompose 方法允许你对两个 CompletionStage 进行流水线操作，第一个操作完成时，将其结果作为参数传递给第二个操作
public <U> CompletableFuture<U> thenCompose(Function<? super T, ? extends CompletionStage<U>> fn);
public <U> CompletionStage<U> thenComposeAsync(Function<? super T, ? extends CompletionStage<U>> fn);
public <U> CompletionStage<U> thenComposeAsync(Function<? super T, ? extends CompletionStage<U>> fn, Executor executor);
```

thenApply 系列函数里参数 fn 的类型是接口 Function<T, R>，这个接口里与 CompletionStage 相关的方法是 R apply(T t) ，这个方法既能接收参数也支持返回值，所以 thenApply 系列方法返回的是 CompletionStage<R>。

而 thenAccept 系列方法里参数 consumer 的类型是接口 Consumer<T>，这个接口里与 CompletionStage 相关的方法是 void accept(T t) ，这个方法虽然支持参数，但却不支持回值，所以 thenAccept 系列方法返回的是 CompletionStage<Void>。

thenRun 系列方法里 action 的参数是 Runnable，所以 action 既不能接收参数也不支持返回值，所以 thenRun 系列方法返回的也是 CompletionStage<Void>。

这些方法里面 Async 代表的是异步执行 fn、consumer 或者 action。其中，需要你注意的是 thenCompose 系列方法，这个系列的方法会新创建出一个子流程，最终结果和 thenApply 系列是相同的。

以下代码展示了 thenApply() 的使用方法：
```java
CompletableFuture<String> f0 = 
    CompletableFuture.supplyAsync(
        () -> "Hello World")
    .thenApply(s -> s + " QQ")
    .thenApply(String::toUpperCase);

System.out.println(f0.join());
// 输出结果
HELLO WORLD QQ
```

#### 2. 描述 AND 汇聚关系
CompletionStage 接口里面描述 AND 汇聚关系，主要是 thenCombine、thenAcceptBoth 和 runAfterBoth 系列的接口，这些接口的区别也是源自 fn、consumer、action 这三个核心参数不同。

```java
// thenCombine 会把 两个 CompletionStage 的任务都执行完成后，把两个任务的结果一块交给 thenCombine 来处理
public <U,V> CompletionStage<V> thenCombine (CompletionStage<? extends U> other, BiFunction<? super T,? super U,? extends V> fn);
public <U,V> CompletionStage<V> thenCombineAsync(CompletionStage<? extends U> other, BiFunction<? super T,? super U,? extends V> fn);
public <U,V> CompletionStage<V> thenCombineAsync(CompletionStage<? extends U> other, BiFunction<? super T,? super U,? extends V> fn, Executor executor);
// 当两个CompletionStage都执行完成后，把结果一块交给thenAcceptBoth来进行消耗
public <U> CompletionStage<Void> thenAcceptBoth(CompletionStage<? extends U> other, BiConsumer<? super T, ? super U> action);
public <U> CompletionStage<Void> thenAcceptBothAsync(CompletionStage<? extends U> other, BiConsumer<? super T, ? super U> action);
public <U> CompletionStage<Void> thenAcceptBothAsync(CompletionStage<? extends U> other, BiConsumer<? super T, ? super U> action, Executor executor);
// 两个CompletionStage，都完成了计算才会执行下一步的操作（Runnable）
public CompletionStage<Void> runAfterBoth(CompletionStage<?> other, Runnable action);
public CompletionStage<Void> runAfterBothAsync(CompletionStage<?> other, Runnable action);
public CompletionStage<Void> runAfterBothAsync(CompletionStage<?> other, Runnable action, Executor executor);
```
#### 3. 描述 OR 汇聚关系
CompletionStage 接口里面描述 OR 汇聚关系，主要是 applyToEither、acceptEither 和 runAfterEither 系列的接口，这些接口的区别也是源自 fn、consumer、action 这三个核心参数不同。

```java
// 两个CompletionStage，谁执行返回的结果快，就用那个CompletionStage的结果进行下一步的转化操作。
public <U> CompletionStage<U> applyToEither(CompletionStage<? extends T> other, Function<? super T, U> fn);
public <U> CompletionStage<U> applyToEitherAsync(CompletionStage<? extends T> other, Function<? super T, U> fn);
public <U> CompletionStage<U> applyToEitherAsync(CompletionStage<? extends T> other, Function<? super T, U> fn, Executor executor);
// 两个CompletionStage，谁执行返回的结果快，就用那个CompletionStage的结果进行下一步的消耗操作
public CompletionStage<Void> acceptEither(CompletionStage<? extends T> other, Consumer<? super T> action);
public CompletionStage<Void> acceptEitherAsync(CompletionStage<? extends T> other, Consumer<? super T> action);
public CompletionStage<Void> acceptEitherAsync(CompletionStage<? extends T> other, Consumer<? super T> action, Executor executor);
// 两个CompletionStage，任何一个完成了都会执行下一步的操作（Runnable）
public CompletionStage<Void> runAfterEither(CompletionStage<?> other, Runnable action);
public CompletionStage<Void> runAfterEitherAsync(CompletionStage<?> other, Runnable action);
public CompletionStage<Void> runAfterEitherAsync(CompletionStage<?> other, Runnable action, Executor executor);
```
下面的示例代码展示了如何使用 applyToEither() 方法来描述一个 OR 汇聚关系:
```java
CompletableFuture<String> f1 = 
    CompletableFuture.supplyAsync(()->{
        int t = getRandom(5, 10);
        sleep(t, TimeUnit.SECONDS);
        return String.valueOf(t);
});

CompletableFuture<String> f2 = 
    CompletableFuture.supplyAsync(()->{
        int t = getRandom(5, 10);
        sleep(t, TimeUnit.SECONDS);
        return String.valueOf(t);
});

CompletableFuture<String> f3 = 
    f1.applyToEither(f2,s -> s);

System.out.println(f3.join());
```
#### 4. 异常处理
在非异步编程里面，我们可以使用 try{} catch{} 来捕获并处理异常，但是在异步编程里面，这个方法就不适用了，因此 CompletionStage 接口给我=们提供了一套简便的异常处理方案，它的使用特别简单，下面是相关代码，他们的使用与串行操作一样，都支持链式编程：
```java
// catch{}
public CompletionStage<T> exceptionally(Function<Throwable, ? extends T> fn);
// finally{}，不支持返回结果
public CompletionStage<T> whenComplete(BiConsumer<? super T, ? super Throwable> action);
public CompletionStage<T> whenCompleteAsync(BiConsumer<? super T, ? super Throwable> action);
public CompletionStage<T> whenCompleteAsync(BiConsumer<? super T, ? super Throwable> action, Executor executor);
// finally{}，支持返回结果
public <U> CompletionStage<U> handle(BiFunction<? super T, Throwable, ? extends U> fn);
public <U> CompletionStage<U> handleAsync(BiFunction<? super T, Throwable, ? extends U> fn);
public <U> CompletionStage<U> handleAsync(BiFunction<? super T, Throwable, ? extends U> fn, Executor executor);
```
下面的代码展示了 exceptionally() 的使用方法：
```java
CompletableFuture<Integer> 
    f0 = CompletableFuture
        .supplyAsync(()->7/0))
        .thenApply(r->r*10)
        .exceptionally(e->0);
System.out.println(f0.join());
```
exceptionally() 类似于 try{} catch{} 中的 catch{} ，而 whenComplete 和 handle 系列则类似于 try{} catch{} finally{} 中的 finally{}，无论是否发生异常，都会执行 whenComplete 中的 consumer 和 handle 中的回调函数 fn。whenComplete 和 handle 的区别在于前者不支持返回结果，而后者支持返回结果。

### 实现 CompletableFuture 版本的“烧水泡茶”
通过以上的分析以及我们的分工方案，我们可以很轻松地实现出我们的“烧水泡茶”程序了：
```java
public static void boilTea() {
    CompletableFuture<String> t2 = CompletableFuture.supplyAsync(() -> {
        System.out.println("t2：洗茶壶======");
        sleep(1, TimeUnit.SECONDS);
        System.out.println("t2：洗茶杯======");
        sleep(2, TimeUnit.SECONDS);
        System.out.println("t2：拿茶叶======");
        sleep(1, TimeUnit.SECONDS);
        return "龙井";
    });

    CompletableFuture<Void> t1 = CompletableFuture.runAsync(() -> {
        System.out.println("t1：洗水壶======");
        sleep(1, TimeUnit.SECONDS);
        System.out.println("t1：烧开水======");
        sleep(15, TimeUnit.SECONDS);
    });

    CompletableFuture<String> t3 = t1.thenCombine(t2, (tt, tf) -> {
        System.out.println("t1：拿到茶叶" + tf);
        System.out.println("t1：泡茶=======");
        return "上茶：" + tf;
    });

    System.out.println(t3.join());
}

private static void sleep(int t, TimeUnit unit) {
    try {
        unit.sleep(t);
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
}
```
### 总结
CompletableFuture 为异步编程提供了很大的便捷性，它能够满足大多数情况下简单的异步编程，同时 java9 还提供了更加完备的 Flow API，异步编程逐渐趋于成熟。

