## **Future**：烧水泡茶
[利用 FutureTask 实现最优“烧水泡茶”](https://github.com/lidonggg/Learning-notes/tree/master/java/src/main/java/com/lidong/java/concurrent/future/BoilTeaWithFutureTask.java)

[含有 result 参数的 submit() 方法](https://github.com/lidonggg/Learning-notes/tree/master/java/src/main/java/com/lidong/java/concurrent/future/SubmitWithResultDemo.java)

### 如何完成“烧水泡茶”的工序
“烧水泡茶”这个典故（例子）出自数学家华罗庚的《统筹方法》，主要的意思是这样的：完成烧水泡茶一共需要洗水壶（1分钟）、烧开水（15分钟）、洗茶壶（1分钟）、洗茶杯（2分钟）、拿茶叶（1分钟）、泡茶这几个任务，问我们最少需要几分钟就可以完成所有的步骤。如果我们采用串行化的方法一步一步执行上面的步骤，则除了泡茶之外至少需要19分钟，然而事实上我们完全可以不这样做。烧开水的前提是洗水壶，因此这两个步骤需要串行执行，但是在烧开水的过程中，我们有一个等待烧水完成的时间，在这段时间里，我们完全可以完成洗茶壶、拿茶杯和拿茶叶这三个任务。因此烧开水和后面的三个任务完全可以并行执行，因此我们可以得到如下图的最优执行过程：
<br>
<div align=center><img src="https://github.com/lidonggg/Learning-notes/blob/master/imgs/boiltea.png"/></div>
<br>
<div align=center style="font-size:10px">烧水泡茶工序图</div>
<br>
“烧水泡茶”这个典故（例子）出自数学家华罗庚的《统筹方法》，主要的意思是这样的：完成烧水泡茶一共需要洗水壶（1分钟）、烧开水（15分钟）、洗茶壶（1分钟）、洗茶杯（2分钟）、拿茶叶（1分钟）、泡茶这几个任务，问我们最少需要几分钟就可以完成所有的步骤。如果我们采用串行化的方法一步一步执行上面的步骤，则除了泡茶之外至少需要19分钟，然而事实上我们完全可以不这样做。烧开水的前提是洗水壶，因此这两个步骤需要串行执行，但是在烧开水的过程中，我们有一个等待烧水完成的时间，在这段时间里，我们完全可以完成洗茶壶、拿茶杯和拿茶叶这三个任务。因此烧开水和后面的三个任务完全可以并行执行，因此我们可以得到如下图的最优执行过程：
<br>
<div align=center><img src="https://github.com/lidonggg/Learning-notes/blob/master/imgs/boiltea-thread.png"/></div>
<br>
<div align=center>使用两个线程完成烧水泡茶</div>
<br>
“烧水泡茶”这个典故（例子）出自数学家华罗庚的《统筹方法》，主要的意思是这样的：完成烧水泡茶一共需要洗水壶（1分钟）、烧开水（15分钟）、洗茶壶（1分钟）、洗茶杯（2分钟）、拿茶叶（1分钟）、泡茶这几个任务，问我们最少需要几分钟就可以完成所有的步骤。如果我们采用串行化的方法一步一步执行上面的步骤，则除了泡茶之外至少需要19分钟，然而事实上我们完全可以不这样做。烧开水的前提是洗水壶，因此这两个步骤需要串行执行，但是在烧开水的过程中，我们有一个等待烧水完成的时间，在这段时间里，我们完全可以完成洗茶壶、拿茶杯和拿茶叶这三个任务。因此烧开水和后面的三个任务完全可以并行执行，因此我们可以得到如下图的最优执行过程：

从图中可以发现，优化之后，我们前五个步骤只需要 16 分钟，虽然在这个例子中时间成本降低的不是很多，但假设烧开水后面的三个步骤每个都需要 5 分钟，此时时间成本的降低将会非常明显。

如果要用 java 多线程的方式来实现上述任务该怎么做呢？

并发编程可以总结为三个核心问题：分工、同步和互斥。编写并发程序，首先要做的就是分工，所谓分工指的是如何高效地拆解任务并分配给线程。对于烧水泡茶这个程序，一种最优的分工方案可以是下图所示的这样：用两个线程 T1 和 T2 来完成烧水泡茶程序，T1 负责洗水壶、烧开水、泡茶这三道工序，T2 负责洗茶壶、洗茶杯、拿茶叶三道工序，其中 T1 在执行泡茶这道工序时需要等待 T2 完成拿茶叶的工序。对于 T1 的这个等待动作，你应该可以想出很多种办法，例如 Thread.join()、CountDownLatch，甚至阻塞队列都可以解决，不过今天我们用 Future 特性来实现。

### 通过 Future 提供的方法可以获取任务的执行结果
ThreadPoolExecutor 的 execute(Runnable task) 可以提交任务，但是由于它没有返回值，因此我们无法获取任务的执行结果。但是 java 通过 ThreadPoolExecutor 继承自 AbstractExecutorService 提供的三个 submit() 方法以及一个工具类 FutureTask 来支持获得线程执行结果的需求。

#### ThreadPoolExecutor 中的 submit() 方法
三个 submit() 方法的签名如下：
```java
// 提交 Runnable 任务
Future<?> submit(Runnable task);
// 提交 Callable 任务
<T> Future<T> submit(Callable<T> task);
// 提交 Runnable 任务及结果引用  
<T> Future<T> submit(Runnable task, T result);
```

它们返回的都是 Future 接口，Future 接口有 5 个方法，如下：
```java
// 取消任务
boolean cancel(boolean mayInterruptIfRunning);
// 判断任务是否已取消  
boolean isCancelled();
// 判断任务是否已结束
boolean isDone();
// 获得任务执行结果
get();
// 获得任务执行结果，支持超时
get(long timeout, TimeUnit unit);
```
通过这 5 个方法，我们提交的任务不但能够获取执行的结果，还可以取消任务。值得注意的是，上面的两个 get() 方法都是阻塞式的，如果调用的时候任务还没有执行完成，那么调用 get() 方法的线程会阻塞，直到任务执行完成。

3 个 submit() 方法的区别在于方法参数的不同，区别下：

- 提交 Runnable 任务的 Future<?> submit(Runnable task)：此方法的参数是一个 Runnable 接口，然而 Runnable 接口的 run() 方法是没有返回值的，因此此方法返回的 Future 仅可以用来断言任务已经结束了，类似于 Thread.join();
- 提交 Callable 任务的 <T> Future<T> submit(Callable<T> task)：此方法的参数是一个 Callable 接口，它只有一个 call() 方法，并且这个方法有返回值，所以这个方法返回的 Future 对象可以通过调用其 get() 方法来获取任务的执行结果；
- 提交 Runnable 任务以及结果引用的 <T> Future<T> submit(Runnable task, T result)：假设这个方法返回的 Future 对象是 f，那么 f.get() 的返回值就是此方法参数中的 result。它最普遍的用法如以下[代码](https://github.com/lidonggg/Learning-notes/tree/master/java/src/main/java/com/lidong/java/concurrent/future/SubmitWithResultDemo.java)所示，

```java
public class SubmitWithResultDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService es = Executors.newFixedThreadPool(1);
        Result result = new Result();
        Future<Result> future = es.submit(new Task(result),result);
        Result r = future.get();
        System.out.println(r.getCode());
    }
}

class Task implements Runnable {
    /**
     * result
     */
    private Result result;
    Task(Result result){
        this.result = result;
    }
    @Override
    public void run() {
        System.out.println("我被执行了");
        result.setCode(0);
        result.setMsg("success");
    }
}
class Result {
    /**
     * code
     */
    private int code;
    /**
     * message
     */
    private String msg;
    public int getCode() {
        return code;
    }
    public void setCode(int code) {
        this.code = code;
    }
    public String getMsg() {
        return msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }
}
```
#### FutureTask 工具类

FutureTask 工具类实现了 Runnable 和 Future 接口，它有两个构造函数，参数与上文的 submit() 方法类似：
```java
FutureTask(Callable<V> callable);
FutureTask(Runnable runnable, V result);
```
由于实现了 Runnable 接口，所以可以将 FutureTask 对象作为任务提交给 ThreadPoolExecutor 执行，也可以被 Thread 直接执行；又因为实现了 Future 接口，因此可以用来获取任务的执行结果。

提交给 ThreadPoolExecutor 的用法如下：
```java
// 创建 FutureTask
FutureTask<Integer> futureTask = new FutureTask<>(()-> 1+2);
// 创建线程池
ExecutorService es = Executors.newCachedThreadPool();
// 提交 FutureTask 
es.submit(futureTask);
// 获取计算结果
Integer result = futureTask.get();
```
直接被 Thread 执行的用法如下：
```java
// 创建 FutureTask
FutureTask<Integer> futureTask = new FutureTask<>(()-> 1+2);
// 创建并启动线程
Thread T1 = new Thread(futureTask);
T1.start();
// 获取计算结果
Integer result = futureTask.get();
```
### 利用 FutureTask 实现最优的“烧水泡茶”程序

分工方案在文章的最开头已经给出，通过两个线程即可实现，因此我们在这里实现两个 Callable 接口，分别执行 T1 和 T2 的操作，关键步骤就是在 T1 中要通过调用 T2 的 future.get() 方法（在上文说过这里的 get() 方法是阻塞的）来实现线程等待。实现[代码](https://github.com/lidonggg/Learning-notes/tree/master/java/src/main/java/com/lidong/java/concurrent/future/BoilTeaWithFutureTask.java)如下：
```java
public class BoilTeaWithFutureTask {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<Boolean> ft2 = new FutureTask<>(new Task2());
        FutureTask<Boolean> ft1 = new FutureTask<>(new Task1(ft2));
        new Thread(ft1).start();
        System.out.println(ft1.get());
    }
}

class Task1 implements Callable<Boolean> {
    /**
     * t1 在执行过程中需要等待 t2 执行完成，所以需要拥有 t2 变量
     */
    private FutureTask<Boolean> tf2;
    Task1(FutureTask<Boolean> tf2){
        this.tf2 = tf2;
    }
    @Override
    public Boolean call() throws Exception {
        System.out.println("T1: 洗水壶");
        TimeUnit.SECONDS.sleep(1);
        System.out.println("T1: 烧开水");
        // 烧开水过程中 t2 开始执行
        new Thread(tf2).start();
        TimeUnit.SECONDS.sleep(15);
        Boolean rtf2 = tf2.get();
        System.out.println("T1: 拿到茶叶: " + rtf2);
        System.out.println("T1: 泡茶");
        return true;
    }
}

class Task2 implements Callable<Boolean> {
    @Override
    public Boolean call() throws Exception {
        System.out.println("T2: 洗茶壶");
        TimeUnit.SECONDS.sleep(1);
        System.out.println("T2: 洗茶杯");
        TimeUnit.SECONDS.sleep(2);
        System.out.println("T2: 拿茶叶");
        return true;
    }
}
```
### 总结
利用 Future 接口可以获取线程的执行结果，由于其 get() 方法是阻塞的，因此也可以实现不同线程间的等待，所以说 Future 接口在实际中的实用性还是很高的。在使用 Future 的时候，最好是可以画一张执行过程图，类似于上文中的“烧水泡茶”过程，这样可以让我们在代码实现的时候更不容易出错。
