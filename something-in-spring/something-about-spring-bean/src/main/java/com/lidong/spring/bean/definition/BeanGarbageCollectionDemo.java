package com.lidong.spring.bean.definition;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Bean 垃圾回收
 *
 * @author Ls J
 * @date 2020/5/16 1:28 PM
 */
public class BeanGarbageCollectionDemo {

    public static void main(String[] args) throws InterruptedException {
        // 创建 BeanFactory 容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 注册 Configuration Class（配置类）
        applicationContext.register(BeanInitializationDemo.class);
        // 启动 Spring 应用上下文
        applicationContext.refresh();
        // 关闭 Spring 应用上下文
        applicationContext.close();
        Thread.sleep(5000L);
        // 强制触发 GC
        System.gc();
        Thread.sleep(5000L);
    }
}
