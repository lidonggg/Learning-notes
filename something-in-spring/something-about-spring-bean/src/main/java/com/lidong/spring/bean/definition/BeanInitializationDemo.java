package com.lidong.spring.bean.definition;

import com.lidong.spring.bean.factory.DefaultUserFactory;
import com.lidong.spring.bean.factory.UserFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

/**
 * @author Ls J
 * @date 2020/5/16 12:38 PM
 */
@Configuration
public class BeanInitializationDemo {

    public static void main(String[] args) {
        // 创建 BeanFactory 容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 注册 Configuration Class（配置类）
        applicationContext.register(BeanInitializationDemo.class);
        // 启动 Spring 应用上下文，这里会
        applicationContext.refresh();
        // 非延迟初始化在 Spring 应用上下文启动完成的时候被初始化
        // 延迟初始化则是一种按需初始化，当执行到下面的 applicationContext.getBean(UserFactory.class) 的时候，它才会被初始化
        System.out.println("Spring 应用上下文已启动...");
        // 依赖查找 UserFactory
        UserFactory userFactory = applicationContext.getBean(UserFactory.class);
        System.out.println(userFactory);
        System.out.println("Spring 应用上下文准备关闭...");
        // 关闭 Spring 应用上下文
        applicationContext.close();
        System.out.println("Spring 应用上下文已关闭...");
    }

    /**
     * 配置自定义初始化方法
     *
     * @return userFactory
     */
    @Bean(initMethod = "initUserFactory", destroyMethod = "doDestroy")
    @Lazy(value = false)
    public DefaultUserFactory userFactory() {
        return new DefaultUserFactory();
    }
}
