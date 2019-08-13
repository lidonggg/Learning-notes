## CompletionService：批量执行异步任务

[Dubbo Forking Cluster 模式](https://github.com/lidonggg/Learning-notes/tree/master/java/src/main/java/com/lidong/java/concurrent/completionservice/ForkingDemo.java)

考虑如下代码：
```java
// 创建线程池
ExecutorService executor = Executors.newFixedThreadPool(3);
// 异步获取 value1
Future<Integer> f1 = executor.submit(() -> getValue1());
// 异步获取 value2
Future<Integer> f2 = executor.submit(() -> getValue2());
// 异步获取 value3
Future<Integer> f3 = executor.submit(() -> getValue3());
    
// 获取 value1 并保存
r = f1.get();
executor.execute(() -> save(r));
// 获取 value2 并保存
r = f2.get();
executor.execute(() -> save(r));
// 获取 value3 并保存
r = f3.get();
executor.execute(() -> save(r));
```
上述代码通过并行执行三个取值任务并保存，类似的使用场景在服务端访问数据库的时候很常见，它虽然能够减少串行执行所带来的时间开销，但由于 Future.get() 方法是阻塞的，如果 f1.get() 耗时很久，那么接下来的两个 get() 方法都会等待它执行完毕，这种情况下，即使后面两个耗时很短，它们也无法先执行完毕，因此上述代码还可以进一步优化。

我们可以利用一个阻塞队列来实现先获取到值的先保存，如下所示：
```java
// 创建阻塞队列
BlockingQueue<Integer> bq =new LinkedBlockingQueue<>();
// getValue1 异步进入阻塞队列  
executor.execute(() -> bq.put(f1.get()));
// getValue2 异步进入阻塞队列  
executor.execute(() -> bq.put(f2.get()));
// getValue3 异步进入阻塞队列  
executor.execute(() -> bq.put(f3.get()));
// 异步保存
for (int i=0; i<3; i++) {
    Integer r = bq.take();
    executor.execute(()->save(r));
}  
```

然而 java SDK 提供了更为简便的实现方法，那就是 CompletionService。它的原理也是内部维护了一个阻塞队列，不同的是当任务执行结束，它会把任务执行结果的 Future 对象加入到阻塞队列中，而不是像上述代码一样把任务最终的执行结果放入阻塞队列中。

### 创建 CompletionService
CompletionService接口的实现类是 ExecutorCompletionService，它的构造方法有两个：
```java
public ExecutorCompletionService(Executor executor);
public ExecutorCompletionService(Executor executor,
                                     BlockingQueue<Future<V>> completionQueue)
```
如上述代码所示，这两个构造方法都需要传入一个线程池，如果不指定 completionQueue，则默认使用无界的 LinkedBlockingQueue，它存放的是任务执行结果的 Future 对象。

以下代码是上述小程序的 CompletionService 实现：
```java
// 创建线程池
ExecutorService executor = Executors.newFixedThreadPool(3);
// 创建 CompletionService
CompletionService<Integer> cs = new ExecutorCompletionService<>(executor);
// 异步获取 value1
cs.submit(() -> getValue1());
// 异步获取 value2
cs.submit(() -> getValue2());
// 异步获取 value3
cs.submit(() -> getValue3());
// 将询价结果异步保存到数据库
for (int i = 0; i < 3; i++) {
    Integer r = cs.take().get();
    executor.execute(() -> save(r));
}
```
### CompletionService 接口说明
CompletionService 接口提供了五个方法，方法签名如下：
```java
Future<V> submit(Callable<V> task);
Future<V> submit(Runnable task, V result);
Future<V> take() throws InterruptedException;
Future<V> poll();
Future<V> poll(long timeout, TimeUnit unit) throws InterruptedException;
```
其中两个 submit() 方法的作用就是任务提交，且两个都可以获取到返回值，用法与 ThreadPoolExecutor 中的 submit() 方法类似。

其余的三个方法都是与阻塞队列有关的，take()、poll() 都是从阻塞队列中获取并移除一个元素，它们的区别在于，如果阻塞队列为空，那么 take() 方法会被阻塞，而 poll() 方法会返回 null 值。poll(long timeout, TimeUnit unit)  方法支持以超时的方式获取并移除阻塞队列头部的一个元素，如果等待了 timeout unit 时间，阻塞队列还是空的，那么该方法会返回 null 值。

### 实现 Dubbo 的 Forking Cluster 模式
[代码如下](https://github.com/lidonggg/Learning-notes/tree/master/java/src/main/java/com/lidong/java/concurrent/completionservice/ForkingDemo.java)：
```java
public class ForkingDemo {
    public static int getValueFromIp1() {
        return 1;
    }
    public static int getValueFromIp2() {
        return 2;
    }
    public static int getValueFromIp3() {
        return 3;
    }

    /**
     * 取值
     *
     * @return int
     */
    public static Integer getValue() {
        ExecutorService es = Executors.newFixedThreadPool(3);
        CompletionService<Integer> cs = new ExecutorCompletionService<>(es);
        List<Future<Integer>> futures = new ArrayList<>();
        futures.add(cs.submit(ForkingDemo::getValueFromIp1));
        futures.add(cs.submit(ForkingDemo::getValueFromIp2));
        futures.add(cs.submit(ForkingDemo::getValueFromIp3));
        Integer res = 0;
        try {
            for (int i = 0; i < futures.size(); ++i) {
                res = cs.take().get();
                if (null != res) {
                    break;
                }
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            for (Future<Integer> future : futures) {
                future.cancel(true);
            }
        }

        return res;
    }

    public static void main(String[] args) {
        System.out.println(getValue());
    }
}
```

### 总结
当需要批量提交异步任务的时候建议使用 CompletionService。CompletionService 将线程池 Executor 和阻塞队列 BlockingQueue 的功能融合在了一起，能够让批量异步任务的管理更简单。除此之外，CompletionService 能够让异步任务的执行结果有序化，先执行完的先进入阻塞队列，利用这个特性，我们可以轻松实现后续处理的有序性，避免无谓的等待，同时还可以快速实现诸如 Forking Cluster 这样的需求。

CompletionService 的实现类 ExecutorCompletionService，需要我们自己创建线程池，虽看上去有些啰嗦，但好处是可以让多个 ExecutorCompletionService 的线程池隔离，这种隔离性能避免几个特别耗时的任务拖垮整个应用的风险。