## Fork-Join：并行计算框架

- [斐波那契数列](https://github.com/lidonggg/Learning-notes/tree/master/java/src/main/java/com/lidong/java/concurrent/forkjoin/FibonacciTask.java)
- [单机版 MapReduce 统计单词数量](https://github.com/lidonggg/Learning-notes/tree/master/java/src/main/java/com/lidong/java/concurrent/forkjoin/SingleMapReduceToCountWords.java)

CompletableFuture 可以实现并行、聚合等任务模型，CompletionService 则可以实现批量并行，但是还有一种常见的任务模型它们没有覆盖到，那就是“分治”。分治的思想就是分而治之，把一个复杂的问题分解成若干个相似的子问题，再把这些子问题分解成更小的子问题，直到子问题可以直接求解，这种思想非常常见，例如排序算法中的归并排序就充分运用了分支的思想，例如大数据计算框架 MapReduce 等。

正因为分治思想在诸多领域都有广泛的应用，因此 java 并发包中也提供了一种叫做 Fork/Join 的并行计算框架，它就是用来支持分治任务模型的。

### Fork/Join 工作原理
Fork/Join 主要就是用来支持分治任务模型的，其中 Fork 对应的是分治任务模型里的任务分解， Join 对应的是结果合并。Fork/Join 计算框架主要包括两个部分，一部分是分治任务的线程池 ForkJoinPool，另一部分是分治任务 ForkJoinTask，它们的关系类似于 ThreadPoolExecutor 和 Runnable 的关系，都可以理解为提交任务到线程池。

ForkJoinTask 是一个抽象类，它的方法有很多，最核心的是 fork() 和 join()，从命名上来看就可以知道它们分别对应分治和合并，fork() 会异步地执行一个子任务，而 join() 方法会阻塞当前线程来等待子任务的执行结果。

ForkJoinTask 有两个子类，分别是 RecursiveAction 和 RecursiveTask，它们都是通过递归的方式来处理分治任务的，切同样都是抽象类。这两个子类都定义了抽象方法 compute()，区别是前者的 compute() 没有返回值，而后者的该方法有返回值。在使用的时候，我们需要自己定义子类去扩展它们。

Fork/Join 并行计算的核心组件是 ForkJoinPool。与 ThreadPoolExecutor 类似，ForkJoinPool 本质上也是一个“生产者-消费者”模型的实现，但它更加智能。ForkJoinPool 内部有多个任务队列，当我们通过 ForkJoinPool 的 invoke() 或者 submit() 方法提交任务的时候，ForkJoinPool 会根据一定的路由规则把任务提交到一个任务队列中，如果任务在执行的过程中会创建出子任务，那么这些子任务会提交到该任务工作线程对应的任务队列中。

ForkJoinPool 支持“任务窃取”机制，如果工作线程空闲了，那它可以“窃取”其他工作任务队列里的任务，这样的话，可以保证所有的工作线程都不会空闲下来，从而提高整体性能。ForkJoinPool 中的任务队列采用双端队列，获取任务和窃取任务分别从不同的两端进行消费，这样可以尽量减少不必要的数据竞争。

### 使用 Fork/Join 实现斐波那契数列
java 官方示例提供了斐波那契数列的 Fork/Join 方法的实现。首先需要创建一个分治任务线程池以及计算斐波那契数列的分治任务，之后通过分治任务线程池的 invoke() 方法来启动分治任务。由于计算斐波那契数列需要返回值，因此它继承自 RecursiveTask。分治任务 Fibonacci 需要实现 compute() 方法，这个方法里面的逻辑和普通计算斐波那契数列非常类似，区别之处在于计算 Fibonacci(n - 1)  使用了异步子任务，这是通过 f1.fork() 这条语句实现的。

[代码](https://github.com/lidonggg/Learning-notes/tree/master/java/src/main/java/com/lidong/java/concurrent/forkjoin/FibonacciTask.java)如下所示：
```java
public class FibonacciTask extends RecursiveTask<Integer> {
    final int n;
    public FibonacciTask(int n) {
        this.n = n;
    }
    @Override
    protected Integer compute() {
        if (n <= 1) {
            return n;
        }

        FibonacciTask f1 = new FibonacciTask(n - 1);
        // 创建子任务
        f1.fork();
        FibonacciTask f2 = new FibonacciTask(n - 2);
        // 等待子任务结果，并合并结果
        return f2.compute() + f1.join();
    }
    public static void main(String[] args) {
        // 创建分治任务线程池
        ForkJoinPool fjp = new ForkJoinPool(4);
        // 创建分治任务
        FibonacciTask ft = new FibonacciTask(30);
        // 启动分治任务
        Integer result = fjp.invoke(ft);
        // 输出结果
        System.out.println(result);
    }
}
```

### 使用 Fork/Join 实现单机版的 MapReduce 来统计单词数量
利用 MapReduce 的思想来统计单词数量，主要原理就是把文件拆分成若干个小文件，然后分别统计，最后再进行汇总，这非常符合分治的思想。

我们可以利用二分法递归地对文件进行拆分，直到每个小文件只有一行数据，然后对每个文件进行分别统计并逐级汇总。[代码]如下：
```java
public class SingleMapReduceToCountWords extends RecursiveTask<Map<String, Long>> {

    private String[] fc;
    private int start, end;

    SingleMapReduceToCountWords(String[] fc, int fr, int to) {
        this.fc = fc;
        this.start = fr;
        this.end = to;
    }

    @Override
    protected Map<String, Long> compute() {
        if (end - start == 1) {
            return calc(fc[start]);
        } else {
            // 利用二分法递归分解
            int mid = (start + end) / 2;
            SingleMapReduceToCountWords smr1 = new SingleMapReduceToCountWords(fc, start, mid);
            smr1.fork();
            SingleMapReduceToCountWords smr2 = new SingleMapReduceToCountWords(fc, mid, end);
            // 计算子任务，并返回合并的结果
            return merge(smr2.compute(), smr1.join());
        }
    }

    /**
     * 结果合并
     *
     * @param r1 r1
     * @param r2 r2
     * @return res
     */
    private Map<String, Long> merge(Map<String, Long> r1, Map<String, Long> r2) {
        Map<String, Long> result = new HashMap<>(r1);
        // 合并结果
        r2.forEach((k, v) -> result.merge(k, v, (a, b) -> a + b));
        return result;
    }

    /**
     * 统计单词数量
     *
     * @param line 每一行数据
     * @return map
     */
    private Map<String, Long> calc(String line) {
        Map<String, Long> result = new HashMap<>();
        // 分割单词
        String[] words = line.split("\\s+");
        // 统计单词数量
        for (String w : words) {
            Long v = result.get(w);
            if (null != v) {
                result.put(w, v + 1);
            } else {
                result.put(w, 1L);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        String[] fc = {"hello world",
                "hello me",
                "hello fork",
                "hello join",
                "fork join in world"};
        // 创建 ForkJoin 线程池
        ForkJoinPool fjp = new ForkJoinPool(3);
        // 创建任务
        SingleMapReduceToCountWords smr = new SingleMapReduceToCountWords(fc, 0, fc.length);
        // 启动任务
        Map<String, Long> result = fjp.invoke(smr);
        // 输出结果
        result.forEach((k, v) -> System.out.println(k + ":" + v));
    }
}
```

### 总结
Fork/Join 主要的作用就是解决分治任务，其核心是 ForkJoinPool。但默认情况先所有的并行流计算都是共享一个 ForkJoinPool 的，它的默认线程数是 CPU 的核数，如果所有的并行流计算都是 CPU 密集型计算的话，完全没有问题，但是如果存在 I/O 密集型的并行流计算，那么很可能会因为一个很慢的 I/O 计算而拖慢整个系统的性能。所以建议用不同的 ForkJoinPool 执行不同类型的计算任务。


